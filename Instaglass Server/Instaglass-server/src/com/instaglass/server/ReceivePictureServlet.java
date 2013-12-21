package com.instaglass.server;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ReceivePictureServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ReceivePictureServlet.class.getName());
	// Datastore is database where all device tokens get stored
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	// Handles HTTP GET request from the storeid.jsp
	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/receivePicture.jsp"); 
	}
	
	// Handles HTTP POST request - submit message from the storeid.jsp
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
		try{
		    ServletFileUpload upload = new ServletFileUpload();
		    FileItemIterator iter = upload.getItemIterator(req);
		    FileItemStream imageItem = iter.next();
		    InputStream imgStream = imageItem.openStream();
	
		    // construct our entity objects
		    Blob imageBlob = new Blob(IOUtils.toByteArray(imgStream));
		    InstaImage myImage = new InstaImage(imageItem.getName(), imageBlob);
	
		    // persist image
		    PersistenceManager pm = PMF.get().getPersistenceManager();
		    pm.makePersistent(myImage);
		    pm.close();
	
		    // respond to query
		    //res.setContentType("text/plain");
		    //res.getOutputStream().write("OK!".getBytes());
		} catch(IOException e){
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
/*
		try {
			ServletFileUpload upload = new ServletFileUpload();
			res.setContentType("text/plain");

			FileItemIterator iterator = upload.getItemIterator(req);
			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();
				InputStream stream = item.openStream();
				
				if (item.isFormField()) {
					log.warning("Got a form field: " + item.getFieldName());
				} else {
					log.warning("Got an uploaded file: " + item.getFieldName() +
							", name = " + item.getName());
					
					// You now have the filename (item.getName() and the
					// contents (which you can read from stream). Here we just
					// print them back out to the servlet output stream, but you
					// will probably want to do something more interesting (for
					// example, wrap them in a Blob and commit them to the
					// datastore).
					int len;
					byte[] buffer = new byte[8192];
					while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
						res.getOutputStream().write(buffer, 0, len);
					}
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}*/
	}

	
	
	/*public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException { 
    	String txtRegId = req.getParameter("txtRegId");
    	
    	// Creates device token entity and saves it in the database
    	Entity regId = new Entity("GCMDeviceIds",txtRegId);
    	regId.setProperty("regid", txtRegId);
    	if(!isReqIdExist(txtRegId)){
    		saveToDB(regId);
    		log.info("RegId inserted into DB: " + txtRegId);
    	}
    }*/
    
    // Save device token in the database
    private void saveToDB(Entity regId){
    	datastore.put(regId);
    }
    
    // Checks if the device token already exist in the database
    private boolean isReqIdExist(String regId){
    	Key keyRegId = KeyFactory.createKey("GCMDeviceIds", regId);
    	Entity entity = null;
    	try {
			entity = datastore.get(keyRegId);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(entity!=null){
    		return true;
    	}
    	return false;
    }
}


