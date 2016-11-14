package org.df4ze.course2.dao.bean;

public class Partant extends ParentBean implements Cloneable{

	private Long id;
	private Long courseId;
	private Integer numCheval;
	private String nomCheval;
	private String ageSexe;
	private String musique;
	private String gains;
	private Integer iGains;
	
	
	public Partant() {
		// TODO Auto-generated constructor stub
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


	public String getNomCheval() {
		return nomCheval;
	}


	public void setNomCheval(String nomCheval) {
		this.nomCheval = nomCheval;
	}


	public String getAgeSexe() {
		return ageSexe;
	}


	public void setAgeSexe(String ageSexe) {
		this.ageSexe = ageSexe;
	}


	public String getMusique() {
		return musique;
	}


	public void setMusique(String musique) {
		this.musique = musique;
	}





	public void setGains(String legains) {
		this.gains = legains;
		
		try{
			if( gains != null ){
				gains = gains.replace(" ", "");
	
				this.iGains = Integer.parseInt(gains);
			}
		}catch( Exception e ){
			;
		}

	}


	public Integer getiGains() {
		return iGains;
	}


	public void setiGains(Integer iGains) {
		this.iGains = iGains;
	}
	
	public Partant clone() {
		Partant o = new Partant();

		o.ageSexe = ageSexe;
		o.courseId = courseId.longValue();
		o.gains = gains;
		o.id = id.longValue();
		o.iGains = iGains.intValue();
		o.musique = musique;
		o.nomCheval = nomCheval;
		o.numCheval = numCheval.intValue();
		
		
		// on renvoie le clone
		return o;
	}
}
