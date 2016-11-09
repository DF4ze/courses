package org.df4ze.course2.dao.bean;

public class Cote extends ParentBean {
	
	private Long id;
	private Long courseId;
	private Integer numCheval;
	private Float cote;
	private Float enjeux;

	public Cote() {
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

	public Float getCote() {
		return cote;
	}

	public void setCote(Float cote) {
		this.cote = cote;
	}

	public Float getEnjeux() {
		return enjeux;
	}

	public void setEnjeux(Float enjeux) {
		this.enjeux = enjeux;
	}

}
