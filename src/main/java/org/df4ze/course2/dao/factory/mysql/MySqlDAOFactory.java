package org.df4ze.course2.dao.factory.mysql;

//Implementation de la factory DAO pour Oracle
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.df4ze.course2.dao.DAO;
import org.df4ze.course2.dao.DAOException;
import org.df4ze.course2.dao.bean.BeansList;
import org.df4ze.course2.dao.bean.Cote;
import org.df4ze.course2.dao.bean.Course;
import org.df4ze.course2.dao.bean.CourseComplete;
import org.df4ze.course2.dao.bean.ParentBean;
import org.df4ze.course2.dao.bean.Partant;
import org.df4ze.course2.dao.bean.Rapport;
import org.df4ze.course2.dao.factory.DAOFactory;
import org.df4ze.course2.mdl.Logger;

public class MySqlDAOFactory extends DAOFactory {
	public static final String DRIVER="com.mysql.jdbc.Driver";
	private static String sqlHost = null;
	private static String sqlUser = null;
	private static String sqlPassword = null;
	private static String sqlBase = null;
	private static String DBURL;
	//	public static final String DBURL="jdbc:mysql://localhost/crawler?user=root&password=&connectTimeout=60&socketTimeout=60"; //&autoReconnect=true&failOverReadOnly=false&maxReconnects=10
	public static final String DBLOGIN=null;//"culture";
	public static final String DBPASSWORD=null;//"afpa123";
	
	private static Connection myconnection = null;
	
	
	public MySqlDAOFactory( String host, String user, String password, String base ){
		sqlHost = host;
		sqlUser = user;
		sqlPassword = password;
		sqlBase = base;
		DBURL = "jdbc:mysql://"+sqlHost+"/"+sqlBase+"?user="+sqlUser+"&password="+sqlPassword+"&connectTimeout=350000&socketTimeout=350000&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";//&failOverReadOnly=false";//&maxReconnects=10

	}
	
	/**
	 * Obtenir une connexion
	 * @return connexion : instance de l'objet Connection
	 * @throws SQLException
	 */
	public static Connection createConnection ()
	{
		try {
			// SI pas de connexion ouverte
			//if (!isValidConnection())
			if( myconnection == null ){
				// Chargement du pilote (pas n�cessaire avec JDBC4)
				Class.forName (DRIVER);
				
				System.out.println( "Creating connection : DBURL='"+DBURL+"'" );
				
				// Utiliser la m�thode getConnexion() qui permet de d�finir le login/mot de passe
				if( DBLOGIN != null )
					myconnection = DriverManager.getConnection (DBURL, DBLOGIN, DBPASSWORD);
				else
					myconnection = DriverManager.getConnection (DBURL);
				
				
				//System.out.println("************* NetWork TimeOut : "+myconnection.getNetworkTimeout());
				
				//myconnection.setNetworkTimeout(null, 20000  );
				//myconnection.get

			}
//			Statement st = myconnection.createStatement(); 
//			st.execute("SET GLOBAL CONNECT TIMEOUT=28000");
			//Statement.
			
			return myconnection;
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Classe non trouv�e.");
			return myconnection;
		}
		catch (SQLException ex) {
			System.out.println("Ouverture connexion impossible : "+ex);
			return myconnection;
		}
	}
	
	
	/**
	 * Fermer la connexion
	 * 	 @return true si connexion a �t� ferm�e sinon false
	 */
	@SuppressWarnings("finally")
	public static boolean cloreConnexion ()
	{
		//System.out.println("\nFERMETURE DE LA CONNEXION - cloreConnexion()\n"); 
		
		// Bool�en retourn� par la m�thode
		boolean bClosed = false;
		
		try
		{
			// SI connexion ouverte
			//if (isValidConnection()){
			if (myconnection != null){
				// Fermer la connexion
				myconnection.close();
				// Positionner le handle � NULL
				myconnection = null;
				// Retourner true pour indiquer que la connexion ferm�e
				bClosed = true;
			}
		}
		catch (SQLException ex) {
			System.out.println("Fermeture connexion impossible " + ex.getMessage());
		}
		finally{
			return bClosed;
		}
	}
	
	/**
	 * Verifier si la connexion est ouverte
	 * Utilisation de la m�thode isValid() disponible avec jdbc4
	 * @return true si connexion ouverte sinon false
	 */
	public static boolean isValidConnection(){
	
		// SI handle vaut null ALORS connexion ferm�e SINON poursuivre traitement
		if (myconnection==null)
			return false;
			
		try{		
			// SI connexion est ferm�e RETOURNER false SINON RETOURNER true
			// note : m�thode isValid() existe depuis JDBC 4
			// isValid() teste la connexion en ex�cutant une requ�te
			// 10 secondes : temps d'attente
			if (myconnection.isValid(60))
				return true;
			else
				return false;	
		}
		catch(SQLException ex){
			return false;
		}
		finally{
		}
	}
	

	public static String getSqlHost() {
		return sqlHost;
	}
	public static void setSqlHost(String sqlHost) {
		MySqlDAOFactory.sqlHost = sqlHost;
	}

	public static String getSqlUser() {
		return sqlUser;
	}
	public static void setSqlUser(String sqlUser) {
		MySqlDAOFactory.sqlUser = sqlUser;
	}


	public static String getSqlPassword() {
		return sqlPassword;
	}
	public static void setSqlPassword(String sqlPassword) {
		MySqlDAOFactory.sqlPassword = sqlPassword;
	}


	public static String getSqlBase() {
		return sqlBase;
	}
	public static void setSqlBase(String sqlBase) {
		MySqlDAOFactory.sqlBase = sqlBase;
	}

	@Override
	public DAO<Course> getCourseDAO() {
		return new MySqlDAOCourse();
	}

	@Override
	public DAO<Rapport> getRapportDAO() {
		return new MySqlDAORapport();
	}
	
	@Override
	public DAO<Cote> getCoteDAO() {
		return new MySqlDAOCote();
	}

	@Override
	public DAO<Partant> getPartantDAO() {
		return new MySqlDAOPartant();
	}

	@Override
	public DAO<CourseComplete> getCourseCompleteDAO() {
		return new MySqlDAOCourseComplete();
	}

	public void recordSet( BeansList beansList ){
		
		if( beansList == null )
			return;
		if( beansList.get().size() == 0 )
			return;
		
		ArrayList<Cote> cotesList = new ArrayList<Cote>();
		ArrayList<Course> coursesList = new ArrayList<Course>();
		ArrayList<Partant> partantsList = new ArrayList<Partant>();
		ArrayList<Rapport> rapportsList = new ArrayList<Rapport>();
		ArrayList<CourseComplete> courseCompletesList = new ArrayList<CourseComplete>();
		
		
		// tri
		for( ParentBean bean : beansList.get() ){
			
			if( bean instanceof Course ){
				boolean ok = true;
				for( Course course : coursesList ){
					if( course.getId() == ((Course)bean).getId() )
						ok = false;
				}
				
				if( ok )
					coursesList.add((Course)bean);
				
			}else if( bean instanceof Cote )
				cotesList.add((Cote)bean);
			else if( bean instanceof Partant )
				partantsList.add((Partant)bean);
			else if( bean instanceof Rapport )
				rapportsList.add((Rapport)bean);
			else if( bean instanceof CourseComplete )
				courseCompletesList.add((CourseComplete)bean);
		}
		
		
		
		
		if( coursesList.size() != 0 ){
			DAO<Course> DAOCourse = getCourseDAO();
			for( Course course : coursesList ){
				try {
					DAOCourse.create(course);
				} catch (DAOException e) {
					System.err.println("Erreur d'insertion dans COURSE : "+e.getMessage());
				}
			}
		}
		
		if( courseCompletesList.size() != 0 ){
			DAO<CourseComplete> DAOCourseComplete = getCourseCompleteDAO();
			for( CourseComplete courseComplete : courseCompletesList ){
				try {
					DAOCourseComplete.create(courseComplete);
				} catch (DAOException e) {
					System.err.println("Erreur d'insertion dans COURSE_COMPLETE : "+e.getMessage());
					Logger.write("Erreur d'insertion dans COURSE_COMPLETE : "+e.getMessage());
				}
			}
		}
		
		if( cotesList.size() != 0 ){
			DAO<Cote> DAOCote = getCoteDAO();
			
			try {
				DAOCote.create( cotesList );
			} catch (DAOException e) {
				System.err.println("Erreur d'insertion dans COTE : "+e.getMessage());
				Logger.write("COTE : "+ e.getMessage() + " --> " +e.getRequete());
			}
			
		}
		
		if( partantsList.size() != 0 ){
			DAO<Partant> DAOpartant = getPartantDAO();
			
			try {
				DAOpartant.create( partantsList );
			} catch (DAOException e) {
				System.err.println("Erreur d'insertion dans PARTANT : "+e.getMessage());
				Logger.write("PARTANT : "+ e.getMessage() + " --> " +e.getRequete());
			}
			
		}
		
		if( rapportsList.size() != 0 ){
			DAO<Rapport> DAORapport = getRapportDAO();
			
			try {
				DAORapport.create( rapportsList );
			} catch (DAOException e) {
				System.err.println("Erreur d'insertion dans RAPPORT : "+e.getMessage());
				Logger.write("RAPPORT : "+ e.getMessage() + " --> " +e.getRequete());
			}
			
		}
		
	}




}
