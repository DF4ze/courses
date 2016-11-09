package org.df4ze.course2.dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.df4ze.course2.dao.DAO;
import org.df4ze.course2.dao.DAOException;
import org.df4ze.course2.dao.bean.Rapport;
import org.df4ze.course2.tools.Debug;

public class MySqlDAORapport implements DAO<Rapport> {
	
	
	Connection connect = null;
	//private String qry_insert_app = "INSERT INTO " + DAO.t_rapport + " ( course_id, num_chv, cote, enjeux, arrivee, place, gagnant )"+"VALUES( ?, ?, ?, ?, ?, ?, ? )";
	private String qry_findAll = "SELECT * FROM " + DAO.t_rapport;
	private String qry_count = "SELECT COUNT(*) as nb FROM " + DAO.t_rapport;
	private String qry_delete = "DELETE FROM " + DAO.t_rapport;
	private String qry_deleteByID = qry_delete + " WHERE id = ?";
	//private String qry_updateWhere = "UPDATE " + DAO.t_rapport + " SET course_id=?, num_chv=?, cote=?, enjeux=?, arrivee=?, place=?, gagnant=? WHERE ";

	public MySqlDAORapport(  ) {	
		connect = MySqlDAOFactory.createConnection();
	}
	

	public void closeConnection(){
		MySqlDAOFactory.cloreConnexion();
	}
	
	
	public Rapport findByID(long id) throws DAOException {
		Rapport oobj = null;
		try{
			oobj = new Rapport();
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(qry_findAll+" WHERE id = "+id);
			
			if( rs.first() ){
				oobj.setId(rs.getLong("id"));
				oobj.setCourseId(rs.getLong("course_id"));
				oobj.setNumCheval(rs.getInt("num_chv"));
//				oobj.setCote(rs.getFloat("cote"));
//				oobj.setEnjeux(rs.getFloat("enjeux"));
				oobj.setArrivee(rs.getInt("arrivee"));
				oobj.setPlace(rs.getDouble("place"));
				oobj.setGagnant(rs.getDouble("gagnant"));
			}
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} 
		return oobj;
	}

	
	public ArrayList<Rapport> findAll() throws DAOException {
		ArrayList<Rapport> oobjs = null;
		try{
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(qry_findAll);
			
			oobjs = new ArrayList<Rapport>();
			while (rs.next()) { 
				Rapport obj = new Rapport();
				obj.setId(rs.getLong("id"));
				obj.setCourseId(rs.getLong("course_id"));
				obj.setNumCheval(rs.getInt("num_chv"));
//				obj.setCote(rs.getFloat("cote"));
//				obj.setEnjeux(rs.getFloat("enjeux"));
				obj.setArrivee(rs.getInt("arrivee"));
				obj.setPlace(rs.getDouble("place"));
				obj.setGagnant(rs.getDouble("gagnant"));
				oobjs.add(obj);
			}
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return oobjs;
	}

	public ArrayList<Rapport> findByCriteria(Rapport obj) throws DAOException {
		ArrayList<Rapport> oobjs = null;
		try{
			String query = qry_findAll +" WHERE ";
			
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getId() != null )
				params.put("id", obj.getId());
			if( obj.getCourseId() != null )
				params.put("course_id", obj.getCourseId());
			if( obj.getNumCheval() != null )
				params.put("num_chv", obj.getNumCheval());
//			if( obj.getCote() != null )
//				params.put("cote", obj.getCote());
//			if( obj.getEnjeux() != null )
//				params.put("enjeux", obj.getEnjeux());
			if( obj.getArrivee() != null )
				params.put("arrivee", obj.getArrivee());
			if( obj.getPlace() != null )
				params.put("place", obj.getPlace());
			if( obj.getGagnant() != null )
				params.put("gagnant", obj.getGagnant());
			
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
			
			oobjs = new ArrayList<Rapport>();
			while (rs.next()) { 
				Rapport oobj = new Rapport();
				oobj.setId(rs.getLong("id"));
				oobj.setCourseId(rs.getLong("course_id"));
				oobj.setNumCheval(rs.getInt("num_chv"));
//				oobj.setCote(rs.getFloat("cote"));
//				oobj.setEnjeux(rs.getFloat("enjeux"));
				oobj.setArrivee(rs.getInt("arrivee"));
				oobj.setPlace(rs.getDouble("place"));
				oobj.setGagnant(rs.getDouble("gagnant"));
				oobjs.add(oobj);
			}
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} 
		return oobjs;
	}
	public List<Rapport> findPair(Rapport obj) throws DAOException {
		ArrayList<Rapport> oobjs = null;
		try{
			String query = qry_findAll +" WHERE ";
			
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			//if( obj.getId() != null )
			//	params.put("id", obj.getId());
			if( obj.getCourseId() != null )
				params.put("course_id", obj.getCourseId());
			if( obj.getNumCheval() != null )
				params.put("num_chv", obj.getNumCheval());
			/*if( obj.getCote() != null )
				params.put("cote", obj.getCote());
			if( obj.getEnjeux() != null )
				params.put("enjeux", obj.getEnjeux());
			if( obj.getArrivee() != null )
				params.put("arrivee", obj.getArrivee());
			if( obj.getPlace() != null )
				params.put("place", obj.getPlace());
			if( obj.getGagnant() != null )
				params.put("gagnant", obj.getGagnant());
			*/
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
			
			oobjs = new ArrayList<Rapport>();
			while (rs.next()) { 
				Rapport oobj = new Rapport();
				oobj.setId(rs.getLong("id"));
				oobj.setCourseId(rs.getLong("course_id"));
				oobj.setNumCheval(rs.getInt("num_chv"));
//				oobj.setCote(rs.getFloat("cote"));
//				oobj.setEnjeux(rs.getFloat("enjeux"));
				oobj.setArrivee(rs.getInt("arrivee"));
				oobj.setPlace(rs.getDouble("place"));
				oobj.setGagnant(rs.getDouble("gagnant"));
				oobjs.add(oobj);
			}
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} 
		return oobjs;
	}

	
	public Rapport create(Rapport obj) throws DAOException {
		Rapport objReturn = null;
		try {
			//objReturn = new Rapport();
			// on v�rifie si l'objet n'existerait pas deja
//			List<Rapport> objs = findPair(obj);
		//	System.out.println("objs size : "+objs.size());
//			if( objs.size() == 1 ){
//				objReturn.setId(objs.get(0).getId());
//				objReturn.setCourseId(objs.get(0).getCourseId());
//				objReturn.setNumCheval(objs.get(0).getNumCheval());
//				objReturn.setCote(objs.get(0).getCote());
//				objReturn.setEnjeux(objs.get(0).getEnjeux());
//				objReturn.setArrivee(objs.get(0).getArrivee());
//				objReturn.setPlace(objs.get(0).getPlace());
//				objReturn.setGagnant(objs.get(0).getGagnant());
				
//				obj.setId(objs.get(0).getId());
//				objReturn = update(obj);
//				return objReturn;
//			}
			
			String queryField = "INSERT INTO " + DAO.t_rapport + " (";
			String queryValue = " VALUES ( ";
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getCourseId() != null )
				params.put("course_id", obj.getCourseId());
			if( obj.getNumCheval() != null )
				params.put("num_chv", obj.getNumCheval());
//			if( obj.getCote() != null )
//				params.put("cote", obj.getCote());
//			if( obj.getEnjeux() != null )
//				params.put("enjeux", obj.getEnjeux());
			if( obj.getArrivee() != null )
				params.put("arrivee", obj.getArrivee());
			if( obj.getPlace() != null )
				params.put("place", obj.getPlace());
			if( obj.getGagnant() != null )
				params.put("gagnant", obj.getGagnant());
			
			//"INSERT INTO " + DAO.t_rapport + " ( course_id, num_chv, cote, enjeux, arrivee, place, gagnant )"+"VALUES( ?, ?, ?, ?, ?, ?, ? )";
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
			//System.out.println("query Insert : " + query);
			
			PreparedStatement prepare = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
//			prepare.setLong( 1, obj.getCourseId() );
//			prepare.setInt( 2, obj.getNumCheval() );
//			prepare.setFloat( 3, obj.getCote());
//			prepare.setFloat( 4, obj.getEnjeux() );
//			prepare.setInt( 5, obj.getArrivee() );
//			prepare.setFloat( 6, obj.getPlace() );
//			prepare.setFloat( 7, obj.getGagnant() );
		
			prepare.executeUpdate();
			
			if( Debug.isEnable() )
				System.out.println("---------------> Insertion avec succes");

			ResultSet rs = prepare.getGeneratedKeys();
		    rs.next();
		    long auto_id = rs.getLong(1);
		    
		    objReturn = obj;
			objReturn.setId( auto_id );
		    
		    
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return objReturn;
	}

	public void create(ArrayList<Rapport> rapportsListe) throws DAOException {
		String query = "";
		try {
			String queryField = "INSERT INTO " + DAO.t_rapport + " (course_id, num_chv, arrivee, place, gagnant)";
			String queryValue = " VALUES ";
			
			int count = 0;
			for( Rapport rapport : rapportsListe ){
				queryValue += "( ";
				
				queryValue += rapport.getCourseId()+", ";
				queryValue += rapport.getNumCheval()+", ";
				queryValue += rapport.getArrivee()+", ";
				queryValue += rapport.getPlace()+", ";
				queryValue += rapport.getGagnant();				
				
				queryValue += " ) ";
				count++;
				if( count < rapportsListe.size() )
					queryValue += ", ";
			}
			
			query = queryField +  queryValue;
			PreparedStatement prepare = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			prepare.executeUpdate();
			
			
			if( Debug.isEnable() )
				System.out.println("---------------> Insertion avec succes");
			
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e, query);
		}

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
			throw new DAOException("Erreur de suppression de l'id '"+id+"' "+e.getMessage(), e);
		}
	      
	 
		return deleted;
	}


	
	public Rapport update(Rapport obj) throws DAOException {
		if( obj.getId() != null ){
			//String query = qry_updateWhere + "id =" + obj.getId();
			String query = "UPDATE "+DAO.t_rapport+" SET ";
			
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getCourseId() != null )
				params.put("course_id", obj.getCourseId());
			if( obj.getNumCheval() != null )
				params.put("num_chv", obj.getNumCheval());
//			if( obj.getCote() != null )
//				params.put("cote", obj.getCote());
//			if( obj.getEnjeux() != null )
//				params.put("enjeux", obj.getEnjeux());
			if( obj.getArrivee() != null )
				params.put("arrivee", obj.getArrivee());
			if( obj.getPlace() != null )
				params.put("place", obj.getPlace());
			if( obj.getGagnant() != null )
				params.put("gagnant", obj.getGagnant());
			
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
			
				//"SET date=?, hippodrome=?, reunion=?, course=?, prix=?, type=? "
//				preparedStmt.setLong( 1, obj.getCourseId() );
//				preparedStmt.setInt( 2, obj.getNumCheval() );
//				preparedStmt.setFloat( 3, obj.getCote());
//				preparedStmt.setFloat( 4, obj.getEnjeux() );
//				preparedStmt.setInt( 5, obj.getArrivee() );
//				preparedStmt.setFloat( 6, obj.getPlace() );
//				preparedStmt.setFloat( 7, obj.getGagnant() );
				
			//	System.out.println("UPDATE Rapport : "+query);
				preparedStmt.execute();
				
			} catch (SQLException e) {
				throw new DAOException("Erreur lors de la mise � jour id '"+obj.getId()+"' "+e.getMessage(), e);
			}
			
		}else
			return null;

		return obj;
	}

	
	public int delete(Rapport obj) throws DAOException {
		int nbDel = 0;
		String queryDel = qry_delete + " WHERE ";
		String queryCount = qry_count + " WHERE ";
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		if( obj.getId() != null )
			params.put("id", obj.getId());
		if( obj.getCourseId() != null )
			params.put("course_id", obj.getCourseId());
		if( obj.getNumCheval() != null )
			params.put("num_chv", obj.getNumCheval());
//		if( obj.getCote() != null )
//			params.put("cote", obj.getCote());
//		if( obj.getEnjeux() != null )
//			params.put("enjeux", obj.getEnjeux());
		if( obj.getArrivee() != null )
			params.put("arrivee", obj.getArrivee());
		if( obj.getPlace() != null )
			params.put("place", obj.getPlace());
		if( obj.getGagnant() != null )
			params.put("gagnant", obj.getGagnant());
		
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
			throw new DAOException(e.getMessage(), e);
		}
		
		return nbDel;
	}

}
