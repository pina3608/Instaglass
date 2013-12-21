package com.instaglass.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class SendPictureServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SendPictureServlet.class.getName());
	
	
	// Handles HTTP GET request from the main.jsp 
	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/sendPicture.jsp"); 
	}
	
	// Handles HTTP POST request - submit message from the main.jsp
    public void doPost(HttpServletRequest req, HttpServletResponse res)
                throws IOException { 
    	String name = req.getParameter("pictureName");
	    // find desired image
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    Query query = pm.newQuery("select from " + InstaImage.class.getName());
	    List<InstaImage> results = (List<InstaImage>)query.execute(name);
	    if(results.isEmpty()){
	    	throw new IOException("No images");
	    }
	    else {
	    	for (InstaImage instaImage : results) {
	    		// Si no la encuentra no hace nada
				if(instaImage.getName().equals(name)){
			    	Blob image = instaImage.getImage();

				    // serve the first image
				    res.setContentType("image/jpeg");
				    res.getOutputStream().write(image.getBytes());
				    break;
				}
			}

	    }
	    
	    
    }
    
    

}
