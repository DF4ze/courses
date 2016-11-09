package org.df4ze.course2.mdl;

import java.util.ArrayList;

import org.df4ze.course2.dao.DAO;
import org.df4ze.course2.dao.DAOException;
import org.df4ze.course2.dao.bean.Cote;
import org.df4ze.course2.dao.bean.Course;
import org.df4ze.course2.dao.bean.CourseComplete;
import org.df4ze.course2.dao.bean.Partant;
import org.df4ze.course2.dao.factory.DAOFactory;
import org.df4ze.course2.dao.factory.mysql.MySqlDAOCourse;

public class RefactorerMAJMusique {

	private static Integer from = null;
	
	private RefactorerMAJMusique() {
	}
	
	public static void makeCourseComplete( Integer fromCourseId ){
		from = fromCourseId;
		makeCourseComplete();
	}	
	
	public static void makeCourseComplete(){
		
		DAO<Course> DAOCourse = DAOFactory.getDAOFactory().getCourseDAO();
		DAO<Cote> DAOCote = DAOFactory.getDAOFactory().getCoteDAO();
		DAO<Partant> DAOPartant = DAOFactory.getDAOFactory().getPartantDAO();
//		DAO<Rapport> DAORapport = DAOFactory.getDAOFactory().getRapportDAO();
		DAO<CourseComplete> DAOcc = DAOFactory.getDAOFactory().getCourseCompleteDAO();
		
		ArrayList<Course> coursesList = null;
		try {
			if( from != null ){
				String texte = "Starting from id "+from;
				System.err.println(texte);
				Logger.write(texte);
			}
			coursesList = ((MySqlDAOCourse)DAOCourse).findAllFrom( from );
			
		} catch (DAOException e) {
			String texte = "Impossible de r�cup�rer les �l�ments des courses : "+e.getMessage()+"\nL'application va s'arreter.";
			System.err.println(texte);
			Logger.write(texte);
			System.exit(0);
		}
		
		
		for( Course course : coursesList ){
			
			CourseComplete cc = new CourseComplete();
			
			///////////////////////////////////
			// Infos Course
			cc.setId(course.getId());
			
					
			
			
			
			////////////////////////////////////
			// Infos Cote
			Cote coteFavPreum = null;
			Cote coteFavDeuz = null;
			Cote coteFavTroiz = null;

			
			
			Cote cote = new Cote();
			cote.setCourseId(course.getId());
			try {
				ArrayList<Cote> cotesList = DAOCote.findByCriteria(cote);
				if( cotesList.size() == 0 )
					continue;
				
				cc.setNombrePartant(cotesList.size());
				
				// On r�cup�re les 3 favoris : cote < 5
				// et les 3 meilleurs pourcentage
				for( Cote uneCote : cotesList ){

					if( coteFavPreum == null )
						coteFavPreum = uneCote;   // Cloner??????????????
					else{

						if( coteFavPreum.getCote() > uneCote.getCote() ){
							coteFavTroiz = coteFavDeuz;
							coteFavDeuz = coteFavPreum;
							coteFavPreum = uneCote;
						
						}else if( coteFavDeuz == null ){
							coteFavDeuz = uneCote;
						
						}else{
							if( coteFavDeuz.getCote() > uneCote.getCote() ){
								coteFavTroiz = coteFavDeuz;
								coteFavDeuz = uneCote;
								
							}else if( coteFavTroiz == null ){
								coteFavTroiz = uneCote;
							
							}else if( coteFavTroiz.getCote() > uneCote.getCote() ){
								coteFavTroiz = uneCote;
							}
						}
					}
				}
				
				
				if( coteFavPreum != null ){
					cc.setNumeroPremierFavori(coteFavPreum.getNumCheval());
					
				}
				
				cotesList = null;
			} catch (DAOException e) {
				String texte = "Impossible de r�cup�rer les �l�ments de COTE pour la course "+course.getId()+" : "+e.getMessage();
				System.err.println(texte);
				Logger.write(texte);
				continue;
			}
			
			
			
			//////////////////////////////
			// Info partant
			Partant partant = new Partant();
			partant.setCourseId(course.getId());
			
			try {
				ArrayList<Partant> partantsListe = DAOPartant.findByCriteria(partant);
				
				if( partantsListe.size() != 0 ){
					for( Partant unPart : partantsListe ){
						if( unPart.getNumCheval() == cc.getNumeroPremierFavori() ){
							cc.setMusiquePremier(unPart.getMusique());
							//cc.setAgeSexChvlPremier(unPart.getAgeSexe());
							cc.setNomChvlPremier( unPart.getNomCheval() );
							break;
						}
						
						
						
					}
					
				}
				partantsListe = null;
				
			} catch (DAOException e) {
				String texte = "Impossible de r�cup�rer les �l�ments de PARTANT pour la course "+course.getId()+" : "+e.getMessage();
				System.err.println(texte);
				Logger.write(texte);
				continue;
			}
			
			
			
			
			///////////////////////////
			// envoi BDD
			try {
				//DAOcc.create(cc);
				
				DAOcc.update(cc);
				System.out.println("MAJ ok for N� : "+course.getId());
				
				cc = null;
				
				
			} catch (DAOException e) {
				String texte = "Impossible de MAJ la COURSE COMPLETE pour la course "+course.getId()+" : "+e.getMessage();
				System.err.println(texte);
				Logger.write(texte);
				continue;
			}
			
			
			try {
				System.gc();
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				
			}
		}
	}

}
