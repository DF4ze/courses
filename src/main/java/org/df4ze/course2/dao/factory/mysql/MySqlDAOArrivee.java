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
import org.df4ze.course2.dao.bean.Arrivee;
import org.df4ze.course2.tools.Debug;



public class MySqlDAOArrivee implements DAO<Arrivee> {
	
	
	Connection connect = null;
	//private String qry_insert_app = "INSERT INTO " + DAO.t_rapport + " ( course_id, num_chv, cote, enjeux, arrivee, place, gagnant )"+"VALUES( ?, ?, ?, ?, ?, ?, ? )";
	private String qry_findAll = "SELECT * FROM " + DAO.t_arrivee;
	private String qry_count = "SELECT COUNT(*) as nb FROM " + DAO.t_arrivee;
	private String qry_delete = "DELETE FROM " + DAO.t_arrivee;
	private String qry_deleteByID = qry_delete + " WHERE id = ?";
	//private String qry_updateWhere = "UPDATE " + DAO.t_rapport + " SET course_id=?, num_chv=?, cote=?, enjeux=?, arrivee=?, place=?, gagnant=? WHERE ";

	public MySqlDAOArrivee(  ) {	
		connect = MySqlDAOFactory.createConnection();
	}
	

	public void closeConnection(){
		MySqlDAOFactory.cloreConnexion();
	}
	
	
	public Arrivee findByID(long id) throws DAOException {
		Arrivee oobj = null;
		try{
			oobj = new Arrivee();
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(qry_findAll+" WHERE id = "+id);
			
			if( rs.first() ){
				oobj.setId(rs.getLong("id"));
				oobj.setCourseId(rs.getLong("course_id"));
				oobj.setNumArrivee(rs.getInt("num_arrivee"));
				oobj.setNumChv(rs.getInt("num_chv"));
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture "+e.getMessage(), e);
		} 
		return oobj;
	}

	
	public ArrayList<Arrivee> findAll() throws DAOException {
		ArrayList<Arrivee> oobjs = null;
		try{
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(qry_findAll);
			
			oobjs = new ArrayList<Arrivee>();
			while (rs.next()) { 
				Arrivee obj = new Arrivee();
				obj.setId(rs.getLong("id"));
				obj.setCourseId(rs.getLong("course_id"));
				obj.setNumCheval(rs.getInt("num_chv"));
				obj.setNumArrivee(rs.getInt("num_arrivee"));
				oobjs.add(obj);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture "+e.getMessage(), e);
		}
		return oobjs;
	}

	public ArrayList<Arrivee> findByCriteria(Arrivee obj) throws DAOException {
		ArrayList<Arrivee> oobjs = null;
		try{
			String query = qry_findAll +" WHERE ";
			
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getId() != null )
				params.put("id", obj.getId());
			if( obj.getCourseId() != null )
				params.put("course_id", obj.getCourseId());
			if( obj.getNumCheval() != null )
				params.put("num_chv", obj.getNumCheval());
			if( obj.getNumArrivee() != null )
				params.put("num_arrivee", obj.getNumArrivee());

			
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
			query += " ORDER BY num_arrivee ASC";
			
			
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(query);
			
			oobjs = new ArrayList<Arrivee>();
			while (rs.next()) { 
				Arrivee oobj = new Arrivee();
				oobj.setId(rs.getLong("id"));
				oobj.setCourseId(rs.getLong("course_id"));
				oobj.setNumCheval(rs.getInt("num_chv"));
				oobj.setNumArrivee(rs.getInt("num_arrivee"));
				oobjs.add(oobj);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture "+e.getMessage(), e);
		} 
		return oobjs;
	}


	
	public Arrivee create(Arrivee obj) throws DAOException {
		Arrivee objReturn = null;
		try {
			
			String queryField = "INSERT INTO " + DAO.t_arrivee + " (";
			String queryValue = " VALUES ( ";
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getCourseId() != null )
				params.put("course_id", obj.getCourseId());
			if( obj.getNumCheval() != null )
				params.put("num_chv", obj.getNumCheval());
			if( obj.getNumArrivee() != null )
				params.put("num_arrivee", obj.getNumArrivee());			
			
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
			
			ResultSet rs = prepare.getGeneratedKeys();
		    rs.next();
		    long auto_id = rs.getLong(1);
		    
		    objReturn = obj;
			objReturn.setId( auto_id );
		    
		    
		} catch (SQLException e) {
			throw new DAOException("Erreur d'insertion "+e.getMessage(), e);
		}
		return objReturn;
	}

	public void create(ArrayList<Arrivee> coteListe) throws DAOException {
		String query = "";
		try {
			String queryField = "INSERT INTO " + DAO.t_arrivee + " (course_id, num_chv, num_arrivee)";
			String queryValue = " VALUES ";
			
			int count = 0;
			for( Arrivee cote : coteListe ){
				queryValue += "( ";
				
				queryValue += cote.getCourseId()+", ";
				queryValue += cote.getNumCheval()+", ";
				queryValue += cote.getNumArrivee();
				
				
				queryValue += ") ";
				count++;
				if( count < coteListe.size() )
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


	
	public Arrivee update(Arrivee obj) throws DAOException {
		if( obj.getId() != null ){
			//String query = qry_updateWhere + "id =" + obj.getId();
			String query = "UPDATE "+DAO.t_arrivee+" SET ";
			
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getCourseId() != null )
				params.put("course_id", obj.getCourseId());
			if( obj.getNumCheval() != null )
				params.put("num_chv", obj.getNumCheval());
			if( obj.getNumArrivee() != null )
				params.put("num_arrivee", obj.getNumArrivee());
			
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
				throw new DAOException("Erreur lors de la mise � jour id '"+obj.getId()+"' "+e.getMessage(), e);
			}
			
		}else
			return null;

		return obj;
	}

	
	public int delete(Arrivee obj) throws DAOException {
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
		if( obj.getNumArrivee() != null )
			params.put("num_arrivee", obj.getNumArrivee());
		
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
			throw new DAOException("Erreur lors de la suppression "+e.getMessage(), e);
		}
		
		return nbDel;
	}




}
