package com.test.web;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(description = "java logging API", urlPatterns = { "/MyJavaLogging" })
public class MyJavaLogging extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger("LoggerName");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyJavaLogging() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	System.out.println("---- init servlet ");
    	ServletContext context = getServletContext( );
    	context.log("++++ context.log: init() ");
    	
//		Handler consoleHandler = new ConsoleHandler();
    	Handler consoleHandler;
		try {
			String path = getServletContext().getRealPath(File.separator) + File.separator + "MyLogFile4.log";
			consoleHandler = new FileHandler(path, true);
			SimpleFormatter formatter = new SimpleFormatter();
			consoleHandler.setFormatter(formatter);
			
			consoleHandler.setFilter(new Filter() {
				
				@Override
				public boolean isLoggable(LogRecord record) {
					if(record.getLevel().intValue() < Level.SEVERE.intValue()){
						return false;
					}
					return true;
				}
			});
			
			logger.addHandler(consoleHandler);
			logger.setUseParentHandlers(false);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(getClass().getClassLoader().getResource("logging.properties"));
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*****HelloWorld: doGet() servlet ");
		
//		request.getRequestDispatcher("/HelloWorldView.jsp").forward(request, response);
//		request.getRequestDispatcher("/SessionTrack").forward(request, response);
//		response.sendRedirect(request.getContextPath()+ "/SessionTrack");
		 	 
		URL location = MyJavaLogging.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
        
        String webAppPath = getServletContext().getRealPath("/");
        System.out.println(webAppPath);

		logger.log(Level.SEVERE, "keep moving forward");
		logger.log(Level.WARNING, "if you continue learning, nobody can stop you");
		logger.log(Level.CONFIG, "I have a strong willpower");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

