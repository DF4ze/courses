package org.df4ze.course2.dao.bean;

public class Course extends ParentBean {
	
	private Long id;
	private String date;
	private String hippodrome;
	private Integer reunion;
	private Integer course;
	private String prix;
	private String type;
	private String prime;
	private String depart;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHippodrome() {
		return hippodrome;
	}

	public void setHippodrome(String hippodrome) {
		this.hippodrome = hippodrome;
	}

	public Integer getReunion() {
		return reunion;
	}

	public void setReunion(Integer reunion) {
		this.reunion = reunion;
	}

	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrime() {
		return prime;
	}

	public void setPrime(String prime) {
		this.prime = prime;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

}
