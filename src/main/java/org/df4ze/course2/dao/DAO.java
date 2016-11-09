package org.df4ze.course2.dao;

import java.util.ArrayList;

public interface DAO<T> {
	
	public static final String t_course = "course";
	public static final String t_rapport = "rapport";
	public static final String t_cote = "cote";
	public static final String t_partant = "partant";
	public static final String t_coursecomplete = "coursecomplete";
	
	
	/**
	* Permet de r�cup�rer un objet via son ID
	* @param id
	* @return
	*/
	public abstract T findByID(long id)throws DAOException;
	
	/**
	* Permet de r�cup�rer tous les objets
	* @param id
	* @return
	*/
	public abstract ArrayList<T> findAll()throws DAOException;
	
	/**
	* Permet de r�cup�rer une liste d'objets via des crit�res
	* @param id
	* @return
	*/
	public abstract ArrayList<T> findByCriteria(T obj) throws DAOException;
	
	/**
	* Permet de cr�er une entr�e dans la base de donn�es
	* par rapport � un objet
	* @param obj
	*/
	public abstract T create(T obj) throws DAOException;
	public abstract void create(ArrayList<T> liste) throws DAOException;	
	/**
	* Permet de mettre � jour les donn�es d'une entr�e dans la base
	* @param obj
	*/
	public abstract T update(T obj) throws DAOException;
	
	/**
	* Permet la suppression d'une entr�e de la base � partir de l'objet m�tier
	* @param obj
	*/
	public abstract int delete(T obj) throws DAOException;
	
	/**
	* Permet la suppression d'une entr�e de la base � partir de 
	* l'identifiant de l'objet m�tier
	* @param obj
	*/
	public abstract int delete(long id) throws DAOException;
	
	/**
	 * Permet de close la connexion apres "chaque" requete car probleme de TIMEOUT...
	 */
	public void closeConnection();
}