package org.df4ze.course2.dao.bean;

public class Arrivee extends ParentBean {

	public Long id;
	public Long CourseId;

	public Integer numArrivee;
	public Integer numChv;
	public String nomChv;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCourseId() {
		return CourseId;
	}
	public void setCourseId(Long courseId) {
		CourseId = courseId;
	}
	public Integer getNumArrivee() {
		return numArrivee;
	}
	public void setNumArrivee(Integer numArrivee) {
		this.numArrivee = numArrivee;
	}
	public Integer getNumChv() {
		return numChv;
	}
	public void setNumChv(Integer numChv) {
		this.numChv = numChv;
	}
	public String getNomChv() {
		return nomChv;
	}
	public void setNomChv(String nomChv) {
		this.nomChv = nomChv;
	}
	public void setNumCheval(Integer numChv) {
		this.numChv = numChv;
	}
	public String getNumCheval() {
		return nomChv;
	} 
	
	

}
