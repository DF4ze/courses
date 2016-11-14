package org.df4ze.course2.mdl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.df4ze.course2.dao.DAO;
import org.df4ze.course2.dao.DAOException;
import org.df4ze.course2.dao.bean.Arrivee;
import org.df4ze.course2.dao.bean.Cote;
import org.df4ze.course2.dao.bean.Course;
import org.df4ze.course2.dao.bean.CourseComplete;
import org.df4ze.course2.dao.bean.Partant;
import org.df4ze.course2.dao.bean.Rapport;
import org.df4ze.course2.dao.factory.DAOFactory;
import org.df4ze.course2.dao.factory.mysql.MySqlDAOCourse;

public class Refactorer {

	private static Integer from = null;
	
	private Refactorer() {
	}
	
	public static void makeCourseComplete( Integer fromCourseId ){
		from = fromCourseId;
		makeCourseComplete();
	}	
	
	public static void makeCourseComplete(){
		
		DAO<Course> DAOCourse = DAOFactory.getDAOFactory().getCourseDAO();
		DAO<Cote> DAOCote = DAOFactory.getDAOFactory().getCoteDAO();
		DAO<Partant> DAOPartant = DAOFactory.getDAOFactory().getPartantDAO();
		DAO<Rapport> DAORapport = DAOFactory.getDAOFactory().getRapportDAO();
		DAO<Arrivee> DAOArrivee = DAOFactory.getDAOFactory().getArriveeDAO();
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
			String texte = "Impossible de recuperer les elements des courses : "+e.getMessage()+"\nL'application va s'arreter.";
			System.err.println(texte);
			Logger.write(texte);
			System.exit(0);
		}
		
		
		for( Course course : coursesList ){
			
			CourseComplete cc = new CourseComplete();
			
			///////////////////////////////////
			// Infos Course
			cc.setId(course.getId());
			cc.setDateCourse(course.getDate());
			cc.setNumeroReunion(course.getReunion());
			cc.setNumeroCourse(course.getCourse());
			cc.setHippodrome(course.getHippodrome());
			cc.setPrime(course.getPrime());
			cc.setTypeCourse(course.getType());
			cc.setAutoStart(course.getDepart());
			
			
			////////////////////////////////////
			// Infos Rapport
			Rapport rapport = new Rapport();
			rapport.setCourseId(course.getId());
			
			try {
				ArrayList<Rapport> rapportsList = DAORapport.findByCriteria(rapport);
				if( rapportsList.size() == 0 )
					continue;
				
				for( Rapport unRap : rapportsList ){
					
					if( unRap.getArrivee() == 1 ){
						cc.setNumeroChvlPremier( unRap.getNumCheval() );
						cc.setRapGagnant(unRap.getGagnant());
						cc.setRapPlacePremier(unRap.getPlace());
					
					}else if( unRap.getArrivee() == 2  ){
						cc.setNumeroChvlDeuxieme( unRap.getNumCheval() );
						cc.setRapPlaceDeuxieme(unRap.getPlace());
					
					}else if( unRap.getArrivee() == 3  ){
						cc.setNumeroChvlTroisieme( unRap.getNumCheval() );
						cc.setRapPlaceTroisieme(unRap.getPlace());
					
					}
						
				}
				
				rapportsList = null;
			} catch (DAOException e) {
				String texte = "Impossible de recuperer les elements de RAPPORT pour la course "+course.getId()+" : "+e.getMessage();
				System.err.println(texte);
				Logger.write(texte);
				continue;
			}
			
			
			
			
			
			////////////////////////////////////
			// Infos Cote
			Cote coteFavPreum = null;
			Cote coteFavDeuz = null;
			Cote coteFavTroiz = null;
			
			Cote cotePrCtPreum = null;
			Cote cotePrCtDeuz = null;
			Cote cotePrCtTroiz = null;
			
			
			Cote cote = new Cote();
			cote.setCourseId(course.getId());
			try {
				ArrayList<Cote> cotesList = DAOCote.findByCriteria(cote);
				if( cotesList.size() == 0 )
					continue;
				
				cc.setNombrePartant(cotesList.size());
				
				// On recupere les 3 favoris : cote < 5
				// et les 3 meilleurs pourcentage
				int nbCoteInf5 = 0;
				for( Cote uneCote : cotesList ){

					if( uneCote.getCote() < 5 ){
						nbCoteInf5 ++;
					}	
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
					
					
					if( cotePrCtPreum == null )
						cotePrCtPreum = uneCote;   // Cloner??????????????
					else{

						if( cotePrCtPreum.getEnjeux() < uneCote.getEnjeux() ){
							cotePrCtTroiz = cotePrCtDeuz;
							cotePrCtDeuz = cotePrCtPreum;
							cotePrCtPreum = uneCote;
						
						}else if( cotePrCtDeuz == null ){
							cotePrCtDeuz = uneCote;
						
						}else{
							if( cotePrCtDeuz.getEnjeux() < uneCote.getEnjeux() ){
								cotePrCtTroiz = cotePrCtDeuz;
								cotePrCtDeuz = uneCote;
								
							}else if( cotePrCtTroiz == null ){
								cotePrCtTroiz = uneCote;
							
							}else if( cotePrCtTroiz.getEnjeux() < uneCote.getEnjeux() ){
								cotePrCtTroiz = uneCote;
							}
						}
					}
					
				}
				
				cc.setNombreChevauxInfCinq(nbCoteInf5);
				
				if( coteFavPreum != null ){
					cc.setNumeroPremierFavori(coteFavPreum.getNumCheval());
					
				}
				if( coteFavDeuz != null ){
					cc.setNumeroDeuxiemeFavori(coteFavDeuz.getNumCheval());
					
				}
				if( coteFavTroiz != null ){
					cc.setNumeroTroisiemeFavori(coteFavTroiz.getNumCheval());
					
				}
				
				cc.setPourcentPremierFavori(( cotePrCtPreum == null?null:cotePrCtPreum.getEnjeux() ));
				cc.setPourcentDeuxiemeFavori(( cotePrCtDeuz  == null?null:cotePrCtDeuz.getEnjeux() ));
				cc.setPourcentTroisiemeFavori(( cotePrCtTroiz == null?null:cotePrCtTroiz.getEnjeux() ));
				
				// calcul de la somme des enjeux/prcent
				Float somPrCent = 	( cotePrCtPreum == null?0f:cotePrCtPreum.getEnjeux() ) +
									( cotePrCtDeuz  == null?0f:cotePrCtDeuz.getEnjeux() )+
									( cotePrCtTroiz == null?0f:cotePrCtTroiz.getEnjeux() );
				cc.setTotalPourcent(somPrCent);
				
				// nb chv cote < 5 arrivé placé => dans les 3 1er
				int nbInf5Place = 0;
				
				if( coteFavPreum != null ){
					if( coteFavPreum.getNumCheval() == cc.getNumeroChvlPremier() || 
						coteFavPreum.getNumCheval() == cc.getNumeroChvlDeuxieme() ||
						coteFavPreum.getNumCheval() == cc.getNumeroChvlTroisieme())
						nbInf5Place ++;
				}
				
				if( coteFavDeuz != null ){
					if( coteFavDeuz.getNumCheval() == cc.getNumeroChvlPremier() || 
						coteFavDeuz.getNumCheval() == cc.getNumeroChvlDeuxieme() ||
						coteFavDeuz.getNumCheval() == cc.getNumeroChvlTroisieme())
						nbInf5Place ++;
				}
				
				if( coteFavTroiz != null ){
					if( coteFavTroiz.getNumCheval() == cc.getNumeroChvlPremier() || 
						coteFavTroiz.getNumCheval() == cc.getNumeroChvlDeuxieme() ||
						coteFavTroiz.getNumCheval() == cc.getNumeroChvlTroisieme())
						nbInf5Place ++;
				}
				
//				if( coteFavPreum.getNumCheval() == cc.getNumeroChvlPremier() || 
//						coteFavDeuz.getNumCheval() == cc.getNumeroChvlPremier() ||	
//						coteFavTroiz.getNumCheval() == cc.getNumeroChvlPremier() )
//						nbInf5Place ++;
//				if( coteFavPreum.getNumCheval() == cc.getNumeroChvlDeuxieme() || 
//						coteFavDeuz.getNumCheval() == cc.getNumeroChvlDeuxieme() ||	
//						coteFavTroiz.getNumCheval() == cc.getNumeroChvlDeuxieme() )
//						nbInf5Place ++;
//				if( coteFavPreum.getNumCheval() == cc.getNumeroChvlTroisieme() || 
//						coteFavDeuz.getNumCheval() == cc.getNumeroChvlTroisieme() ||	
//						coteFavTroiz.getNumCheval() == cc.getNumeroChvlTroisieme() )
//						nbInf5Place ++;
				cc.setNombreChvlFavoriPlace(nbInf5Place);
				
				cotesList = null;
			} catch (DAOException e) {
				String texte = "Impossible de recuperer les elements de COTE pour la course "+course.getId()+" : "+e.getMessage();
				System.err.println(texte);
				Logger.write(texte);
				continue;
			}
			
			
			
			///////////////////////////
			// Arrivees
			Arrivee arrivee = new Arrivee(course.getId(), null, null);
			try {
				List<Arrivee> arrivees = DAOArrivee.findByCriteria(arrivee);
				
				Map<Integer, Integer> resultats = getNumFromPlace(arrivees);
				
				try{
					cc.setP1er(resultats.get(1));
					cc.setP2eme(resultats.get(2));
					cc.setP3eme(resultats.get(3));
					cc.setP4eme(resultats.get(4));
					cc.setP5eme(resultats.get(5));
					cc.setP6eme(resultats.get(6));
					cc.setP7eme(resultats.get(7));
					cc.setP8eme(resultats.get(8));
					cc.setP9eme(resultats.get(9));
					cc.setP10eme(resultats.get(10));
					cc.setP11eme(resultats.get(11));
					cc.setP12eme(resultats.get(12));
					cc.setP13eme(resultats.get(13));
					cc.setP14eme(resultats.get(14));
					cc.setP15eme(resultats.get(15));
				}catch( Exception e2 ){
					
				}
				
			} catch (DAOException e) {
				String texte = "Impossible de recuperer les elements de ARRIVEE pour la course "+course.getId()+" : "+e.getMessage();
				System.err.println(texte);
				Logger.write(texte);
				continue;
			}
						
			
			//////////////////////////////
			// Info partant
			Partant partant = new Partant();
			partant.setCourseId(course.getId());
			
			int ageMin = 30;
			int ageMax = 0;
			try {
				ArrayList<Partant> partantsListe = DAOPartant.findByCriteria(partant);
				Partant ChvBestGains = null;
				if( partantsListe.size() != 0 ){
					// on fait le tour de tout les partant de cette course
					for( Partant unPart : partantsListe ){
						// si le numero du cheval en cours est celui du 1er favoris
						if( unPart.getNumCheval() == cc.getNumeroPremierFavori() ){
							cc.setMusiquePremier(unPart.getMusique());
							//cc.setAgeSexChvlPremier(unPart.getAgeSexe());
							cc.setNomChvlPremier( unPart.getNomCheval() );
							//cc.setGainsPremier(unPart.getGains());
							//break;
						}
						
						if( unPart.getNumCheval().equals( cc.getP1er() ) ){
							cc.setGainChvlPremier(unPart.getiGains());
						}
						
						// min et max age
						try{
							String text = unPart.getAgeSexe().replace("F", "");
							text = text.replace("H", "");
							text = text.replace("M", "");
							
							int age = Integer.parseInt(text);
							
							if( age > ageMax )
								ageMax = age;
							if( age < ageMin )
								ageMin = age;
							
						}catch(Exception e){
							String texte = "Pb parse Age ("+unPart.getAgeSexe()+"), course "+course.getId()+" : "+e.getMessage();
							System.err.println(texte);
							Logger.write(texte);
							
						}
						
						// musique du cheval qui a le plus gros gain
						if( ChvBestGains == null && unPart.getiGains() != null ){
							
							ChvBestGains = unPart.clone();
						}else if( unPart.getiGains() != null )
							if( unPart.getiGains() > ChvBestGains.getiGains() ){
							ChvBestGains = unPart.clone();
						}
						
					}
					cc.setAgeSexChvlPremier( ageMin+"-"+ageMax);
					if( ChvBestGains != null ){
						cc.setMusiqueMeilleurGains(ChvBestGains.getMusique());
						cc.setNumeroMeilleurGains(ChvBestGains.getNumCheval());
					}
				}
				partantsListe = null;
				
			} catch (DAOException e) {
				String texte = "Impossible de recuperer les elements de PARTANT pour la course "+course.getId()+" : "+e.getMessage();
				System.err.println(texte);
				Logger.write(texte);
				continue;
			}
			
			
			

			
			
			///////////////////////////
			// envoi BDD
			try {
				DAOcc.create(cc);
				
				System.out.println("Insert ok for N� : "+course.getId());
				
				cc = null;
				
				
			} catch (DAOException e) {
				String texte = "Impossible de creer la COURSE COMPLETE pour la course "+course.getId()+" : "+e.getMessage();
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
	
	private static Map<Integer, Integer> getNumFromPlace( List<Arrivee> arrivees ){
		Map<Integer, Integer> ret = new HashMap<Integer, Integer>();
		
		for (Arrivee arrivee : arrivees) {
			ret.put(arrivee.getNumArrivee(), arrivee.getNumCheval());
		}
		
		return ret;
	}

}
