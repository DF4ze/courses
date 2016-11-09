package org.df4ze.course2.dao.factory;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.df4ze.course2.dao.DAO;
import org.df4ze.course2.dao.bean.Arrivee;
import org.df4ze.course2.dao.bean.Cote;
import org.df4ze.course2.dao.bean.Course;
import org.df4ze.course2.dao.bean.CourseComplete;
import org.df4ze.course2.dao.bean.Partant;
import org.df4ze.course2.dao.bean.Rapport;
import org.df4ze.course2.dao.factory.mysql.MySqlDAOFactory;

//Abstract class DAO Factory
public abstract class DAOFactory {
	
	// Liste des types de DAO support�s par la fabrique
	//public static final int ORACLE = 1;
	public static final int MYSQL = 2;

	// Parametres de connexion
	private static String host = null;
	private static String user = null;
	private static String password = null;
	private static String base = null;
	
	// Il doit y avoir une methode pour chaque DAO qui peut �tre cr�� 
	// Les fabriques concr�tes doivent impl�ment�es ces m�thodes
	public abstract DAO<Course> getCourseDAO();
	public abstract DAO<Rapport> getRapportDAO();
	public abstract DAO<Cote> getCoteDAO();
	public abstract DAO<Partant> getPartantDAO();
	public abstract DAO<Arrivee> getArriveeDAO();
	public abstract DAO<CourseComplete> getCourseCompleteDAO();
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {

		case MYSQL :
			return new MySqlDAOFactory( host, user, password, base );
		default :
			return null;
		}
	}
	
	public static MySqlDAOFactory getDAOFactory() {
		return new MySqlDAOFactory( host, user, password, base );	
	}
	
	public static String getHost() {
		return host;
	}
	public static void setHost(String host) {
		DAOFactory.host = host;
	}
	
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		DAOFactory.user = user;
	}
	
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		DAOFactory.password = password;
	}
	
	public static String getBase() {
		return base;
	}
	public static void setBase(String base) {
		DAOFactory.base = base;
	}
	
	public ResultSet queryer( String query ) throws SQLException{
		Connection connect = MySqlDAOFactory.createConnection();

		Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery(query);

		return rs;
	}
}