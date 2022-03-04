package model;

public class Conducteur {

	// Les attributs
	private int id; // Ici, je ne mets pas id_conducteur, je le ferai dans la vue: coté jsp
	private String prenom;
	private String nom;

	//Le(s) constructeur(s)
	public Conducteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Conducteur(int id, String prenom, String nom) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Conducteur(String prenom, String nom) {
		super();
		this.prenom = prenom;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	/*
	 * Je n'ai pas besoin du setId car il est géré car mysql
	 * public void setId(int id) {
		this.id = id;
	}*/

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	
}
