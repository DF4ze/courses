package org.df4ze.course2.dao.bean;

public class Rapport extends ParentBean{

	private Long id;
	private Long courseId;
	private Integer numCheval;
	private Integer arrivee;
	private Double place;
	private Double gagnant;
	
	
	public Rapport() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCourseId() {
		return courseId;
	}


	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}


	public Integer getNumCheval() {
		return numCheval;
	}


	public void setNumCheval(Integer numCheval) {
		this.numCheval = numCheval;
	}



	public Integer getArrivee() {
		return arrivee;
	}


	public void setArrivee(Integer arrivee) {
		this.arrivee = arrivee;
	}


	public Double getPlace() {
		return place;
	}


	public void setPlace(Double place) {
		this.place = place;
	}


	public Double getGagnant() {
		return gagnant;
	}


	public void setGagnant(Double gagnant) {
		this.gagnant = gagnant;
	}

}
