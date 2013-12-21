package com.instaglass.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class SendPictureServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SendPictureServlet.class.getName());
	// API_KEY is sender_auth_token (server key previously generated in GCM)
	private static final String API_KEY = "";
	// Datastore is database where all device tokens get stored
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	
	// Handles HTTP GET request from the main.jsp 
	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/main.jsp"); 
	}
	
	// Handles HTTP POST request - submit message from the main.jsp
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException { 
    	String txtInput = req.getParameter("txtInput");
    	
    	
    }
    
    

}
