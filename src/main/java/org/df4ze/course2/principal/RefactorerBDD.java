package org.df4ze.course2.principal;

import java.util.Timer;
import java.util.TimerTask;

import org.df4ze.course2.dao.factory.DAOFactory;
import org.df4ze.course2.mdl.Logger;
import org.df4ze.course2.mdl.Refactorer;
import org.df4ze.course2.tools.Debug;

public class RefactorerBDD {

	private static Integer from = null;

	public RefactorerBDD() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Debug.setEnable(false);
		
//		String host = "192.168.1.32";
//		String user = "wappalyzer";
//		String password = "Toulouse31";
		String host = "127.0.0.1";
		String user = "root";
		String password = "";
		String base = "courses2_0";

		DAOFactory.setBase(base);
		DAOFactory.setHost(host);
		DAOFactory.setPassword(password);
		DAOFactory.setUser(user);

		if( args.length == 1 )
			try{
				from = Integer.parseInt(args[0]);
			}catch( Exception e ){
				from = null;
			}
		
		String info = "\n\n\nRefactorer v2\n"+
				" - mySql host : "+host+"\n"+
				" - mySql base : "+base+"\n"+
				" - mySql user : "+user+"\n"+
				"\n\nStarting...\n\n";
		
		System.out.println(info);
		Logger.write(info);
		
		Thread t = new Thread(new Runnable() {	
			
			public void run() {
				try{
					if( from == null )
						Refactorer.makeCourseComplete();
					else
						Refactorer.makeCourseComplete(from);
//					if( from == null )
//						RefactorerMAJMusique.makeCourseComplete();
//					else
//						RefactorerMAJMusique.makeCourseComplete(from);
					
					
					String text = "====================\nJob is done\n================\n";
					System.out.println(text);
					Logger.write(text);
					
					System.exit(0);
				}catch( Exception e ){
					e.printStackTrace();
					System.exit(0);
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
