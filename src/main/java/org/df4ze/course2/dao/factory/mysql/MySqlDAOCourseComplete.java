package org.df4ze.course2.dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.df4ze.course2.dao.DAO;
import org.df4ze.course2.dao.DAOException;
import org.df4ze.course2.dao.bean.CourseComplete;

public class MySqlDAOCourseComplete implements DAO<CourseComplete> {
	
	
	Connection connect = null;
	//private String qry_insert_app = "INSERT INTO " + DAO.t_rapport + " ( course_id, num_chv, cote, enjeux, arrivee, place, gagnant )"+"VALUES( ?, ?, ?, ?, ?, ?, ? )";
	private String qry_findAll = "SELECT * FROM " + DAO.t_coursecomplete;
	private String qry_count = "SELECT COUNT(*) as nb FROM " + DAO.t_coursecomplete;
	private String qry_delete = "DELETE FROM " + DAO.t_coursecomplete;
	private String qry_deleteByID = qry_delete + " WHERE id = ?";
	//private String qry_updateWhere = "UPDATE " + DAO.t_rapport + " SET course_id=?, num_chv=?, cote=?, enjeux=?, arrivee=?, place=?, gagnant=? WHERE ";

	public MySqlDAOCourseComplete(  ) {	
		connect = MySqlDAOFactory.createConnection();
	}
	

	public void closeConnection(){
		MySqlDAOFactory.cloreConnexion();
	}
	
	
	public CourseComplete findByID(long id) throws DAOException {
		CourseComplete oobj = null;
		try{
			oobj = new CourseComplete();
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(qry_findAll+" WHERE id = "+id);
			
			if( rs.first() ){
				oobj.setId(rs.getLong("id"));
				oobj.setAgeSexChvlPremier(rs.getString("age_sexe"));
				oobj.setAutoStart(rs.getString("depart"));
				oobj.setDateCourse(rs.getString("date"));
				oobj.setHippodrome(rs.getString("hippodrome"));
				oobj.setMusiquePremier(rs.getString("musique_premier"));
				oobj.setNombreChevauxInfCinq(rs.getInt("nb_chv_inf_cinq"));
				oobj.setNombreChvlFavoriPlace(rs.getInt("nb_chv_fav_place"));
				oobj.setNombrePartant(rs.getInt("nb_partant"));
				oobj.setNumeroChvlDeuxieme(rs.getInt("num_chv_deuxieme"));
				oobj.setNumeroChvlPremier(rs.getInt("num_chv_premier"));
				oobj.setNumeroChvlTroisieme(rs.getInt("num_chv_troisieme"));
				oobj.setNumeroCourse(rs.getInt("num_course"));
				oobj.setNumeroDeuxiemeFavori(rs.getInt("num_fav_deuxieme"));
				oobj.setNumeroPremierFavori(rs.getInt("num_fav_premier"));
				oobj.setNumeroReunion(rs.getInt("num_reunion"));
				oobj.setNumeroTroisiemeFavori(rs.getInt("num_fav_troisieme"));
				oobj.setPourcentDeuxiemeFavori(rs.getFloat("pourcent_fav_deuxieme"));
				oobj.setPourcentPremierFavori(rs.getFloat("pourcent_fav_premier"));
				oobj.setPourcentTroisiemeFavori(rs.getFloat("pourcent_fav_troisieme"));
				oobj.setPrime(rs.getString("prime"));
				oobj.setRapGagnant(rs.getDouble("rap_gagnant"));
				oobj.setRapPlaceDeuxieme(rs.getDouble("rap_place_deuxieme"));
				oobj.setRapPlacePremier(rs.getDouble("rap_place_premier"));
				oobj.setRapPlaceTroisieme(rs.getDouble("rap_place_troisieme"));
				oobj.setTotalPourcent(rs.getFloat("total_pourcent"));
				oobj.setTypeCourse(rs.getString("type_course"));
				oobj.setNomChvlPremier(rs.getString("nom_chv_premier"));
				oobj.setMusiqueMeilleurGains(rs.getString("musique_meilleur_gain"));
				oobj.setNumeroMeilleurGains(rs.getInt("numero_meilleur_gain"));
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture : "+e.getMessage(), e);
		} 
		return oobj;
	}

	
	public ArrayList<CourseComplete> findAll() throws DAOException {
		ArrayList<CourseComplete> oobjs = null;
		try{
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(qry_findAll);
			
			oobjs = new ArrayList<CourseComplete>();
			while (rs.next()) { 
				CourseComplete obj = new CourseComplete();
				obj.setId(rs.getLong("id"));
				obj.setAgeSexChvlPremier(rs.getString("agesexe_chvl_premier"));
				obj.setAutoStart(rs.getString("depart"));
				obj.setDateCourse(rs.getString("date"));
				obj.setHippodrome(rs.getString("hippodrome"));
				obj.setMusiquePremier(rs.getString("musique_premier"));
				obj.setNombreChevauxInfCinq(rs.getInt("nb_chv_inf_cinq"));
				obj.setNombreChvlFavoriPlace(rs.getInt("nb_chv_fav_place"));
				obj.setNombrePartant(rs.getInt("nb_partant"));
				obj.setNumeroChvlDeuxieme(rs.getInt("num_chv_deuxieme"));
				obj.setNumeroChvlPremier(rs.getInt("num_chv_premier"));
				obj.setNumeroChvlTroisieme(rs.getInt("num_chv_troisieme"));
				obj.setNumeroCourse(rs.getInt("num_course"));
				obj.setNumeroDeuxiemeFavori(rs.getInt("num_fav_deuxieme"));
				obj.setNumeroPremierFavori(rs.getInt("num_fav_premier"));
				obj.setNumeroReunion(rs.getInt("num_reunion"));
				obj.setNumeroTroisiemeFavori(rs.getInt("num_fav_troisieme"));
				obj.setPourcentDeuxiemeFavori(rs.getFloat("pourcent_fav_deuxieme"));
				obj.setPourcentPremierFavori(rs.getFloat("pourcent_fav_premier"));
				obj.setPourcentTroisiemeFavori(rs.getFloat("pourcent_fav_troisieme"));
				obj.setPrime(rs.getString("prime"));
				obj.setRapGagnant(rs.getDouble("rap_gagnant"));
				obj.setRapPlaceDeuxieme(rs.getDouble("rap_place_deuxieme"));
				obj.setRapPlacePremier(rs.getDouble("rap_place_premier"));
				obj.setRapPlaceTroisieme(rs.getDouble("rap_place_troisieme"));
				obj.setTotalPourcent(rs.getFloat("total_pourcent"));
				obj.setTypeCourse(rs.getString("type_course"));				
				obj.setNomChvlPremier(rs.getString("nom_chv_premier"));
				obj.setMusiqueMeilleurGains(rs.getString("musique_meilleur_gain"));
				obj.setNumeroMeilleurGains(rs.getInt("numero_meilleur_gain"));
				
				oobjs.add(obj);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture : "+e.getMessage(), e);
		}
		return oobjs;
	}

	public ArrayList<CourseComplete> findByCriteria(CourseComplete obj) throws DAOException {
		ArrayList<CourseComplete> oobjs = null;
		try{
			String query = qry_findAll +" WHERE ";
			
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getId() != null )
				params.put("id", obj.getId());
			if( obj.getAgeSexChvlPremier() != null )
				params.put("agesexe_chvl_premier", obj.getAgeSexChvlPremier());
			if( obj.getAutoStart() != null )
				params.put("depart", obj.getAutoStart());
			if( obj.getDateCourse() != null )
				params.put("date", obj.getDateCourse());
			if( obj.getHippodrome() != null )
				params.put("hippodrome", obj.getHippodrome());
			if( obj.getMusiquePremier() != null )
				params.put("musique_premier", obj.getMusiquePremier());
			if( obj.getNombreChevauxInfCinq() != null )
				params.put("nb_chv_inf_cinq", obj.getNombreChevauxInfCinq());
			if( obj.getNombreChvlFavoriPlace() != null )
				params.put("nb_chv_fav_place", obj.getNombreChvlFavoriPlace());
			if( obj.getNombrePartant() != null )
				params.put("nb_partant", obj.getNombrePartant());
			if( obj.getNumeroChvlDeuxieme() != null )
				params.put("num_chv_deuxieme", obj.getNumeroChvlDeuxieme());
			if( obj.getNumeroChvlPremier() != null )
				params.put("num_chv_premier", obj.getNumeroChvlPremier());
			if( obj.getNumeroChvlTroisieme() != null )
				params.put("num_chv_troisieme", obj.getNumeroChvlTroisieme());
			if( obj.getNumeroCourse() != null )
				params.put("num_course", obj.getNumeroCourse());
			if( obj.getNumeroDeuxiemeFavori() != null )
				params.put("num_fav_deuxieme", obj.getNumeroDeuxiemeFavori());
			if( obj.getNumeroPremierFavori() != null )
				params.put("num_fav_premier", obj.getNumeroPremierFavori());
			if( obj.getNumeroTroisiemeFavori() != null )
				params.put("num_fav_troisieme", obj.getNumeroTroisiemeFavori());
			if( obj.getNumeroReunion() != null )
				params.put("num_reunion", obj.getNumeroReunion());
			if( obj.getPourcentDeuxiemeFavori() != null )
				params.put("pourcent_fav_deuxieme", obj.getPourcentDeuxiemeFavori());
			if( obj.getPourcentPremierFavori() != null )
				params.put("pourcent_fav_premier", obj.getPourcentPremierFavori());
			if( obj.getPourcentTroisiemeFavori() != null )
				params.put("pourcent_fav_troisieme", obj.getPourcentTroisiemeFavori());
			if( obj.getPrime() != null )
				params.put("prime", obj.getPrime());
			if( obj.getRapGagnant() != null )
				params.put("rap_gagnant", obj.getRapGagnant());
			if( obj.getRapPlaceDeuxieme() != null )
				params.put("rap_place_deuxieme", obj.getRapPlaceDeuxieme());
			if( obj.getRapPlacePremier() != null )
				params.put("rap_place_premier", obj.getRapPlacePremier());
			if( obj.getRapPlaceTroisieme() != null )
				params.put("rap_place_troisieme", obj.getRapPlaceTroisieme());
			if( obj.getTotalPourcent() != null )
				params.put("total_pourcent", obj.getTotalPourcent());
			if( obj.getTypeCourse() != null )
				params.put("type_course", obj.getTypeCourse());
			if( obj.getNomChvlPremier() != null )
				params.put("nom_chv_premier", obj.getNomChvlPremier());
			if( obj.getMusiqueMeilleurGains() != null )
				params.put("musique_meilleur_gain", obj.getMusiqueMeilleurGains());
			if( obj.getNumeroMeilleurGains() != null )
				params.put("numero_meilleur_gain", obj.getNumeroMeilleurGains());

			
			
			
			
			Iterator<Entry<String, Object>> i = params.entrySet().iterator();
			while( i.hasNext() ){
				Entry<String, Object> param = i.next();
				if( param.getValue() instanceof String ){
					String txt = (String)(param.getValue());
					txt = txt.replace("'", "\\'");

					query += param.getKey()+ " LIKE '%"+txt+"%'";
				}else
					query += param.getKey()+ " = "+param.getValue();
				
				if( i.hasNext() )
					query += " AND ";
			}
			
			
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(query);
			
			oobjs = new ArrayList<CourseComplete>();
			while (rs.next()) { 
				CourseComplete oobj = new CourseComplete();
				oobj.setId(rs.getLong("id"));
				oobj.setAgeSexChvlPremier(rs.getString("age_sexe"));
				oobj.setAutoStart(rs.getString("depart"));
				oobj.setDateCourse(rs.getString("date"));
				oobj.setHippodrome(rs.getString("hippodrome"));
				oobj.setMusiquePremier(rs.getString("musique_premier"));
				oobj.setNombreChevauxInfCinq(rs.getInt("nb_chv_inf_cinq"));
				oobj.setNombreChvlFavoriPlace(rs.getInt("nb_chv_fav_place"));
				oobj.setNombrePartant(rs.getInt("nb_partant"));
				oobj.setNumeroChvlDeuxieme(rs.getInt("num_chv_deuxieme"));
				oobj.setNumeroChvlPremier(rs.getInt("num_chv_premier"));
				oobj.setNumeroChvlTroisieme(rs.getInt("num_chv_troisieme"));
				oobj.setNumeroCourse(rs.getInt("num_course"));
				oobj.setNumeroDeuxiemeFavori(rs.getInt("num_fav_deuxieme"));
				oobj.setNumeroPremierFavori(rs.getInt("num_fav_premier"));
				oobj.setNumeroReunion(rs.getInt("num_reunion"));
				oobj.setNumeroTroisiemeFavori(rs.getInt("num_fav_troisieme"));
				oobj.setPourcentDeuxiemeFavori(rs.getFloat("pourcent_fav_deuxieme"));
				oobj.setPourcentPremierFavori(rs.getFloat("pourcent_fav_premier"));
				oobj.setPourcentTroisiemeFavori(rs.getFloat("pourcent_fav_troisieme"));
				oobj.setPrime(rs.getString("prime"));
				oobj.setRapGagnant(rs.getDouble("rap_gagnant"));
				oobj.setRapPlaceDeuxieme(rs.getDouble("rap_place_deuxieme"));
				oobj.setRapPlacePremier(rs.getDouble("rap_place_premier"));
				oobj.setRapPlaceTroisieme(rs.getDouble("rap_place_troisieme"));
				oobj.setTotalPourcent(rs.getFloat("total_pourcent"));
				oobj.setTypeCourse(rs.getString("type_course"));
				oobj.setNomChvlPremier(rs.getString("nom_chv_premier"));
				oobj.setMusiqueMeilleurGains(rs.getString("musique_meilleur_gain"));
				oobj.setNumeroMeilleurGains(rs.getInt("numero_meilleur_gain"));
				
				oobjs.add(oobj);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture : "+e.getMessage(), e);
		} 
		return oobjs;
	}


	
	public CourseComplete create(CourseComplete obj) throws DAOException {
		CourseComplete objReturn = null;
		try {
			
			String queryField = "INSERT INTO " + DAO.t_coursecomplete + " (";
			String queryValue = " VALUES ( ";
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getId() != null )
				params.put("id", obj.getId());
			if( obj.getAgeSexChvlPremier() != null )
				params.put("agesexe_chvl_premier", obj.getAgeSexChvlPremier());
			if( obj.getAutoStart() != null )
				params.put("depart", obj.getAutoStart());
			if( obj.getDateCourse() != null )
				params.put("date", obj.getDateCourse());
			if( obj.getHippodrome() != null )
				params.put("hippodrome", obj.getHippodrome());
			if( obj.getMusiquePremier() != null )
				params.put("musique_premier", obj.getMusiquePremier());
			if( obj.getNombreChevauxInfCinq() != null )
				params.put("nb_chv_inf_cinq", obj.getNombreChevauxInfCinq());
			if( obj.getNombreChvlFavoriPlace() != null )
				params.put("nb_chv_fav_place", obj.getNombreChvlFavoriPlace());
			if( obj.getNombrePartant() != null )
				params.put("nb_partant", obj.getNombrePartant());
			if( obj.getNumeroChvlDeuxieme() != null )
				params.put("num_chv_deuxieme", obj.getNumeroChvlDeuxieme());
			if( obj.getNumeroChvlPremier() != null )
				params.put("num_chv_premier", obj.getNumeroChvlPremier());
			if( obj.getNumeroChvlTroisieme() != null )
				params.put("num_chv_troisieme", obj.getNumeroChvlTroisieme());
			if( obj.getNumeroCourse() != null )
				params.put("num_course", obj.getNumeroCourse());
			if( obj.getNumeroDeuxiemeFavori() != null )
				params.put("num_fav_deuxieme", obj.getNumeroDeuxiemeFavori());
			if( obj.getNumeroPremierFavori() != null )
				params.put("num_fav_premier", obj.getNumeroPremierFavori());
			if( obj.getNumeroTroisiemeFavori() != null )
				params.put("num_fav_troisieme", obj.getNumeroTroisiemeFavori());
			if( obj.getNumeroReunion() != null )
				params.put("num_reunion", obj.getNumeroReunion());
			if( obj.getPourcentDeuxiemeFavori() != null )
				params.put("pourcent_fav_deuxieme", obj.getPourcentDeuxiemeFavori());
			if( obj.getPourcentPremierFavori() != null )
				params.put("pourcent_fav_premier", obj.getPourcentPremierFavori());
			if( obj.getPourcentTroisiemeFavori() != null )
				params.put("pourcent_fav_troisieme", obj.getPourcentTroisiemeFavori());
			if( obj.getPrime() != null )
				params.put("prime", obj.getPrime());
			if( obj.getRapGagnant() != null )
				params.put("rap_gagnant", obj.getRapGagnant());
			if( obj.getRapPlaceDeuxieme() != null )
				params.put("rap_place_deuxieme", obj.getRapPlaceDeuxieme());
			if( obj.getRapPlacePremier() != null )
				params.put("rap_place_premier", obj.getRapPlacePremier());
			if( obj.getRapPlaceTroisieme() != null )
				params.put("rap_place_troisieme", obj.getRapPlaceTroisieme());
			if( obj.getTotalPourcent() != null )
				params.put("total_pourcent", obj.getTotalPourcent());
			if( obj.getTypeCourse() != null )
				params.put("type_course", obj.getTypeCourse());
			if( obj.getNomChvlPremier() != null )
				params.put("nom_chv_premier", obj.getNomChvlPremier());
			if( obj.getMusiqueMeilleurGains() != null )
				params.put("musique_meilleur_gain", obj.getMusiqueMeilleurGains());
			if( obj.getNumeroMeilleurGains() != null )
				params.put("numero_meilleur_gain", obj.getNumeroMeilleurGains());
			
			
			Iterator<Entry<String, Object>> i = params.entrySet().iterator();
			while( i.hasNext() ){
				Entry<String, Object> param = i.next();
				
				Object value = param.getValue();
				if( param.getValue() instanceof String ){
					String txt = (String)(value);
					txt = txt.replace("'", "\\'");
					value = "'"+txt+"'";
				}
				
				queryField += param.getKey();
				queryValue += value;
				
				if( i.hasNext() ){
					queryField += ", ";
					queryValue += ", ";
				}
			}
			
			String query = queryField + " )"+ queryValue+" )";
			
			PreparedStatement prepare = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
		
			prepare.executeUpdate();
			
//			ResultSet rs = prepare.getGeneratedKeys();
//		    rs.next();
//		    long auto_id = rs.getLong(1);
//		    
//		    objReturn = obj;
//			objReturn.setId( auto_id );
		    
		    
		} catch (SQLException e) {
			throw new DAOException("Erreur d'insertion : "+e.getMessage(), e);
		}
		return objReturn;
	}

	public void create(ArrayList<CourseComplete> courseCListe) throws DAOException {

		throw new DAOException("Methode non implémentée");
		

	}


	
	public int delete(long id) throws DAOException {
		int deleted = 1;
		try {
			String query = qry_deleteByID;
		

			PreparedStatement preparedStmt;
		
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setLong(1, id);
			
			
			preparedStmt.execute();
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de suppression de l'id '"+id+"' : "+e.getMessage(), e);
		}
	      
	 
		return deleted;
	}


	
	public CourseComplete update(CourseComplete obj) throws DAOException {
		if( obj.getId() != null ){
			//String query = qry_updateWhere + "id =" + obj.getId();
			String query = "UPDATE "+DAO.t_coursecomplete+" SET ";
			
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getId() != null )
				params.put("id", obj.getId());
			if( obj.getAgeSexChvlPremier() != null )
				params.put("agesexe_chvl_premier", obj.getAgeSexChvlPremier());
			if( obj.getAutoStart() != null )
				params.put("depart", obj.getAutoStart());
			if( obj.getDateCourse() != null )
				params.put("date", obj.getDateCourse());
			if( obj.getHippodrome() != null )
				params.put("hippodrome", obj.getHippodrome());
			if( obj.getMusiquePremier() != null )
				params.put("musique_premier", obj.getMusiquePremier());
			if( obj.getNombreChevauxInfCinq() != null )
				params.put("nb_chv_inf_cinq", obj.getNombreChevauxInfCinq());
			if( obj.getNombreChvlFavoriPlace() != null )
				params.put("nb_chv_fav_place", obj.getNombreChvlFavoriPlace());
			if( obj.getNombrePartant() != null )
				params.put("nb_partant", obj.getNombrePartant());
			if( obj.getNumeroChvlDeuxieme() != null )
				params.put("num_chv_deuxieme", obj.getNumeroChvlDeuxieme());
			if( obj.getNumeroChvlPremier() != null )
				params.put("num_chv_premier", obj.getNumeroChvlPremier());
			if( obj.getNumeroChvlTroisieme() != null )
				params.put("num_chv_troisieme", obj.getNumeroChvlTroisieme());
			if( obj.getNumeroCourse() != null )
				params.put("num_course", obj.getNumeroCourse());
			if( obj.getNumeroDeuxiemeFavori() != null )
				params.put("num_fav_deuxieme", obj.getNumeroDeuxiemeFavori());
			if( obj.getNumeroPremierFavori() != null )
				params.put("num_fav_premier", obj.getNumeroPremierFavori());
			if( obj.getNumeroTroisiemeFavori() != null )
				params.put("num_fav_troisieme", obj.getNumeroTroisiemeFavori());
			if( obj.getNumeroReunion() != null )
				params.put("num_reunion", obj.getNumeroReunion());
			if( obj.getPourcentDeuxiemeFavori() != null )
				params.put("pourcent_fav_deuxieme", obj.getPourcentDeuxiemeFavori());
			if( obj.getPourcentPremierFavori() != null )
				params.put("pourcent_fav_premier", obj.getPourcentPremierFavori());
			if( obj.getPourcentTroisiemeFavori() != null )
				params.put("pourcent_fav_troisieme", obj.getPourcentTroisiemeFavori());
			if( obj.getPrime() != null )
				params.put("prime", obj.getPrime());
			if( obj.getRapGagnant() != null )
				params.put("rap_gagnant", obj.getRapGagnant());
			if( obj.getRapPlaceDeuxieme() != null )
				params.put("rap_place_deuxieme", obj.getRapPlaceDeuxieme());
			if( obj.getRapPlacePremier() != null )
				params.put("rap_place_premier", obj.getRapPlacePremier());
			if( obj.getRapPlaceTroisieme() != null )
				params.put("rap_place_troisieme", obj.getRapPlaceTroisieme());
			if( obj.getTotalPourcent() != null )
				params.put("total_pourcent", obj.getTotalPourcent());
			if( obj.getTypeCourse() != null )
				params.put("type_course", obj.getTypeCourse());
			if( obj.getNomChvlPremier() != null )
				params.put("nom_chv_premier", obj.getNomChvlPremier());
			if( obj.getMusiqueMeilleurGains() != null )
				params.put("musique_meilleur_gain", obj.getMusiqueMeilleurGains());
			if( obj.getNumeroMeilleurGains() != null )
				params.put("numero_meilleur_gain", obj.getNumeroMeilleurGains());
			
			
			
			Iterator<Entry<String, Object>> i = params.entrySet().iterator();
			while( i.hasNext() ){
				Entry<String, Object> param = i.next();
				if( param.getValue() instanceof String ){
					String txt = (String)(param.getValue());
					txt = txt.replace("'", "\\'");

					query += param.getKey()+ " = '"+txt+"'";
				}else
					query += param.getKey()+ " = "+param.getValue();
				
				if( i.hasNext() )
					query += ", ";
			}
			
			query += " WHERE id = "+obj.getId();
			

			
			PreparedStatement preparedStmt;
			
			try {
				preparedStmt = connect.prepareStatement(query);
			
				preparedStmt.execute();
				
			} catch (SQLException e) {
				throw new DAOException("Erreur lors de la mise a jour id '"+obj.getId()+"' : "+e.getMessage(), e);
			}
			
		}else
			return null;

		return obj;
	}

	
	public int delete(CourseComplete obj) throws DAOException {
		int nbDel = 0;
		String queryDel = qry_delete + " WHERE ";
		String queryCount = qry_count + " WHERE ";
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		if( obj.getId() != null )
			params.put("id", obj.getId());
		if( obj.getAgeSexChvlPremier() != null )
			params.put("agesexe_chvl_premier", obj.getAgeSexChvlPremier());
		if( obj.getAutoStart() != null )
			params.put("depart", obj.getAutoStart());
		if( obj.getDateCourse() != null )
			params.put("date", obj.getDateCourse());
		if( obj.getHippodrome() != null )
			params.put("hippodrome", obj.getHippodrome());
		if( obj.getMusiquePremier() != null )
			params.put("musique_premier", obj.getMusiquePremier());
		if( obj.getNombreChevauxInfCinq() != null )
			params.put("nb_chv_inf_cinq", obj.getNombreChevauxInfCinq());
		if( obj.getNombreChvlFavoriPlace() != null )
			params.put("nb_chv_fav_place", obj.getNombreChvlFavoriPlace());
		if( obj.getNombrePartant() != null )
			params.put("nb_partant", obj.getNombrePartant());
		if( obj.getNumeroChvlDeuxieme() != null )
			params.put("num_chv_deuxieme", obj.getNumeroChvlDeuxieme());
		if( obj.getNumeroChvlPremier() != null )
			params.put("num_chv_premier", obj.getNumeroChvlPremier());
		if( obj.getNumeroChvlTroisieme() != null )
			params.put("num_chv_troisieme", obj.getNumeroChvlTroisieme());
		if( obj.getNumeroCourse() != null )
			params.put("num_course", obj.getNumeroCourse());
		if( obj.getNumeroDeuxiemeFavori() != null )
			params.put("num_fav_deuxieme", obj.getNumeroDeuxiemeFavori());
		if( obj.getNumeroPremierFavori() != null )
			params.put("num_fav_premier", obj.getNumeroPremierFavori());
		if( obj.getNumeroTroisiemeFavori() != null )
			params.put("num_fav_troisieme", obj.getNumeroTroisiemeFavori());
		if( obj.getNumeroReunion() != null )
			params.put("num_reunion", obj.getNumeroReunion());
		if( obj.getPourcentDeuxiemeFavori() != null )
			params.put("pourcent_fav_deuxieme", obj.getPourcentDeuxiemeFavori());
		if( obj.getPourcentPremierFavori() != null )
			params.put("pourcent_fav_premier", obj.getPourcentPremierFavori());
		if( obj.getPourcentTroisiemeFavori() != null )
			params.put("pourcent_fav_troisieme", obj.getPourcentTroisiemeFavori());
		if( obj.getPrime() != null )
			params.put("prime", obj.getPrime());
		if( obj.getRapGagnant() != null )
			params.put("rap_gagnant", obj.getRapGagnant());
		if( obj.getRapPlaceDeuxieme() != null )
			params.put("rap_place_deuxieme", obj.getRapPlaceDeuxieme());
		if( obj.getRapPlacePremier() != null )
			params.put("rap_place_premier", obj.getRapPlacePremier());
		if( obj.getRapPlaceTroisieme() != null )
			params.put("rap_place_troisieme", obj.getRapPlaceTroisieme());
		if( obj.getTotalPourcent() != null )
			params.put("total_pourcent", obj.getTotalPourcent());
		if( obj.getTypeCourse() != null )
			params.put("type_course", obj.getTypeCourse());
		if( obj.getNomChvlPremier() != null )
			params.put("nom_chv_premier", obj.getNomChvlPremier());
		if( obj.getMusiqueMeilleurGains() != null )
			params.put("musique_meilleur_gain", obj.getMusiqueMeilleurGains());
		if( obj.getNumeroMeilleurGains() != null )
			params.put("numero_meilleur_gain", obj.getNumeroMeilleurGains());
		
		
		
		
		Iterator<Entry<String, Object>> i = params.entrySet().iterator();
		while( i.hasNext() ){
			Entry<String, Object> param = i.next();
			if( param.getValue() instanceof String ){
				String txt = (String)(param.getValue());
				txt = txt.replace("'", "\\'");
				queryDel += param.getKey()+ " LIKE '%"+txt+"%'";
				queryCount += param.getKey()+ " LIKE '%"+txt+"%'";
			}else{
				queryDel += param.getKey()+ " = "+param.getValue();
				queryCount += param.getKey()+ " = "+param.getValue();
			}
			if( i.hasNext() ){
				queryDel += " AND ";
				queryCount += " AND ";
			}
		}
		
		Statement st;
		try {
			st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
			ResultSet rs = st.executeQuery(queryCount);
			if( rs.first() ){
				nbDel = rs.getInt("nb");
			}
			
			PreparedStatement preparedStmt;
			preparedStmt = connect.prepareStatement(queryDel);
			preparedStmt.execute();
			
		} catch (SQLException e) {
			throw new DAOException("Erreur lors de la suppression : "+e.getMessage(), e);
		}
		
		return nbDel;
	}

}
