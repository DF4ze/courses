package org.df4ze.course2.principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import org.df4ze.course2.dao.factory.DAOFactory;
import org.df4ze.course2.mdl.Logger;
import org.df4ze.course2.mdl.Refactorer;
import org.df4ze.course2.mdl.WarcReader;
import org.df4ze.course2.tools.Debug;

public class Indexifier {

	private static String path;
	private static WarcReader warcReader;
	private static boolean refactor = true;
	
	public Indexifier() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
//		String host = "192.168.1.32";
//		String user = "wappalyzer";
//		String password = "Toulouse31";
		String host = "127.0.0.1";
		String user = "root";
		String password = "Banzai31";
		String base = "courses2_0";

		DAOFactory.setBase(base);
		DAOFactory.setHost(host);
		DAOFactory.setPassword(password);
		DAOFactory.setUser(user);

		// args get path
		if( args.length < 1 ){
			System.out.println("Please set WARCS path in parameter");
			return;
		}
		path = args[0];
		
		if( args.length >= 2 )
			if( args[1].toLowerCase().equals("false")  )
			refactor = false;
		
		if( args.length >= 3 ){
			if( args[2].toLowerCase().equals("true")  )
				Debug.setEnable(true);
			else
				Debug.setEnable(false);
		}else
			Debug.setEnable(false);
				
		
		String info = "\n\n\nIndexifier v2\n"+
				" - Path WARC : "+path+"\n"+
				" - mySql host : "+host+"\n"+
				" - mySql base : "+base+"\n"+
				" - mySql user : "+user+"\n"+
				"\n\nStarting...\n\n";
		
		System.out.println(info);
		Logger.write(info);
		
		Thread t = new Thread(new Runnable() {	
			
			public void run() {
	            if( path != null && path != "" ){
	            	File arc = new File(path);
	            	try{
						warcReader = new WarcReader( arc );
						// this will take a while
	            		warcReader.getURLs();
	            		
	            		
	            		System.out.println("================\nIndexifier ended\n================");
	            		Logger.write("================\nIndexifier ended\n================\n\n");
	            		
	            		if( refactor ){
		            		Refactorer.makeCourseComplete();
		            		
		            		System.out.println("================\nRefactorer ended\n================");
		            		Logger.write("================\nRefactorer ended\n================\n\n");
	            		}
	            		System.exit(0);
	            		
					}catch( FileNotFoundException e ){
						System.err.println( "Folder don't exists... app will shutdown" );
						System.exit(0);
					}
            	}
            }
   		});	
   		
   		t.start();
   		
   		
   		Timer timer = new Timer();
   		timer.scheduleAtFixedRate(new TimerTask() {
   		  @Override
   		  public void run() {
   			 System.gc();
   		  }
   		}, 2*60*1000l, 2*60*1000l);

	}

}
