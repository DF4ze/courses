package org.df4ze.course2.dao.bean;

import java.util.ArrayList;

public class BeansList {

	private ArrayList<ParentBean> listeBeans = new ArrayList<ParentBean>();

	public BeansList() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void add( ParentBean bean ){
		listeBeans.add(bean);
	}
	
	public void addAll( ArrayList<ParentBean> liste ){
		listeBeans.addAll(liste);
	}
	
	public void addAll( BeansList liste ){
		listeBeans.addAll(liste.get());
	}
	
	public ParentBean get( int i ){
		return listeBeans.get(i);
	}
	
	public void set( ArrayList<ParentBean> liste ){
		listeBeans = liste;
	}
	
	public ArrayList<ParentBean> get(){
		return listeBeans;
	}
}
