package model;

public class AssoM {

private int id_association;
	
	private Conducteur conducteur;
	private VehiculeM vehicule;
	
	public AssoM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssoM(Conducteur conducteur, VehiculeM vehicule) {
		super();
		this.conducteur = conducteur;
		this.vehicule = vehicule;
	}

	public AssoM(int id_association, Conducteur conducteur, VehiculeM vehicule) {
		super();
		this.id_association = id_association;
		this.conducteur = conducteur;
		this.vehicule = vehicule;
	}

	public int getId_association() {
		return id_association;
	}

	public void setId_association(int id_association) {
		this.id_association = id_association;
	}

	public Conducteur getConducteur() {
		return conducteur;
	}

	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}

	public VehiculeM getVehicule() {
		return vehicule;
	}

	public void setVehicule(VehiculeM vehicule) {
		this.vehicule = vehicule;
	}

	@Override
	public String toString() {
		return "Association [id_association=" + id_association + ", conducteur=" + conducteur + ", vehicule=" + vehicule
				+ "]";
	}
}
