package org.df4ze.course2.principal;

import java.io.PrintStream;
import java.util.ArrayList;

import org.df4ze.course2.dao.DAO;
import org.df4ze.course2.dao.DAOException;
import org.df4ze.course2.dao.bean.Partant;
import org.df4ze.course2.dao.factory.DAOFactory;
import org.df4ze.course2.tools.Debug;

public class Relationner {

	public static void main(String[] args) {
		Debug.setEnable(false);
		
		String host = "127.0.0.1";
		String user = "root";
		String password = "";
		String base = "courses2_0";

		DAOFactory.setBase(base);
		DAOFactory.setHost(host);
		DAOFactory.setPassword(password);
		DAOFactory.setUser(user);		

		Partant p = new Partant();
		p.setCourseId(708860l);
		PrintStream out = System.out;
		DAO<Partant> DAOPartant = DAOFactory.getDAOFactory().getPartantDAO();
		try {
			ArrayList<Partant>alPartants = DAOPartant.findByCriteria(p);
			
			out.println( "Nb Partants : "+alPartants.size() );
			
			for( Partant partant : alPartants ){
				Partant search = new Partant();
				search.setNomCheval(partant.getNomCheval());
				
				ArrayList<Partant> nbCourses = DAOPartant.findByCriteria(search);
				out.println("Cheval : "+partant.getNomCheval()+" a fait "+nbCourses.size());
			}
		
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
