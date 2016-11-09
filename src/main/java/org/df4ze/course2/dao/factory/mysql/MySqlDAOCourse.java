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
import org.df4ze.course2.dao.bean.Course;
import org.df4ze.course2.tools.Debug;

public class MySqlDAOCourse implements DAO<Course> {
	
	
	Connection connect = null;
	private String qry_insert_app = "INSERT INTO " + DAO.t_course + " ( id, date, hippodrome, reunion, course, prix, type, prime, depart )"+"VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	private String qry_findAll = "SELECT * FROM " + DAO.t_course + " ORDER BY id ASC";
	private String qry_findAllFrom = "SELECT * FROM " + DAO.t_course + " WHERE id >= id_to_be_replaced ORDER BY id ASC";
	private String qry_count = "SELECT COUNT(*) as nb FROM " + DAO.t_course;
	private String qry_delete = "DELETE FROM " + DAO.t_course;
	private String qry_deleteByID = qry_delete + " WHERE id = ?";
	private String qry_updateWhere = "UPDATE " + DAO.t_course + " SET date=?, hippodrome=?, reunion=?, course=?, prix=?, type=?, prime=?, depart=? WHERE ";

	public MySqlDAOCourse(  ) {	
		connect = MySqlDAOFactory.createConnection();
	}
	

	public void closeConnection(){
		MySqlDAOFactory.cloreConnexion();
	}
	
	
	public Course findByID(long id) throws DAOException {
		Course oobj = null;
		try{
			oobj = new Course();
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(qry_findAll+" WHERE id = "+id);
			
			if( rs.first() ){
				oobj.setId(rs.getLong("id"));
				oobj.setDate(rs.getString("date"));
				oobj.setHippodrome(rs.getString("hippodrome"));
				oobj.setReunion(rs.getInt("reunion"));
				oobj.setCourse(rs.getInt("course"));
				oobj.setPrix(rs.getString("prix"));
				oobj.setType(rs.getString("type"));
				oobj.setType(rs.getString("prime"));
				oobj.setDepart(rs.getString("depart"));
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture "+e.getMessage(), e);
		} 
		return oobj;
	}

	
	public ArrayList<Course> findAll() throws DAOException {
		ArrayList<Course> oobjs = null;
		try{
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(qry_findAll);
			
			oobjs = new ArrayList<Course>();
			while (rs.next()) { 
				Course obj = new Course();
				obj.setId(rs.getLong("id"));
				obj.setDate(rs.getString("date"));
				obj.setHippodrome(rs.getString("hippodrome"));
				obj.setReunion(rs.getInt("reunion"));
				obj.setCourse(rs.getInt("course"));
				obj.setPrix(rs.getString("prix"));
				obj.setType(rs.getString("type"));
				obj.setPrime(rs.getString("prime"));
				obj.setDepart(rs.getString("depart"));
				oobjs.add(obj);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture "+e.getMessage(), e);
		}
		return oobjs;
	}

	public ArrayList<Course> findAllFrom( Integer from ) throws DAOException {
		ArrayList<Course> oobjs = null;
		try{
			if( from == null )
				return findAll();
			
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String query = qry_findAllFrom.replace("id_to_be_replaced", from.toString());
			
			ResultSet rs = st.executeQuery(query);
			
			oobjs = new ArrayList<Course>();
			while (rs.next()) { 
				Course obj = new Course();
				obj.setId(rs.getLong("id"));
				obj.setDate(rs.getString("date"));
				obj.setHippodrome(rs.getString("hippodrome"));
				obj.setReunion(rs.getInt("reunion"));
				obj.setCourse(rs.getInt("course"));
				obj.setPrix(rs.getString("prix"));
				obj.setType(rs.getString("type"));
				obj.setPrime(rs.getString("prime"));
				obj.setDepart(rs.getString("depart"));
				oobjs.add(obj);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture "+e.getMessage(), e);
		}
		return oobjs;
	}

	public ArrayList<Course> findByCriteria(Course obj) throws DAOException {
		ArrayList<Course> oobjs = null;
		try{
			String query = qry_findAll +" WHERE ";
			
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			if( obj.getId() != null )
				params.put("id", obj.getId());
			if( obj.getDate() != null )
				params.put("date", obj.getDate());
			if( obj.getHippodrome() != null )
				params.put("hippodrome", obj.getHippodrome());
			if( obj.getReunion() != null )
				params.put("reunion", obj.getReunion());
			if( obj.getCourse() != null )
				params.put("course", obj.getCourse());
			if( obj.getPrix() != null )
				params.put("prix", obj.getPrix());
			if( obj.getType() != null )
				params.put("type", obj.getType());
			if( obj.getPrime() != null )
				params.put("prime", obj.getPrime());
			if( obj.getDepart() != null )
				params.put("depart", obj.getDepart());
			
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
			//System.out.println("FindByCriteria Course : "+query);
			
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(query);
			
			oobjs = new ArrayList<Course>();
			while (rs.next()) { 
				Course oobj = new Course();
				oobj.setId(rs.getLong("id"));
				oobj.setDate(rs.getString("date"));
				oobj.setHippodrome(rs.getString("hippodrome"));
				oobj.setReunion(rs.getInt("reunion"));
				oobj.setCourse(rs.getInt("course"));
				oobj.setPrix(rs.getString("prix"));
				oobj.setType(rs.getString("type"));
				oobj.setPrime(rs.getString("prime"));
				oobj.setDepart(rs.getString("depart"));
				oobjs.add(oobj);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de lecture "+e.getMessage(), e);
		} 
		return oobjs;
	}

	
	public Course create(Course obj) throws DAOException {
		Course objReturn = null;
		try {
			objReturn = new Course();
			// on v�rifie si l'objet n'existerait pas deja
			// En fait on va laisser planter .... juste question d'optimisiation
			// ca evite de lire a chaque fois
//			List<Course> objs = findByCriteria(obj);
//			if( objs.size() == 1 ){
//				objReturn.setId(objs.get(0).getId());
//				objReturn.setDate(objs.get(0).getDate());
//				objReturn.setHippodrome(objs.get(0).getHippodrome());
//				objReturn.setReunion(objs.get(0).getReunion());
//				objReturn.setCourse(objs.get(0).getCourse());
//				objReturn.setPrix(objs.get(0).getPrix());
//				objReturn.setType(objs.get(0).getType());
//				objReturn.setPrime(objs.get(0).getPrime());
//				return objReturn;
//			}
			
			
			PreparedStatement prepare = connect.prepareStatement(qry_insert_app, Statement.RETURN_GENERATED_KEYS);
			//id, date, hippodrome, reunion, course, prix, type, prime, depart
			prepare.setLong( 1, obj.getId() );
			prepare.setString( 2, obj.getDate() );
			prepare.setString( 3, obj.getHippodrome() );
			prepare.setInt( 4, obj.getReunion() );
			prepare.setInt( 5, obj.getCourse() );
			prepare.setString( 6, obj.getPrix() );
			prepare.setString( 7, obj.getType() );
			prepare.setString( 8, obj.getPrime() );
			prepare.setString( 9, obj.getDepart() );
		
			prepare.executeUpdate();
			if( Debug.isEnable() )
				System.out.println("---------------> Insertion avec succes");
			
//			ResultSet rs = prepare.getGeneratedKeys();
//		    rs.next();
//		    long auto_id = rs.getLong(1);
		    
			objReturn.setId(obj.getId());
			objReturn.setDate(obj.getDate());
			objReturn.setHippodrome(obj.getHippodrome());
			objReturn.setReunion(obj.getReunion());
			objReturn.setCourse(obj.getCourse());
			objReturn.setPrix(obj.getPrix());
			objReturn.setType(obj.getType());
			objReturn.setPrime(obj.getPrime());
			objReturn.setDepart(obj.getDepart());
//		    objReturn.setId( auto_id );
		    
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return objReturn;
	}

	public void create(ArrayList<Course> coursesListe) throws DAOException {
		try {
			String queryField = "INSERT INTO " + DAO.t_course + " ( id, date, hippodrome, reunion, course, prix, type, prime, depart )";
			String queryValue = " VALUES ";
			
			int count = 0;
			for( Course course : coursesListe ){
				queryValue += "( ";
				
				queryValue += course.getId()+", ";
				queryValue += "'"+course.getDate()+"', ";
				queryValue += "'"+course.getHippodrome()+"', ";
				queryValue += "'"+course.getReunion()+"', ";
				queryValue += course.getCourse()+", ";
				queryValue += "'"+course.getPrix()+"', ";
				queryValue += "'"+course.getType()+"', ";
				queryValue += course.getPrime()+", ";				
				queryValue += "'"+course.getDepart()+"'";				
				
				queryValue += ") ";
				count++;
				if( count < coursesListe.size() )
					queryValue += ", ";
			}
			
			String query = queryField +  queryValue;
			PreparedStatement prepare = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			prepare.executeUpdate();
			
			if( Debug.isEnable() )
				System.out.println("---------------> Insertion avec succes");
			
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}

	}

	
	public int delete(long id) throws DAOException {
		int deleted = 1;
		try {
			String query = qry_deleteByID;
		
			PreparedStatement preparedStmt;
		
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setLong(1, id);
			if( Debug.isEnable() )
				System.out.println("delete course : "+query);
			
			preparedStmt.execute();
			
		} catch (SQLException e) {
			throw new DAOException("Erreur de suppression de l'id '"+id+"' "+e.getMessage(), e);
		}
	      
	 
		return deleted;
	}


	
	public Course update(Course obj) throws DAOException {
		if( obj.getId() != null ){
			String query = qry_updateWhere + "id =" + obj.getId();
			
			PreparedStatement preparedStmt;
			
			try {
				preparedStmt = connect.prepareStatement(query);
			
				//"SET date=?, hippodrome=?, reunion=?, course=?, prix=?, type=? "
				preparedStmt.setString(1, obj.getDate());
				preparedStmt.setString(2, obj.getHippodrome());
				preparedStmt.setInt(3, obj.getReunion());
				preparedStmt.setInt(4, obj.getCourse());
				preparedStmt.setString(5, obj.getPrix());
				preparedStmt.setString(6, obj.getType());
				preparedStmt.setString(7, obj.getPrime());
				preparedStmt.setString(8, obj.getDepart());
				
				if( Debug.isEnable() )
					System.out.println("update course : "+query);
				
				preparedStmt.execute();
				
			} catch (SQLException e) {
				throw new DAOException("Erreur lors de la mise � jour id '"+obj.getId()+"' "+e.getMessage(), e);
			}
		}

		return obj;
	}

	
	public int delete(Course obj) throws DAOException {
		int nbDel = 0;
		String queryDel = qry_delete + " WHERE ";
		String queryCount = qry_count + " WHERE ";
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		if( obj.getId() != null )
			params.put("id", obj.getId());
		if( obj.getDate() != null )
			params.put("date", obj.getDate());
		if( obj.getHippodrome() != null )
			params.put("hippodrome", obj.getHippodrome());
		if( obj.getReunion() != null )
			params.put("reunion", obj.getReunion());
		if( obj.getCourse() != null )
			params.put("course", obj.getCourse());
		if( obj.getPrix() != null )
			params.put("prix", obj.getPrix());
		if( obj.getType() != null )
			params.put("type", obj.getType());
		if( obj.getPrime() != null )
			params.put("prime", obj.getPrime());
		if( obj.getDepart() != null )
			params.put("depart", obj.getDepart());
		
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
