package org.df4ze.course2.dao.bean;

public class CourseComplete extends ParentBean{

	private Long 		id;
	private Integer 	nombrePartant;
	private Integer 	nombreChevauxInfCinq;
	private Double 		rapGagnant;
	private Double 		rapPlacePremier;
	private Double 		rapPlaceDeuxieme;
	private Double 		rapPlaceTroisieme;
	private Integer 	numeroChvlPremier;
	private Integer 	numeroChvlDeuxieme;
	private Integer 	numeroChvlTroisieme;
	private Float 		totalPourcent;
	private Float 		pourcentPremierFavori;
	private Integer 	numeroPremierFavori;
	private Float 		pourcentDeuxiemeFavori;
	private Integer 	numeroDeuxiemeFavori;
	private Float 		pourcentTroisiemeFavori;
	private Integer 	numeroTroisiemeFavori;
	private Integer 	nombreChvlFavoriPlace;
	private String 		dateCourse;
	private Integer 	numeroCourse;
	private Integer 	numeroReunion;
	private String 		hippodrome;
	private String 		prime;
	private String 		typeCourse;
	private String 		ageSexChvlPremier;
	private String 		autoStart;
	private String 		musiquePremier;
	private String 		nomChvlPremier;
	private String 		musiqueMeilleurGains;
	private Integer		numeroMeilleurGains;
	
	public CourseComplete() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNombrePartant() {
		return nombrePartant;
	}

	public void setNombrePartant(Integer nombrePartant) {
		this.nombrePartant = nombrePartant;
	}

	public Integer getNombreChevauxInfCinq() {
		return nombreChevauxInfCinq;
	}

	public void setNombreChevauxInfCinq(Integer nombreChevauxInfCinq) {
		this.nombreChevauxInfCinq = nombreChevauxInfCinq;
	}

	public Double getRapGagnant() {
		return rapGagnant;
	}

	public void setRapGagnant(Double rapGagnant) {
		this.rapGagnant = rapGagnant;
	}

	public Double getRapPlaceDeuxieme() {
		return rapPlaceDeuxieme;
	}

	public void setRapPlaceDeuxieme(Double rapPlaceDeuxieme) {
		this.rapPlaceDeuxieme = rapPlaceDeuxieme;
	}

	public Double getRapPlaceTroisieme() {
		return rapPlaceTroisieme;
	}

	public void setRapPlaceTroisieme(Double rapPlaceTroisieme) {
		this.rapPlaceTroisieme = rapPlaceTroisieme;
	}

	public Integer getNumeroChvlPremier() {
		return numeroChvlPremier;
	}

	public void setNumeroChvlPremier(Integer numeroChvlPremier) {
		this.numeroChvlPremier = numeroChvlPremier;
	}

	public Integer getNumeroChvlDeuxieme() {
		return numeroChvlDeuxieme;
	}

	public void setNumeroChvlDeuxieme(Integer numeroChvlDeuxieme) {
		this.numeroChvlDeuxieme = numeroChvlDeuxieme;
	}

	public Integer getNumeroChvlTroisieme() {
		return numeroChvlTroisieme;
	}

	public void setNumeroChvlTroisieme(Integer numeroChvlTroisieme) {
		this.numeroChvlTroisieme = numeroChvlTroisieme;
	}

	public Float getTotalPourcent() {
		return totalPourcent;
	}

	public void setTotalPourcent(Float totalPourcent) {
		this.totalPourcent = totalPourcent;
	}

	public Float getPourcentPremierFavori() {
		return pourcentPremierFavori;
	}

	public void setPourcentPremierFavori(Float pourcentPremierFavori) {
		this.pourcentPremierFavori = pourcentPremierFavori;
	}

	public Integer getNumeroPremierFavori() {
		return numeroPremierFavori;
	}

	public void setNumeroPremierFavori(Integer numeroPremierFavori) {
		this.numeroPremierFavori = numeroPremierFavori;
	}

	public Float getPourcentDeuxiemeFavori() {
		return pourcentDeuxiemeFavori;
	}

	public void setPourcentDeuxiemeFavori(Float pourcentDeuxiemeFavori) {
		this.pourcentDeuxiemeFavori = pourcentDeuxiemeFavori;
	}

	public Integer getNumeroDeuxiemeFavori() {
		return numeroDeuxiemeFavori;
	}

	public void setNumeroDeuxiemeFavori(Integer numeroDeuxiemeFavori) {
		this.numeroDeuxiemeFavori = numeroDeuxiemeFavori;
	}

	public Float getPourcentTroisiemeFavori() {
		return pourcentTroisiemeFavori;
	}

	public void setPourcentTroisiemeFavori(Float pourcentTroisiemeFavori) {
		this.pourcentTroisiemeFavori = pourcentTroisiemeFavori;
	}

	public Integer getNumeroTroisiemeFavori() {
		return numeroTroisiemeFavori;
	}

	public void setNumeroTroisiemeFavori(Integer numeroTroisiemeFavori) {
		this.numeroTroisiemeFavori = numeroTroisiemeFavori;
	}

	public Integer getNombreChvlFavoriPlace() {
		return nombreChvlFavoriPlace;
	}

	public void setNombreChvlFavoriPlace(Integer nombreChvlFavoriPlace) {
		this.nombreChvlFavoriPlace = nombreChvlFavoriPlace;
	}

	public String getDateCourse() {
		return dateCourse;
	}

	public void setDateCourse(String dateCourse) {
		this.dateCourse = dateCourse;
	}

	public Integer getNumeroCourse() {
		return numeroCourse;
	}

	public void setNumeroCourse(Integer numeroCourse) {
		this.numeroCourse = numeroCourse;
	}

	public Integer getNumeroReunion() {
		return numeroReunion;
	}

	public void setNumeroReunion(Integer numeroReunion) {
		this.numeroReunion = numeroReunion;
	}

	public String getHippodrome() {
		return hippodrome;
	}

	public void setHippodrome(String hippodrome) {
		this.hippodrome = hippodrome;
	}

	public String getTypeCourse() {
		return typeCourse;
	}

	public void setTypeCourse(String typeCourse) {
		this.typeCourse = typeCourse;
	}

	public String getAgeSexChvlPremier() {
		return ageSexChvlPremier;
	}

	public void setAgeSexChvlPremier(String ageSexChvl) {
		this.ageSexChvlPremier = ageSexChvl;
	}

	public String getAutoStart() {
		return autoStart;
	}

	public void setAutoStart(String autoStart) {
		this.autoStart = autoStart;
	}

	public String getMusiquePremier() {
		return musiquePremier;
	}

	public void setMusiquePremier(String musiquePremier) {
		this.musiquePremier = musiquePremier;
	}

	public String getPrime() {
		return prime;
	}

	public void setPrime(String prime) {
		this.prime = prime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getRapPlacePremier() {
		return rapPlacePremier;
	}

	public void setRapPlacePremier(Double rapPlacePremier) {
		this.rapPlacePremier = rapPlacePremier;
	}

	public String getNomChvlPremier() {
		return nomChvlPremier;
	}

	public void setNomChvlPremier(String nomChvlPremier) {
		this.nomChvlPremier = nomChvlPremier;
	}


	public String getMusiqueMeilleurGains() {
		return musiqueMeilleurGains;
	}

	public void setMusiqueMeilleurGains(String musiqueMeilleurGains) {
		this.musiqueMeilleurGains = musiqueMeilleurGains;
	}

	public Integer getNumeroMeilleurGains() {
		return numeroMeilleurGains;
	}

	public void setNumeroMeilleurGains(int numeroMeilleurGains) {
		this.numeroMeilleurGains = numeroMeilleurGains;
	}

}
