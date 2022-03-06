package model;

public class VehiculeM {
	
	//Les attributs 
		private int id;
		private String marque;
		private String modele;
		private String immatriculation;
		private String couleur;
		
		public VehiculeM() {
			super();
			// TODO Auto-generated constructor stub
		}
		public VehiculeM(String marque, String modele, String immatriculation, String couleur) {
			
			this.marque = marque;
			this.modele = modele;
			this.immatriculation = immatriculation;
			this.couleur = couleur;
		}
		
		
		public VehiculeM(int id, String marque, String modele, String immatriculation, String couleur) {
			super();
			this.id = id;
			this.marque = marque;
			this.modele = modele;
			this.immatriculation = immatriculation;
			this.couleur = couleur;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getMarque() {
			return marque;
		}
		public void setMarque(String marque) {
			this.marque = marque;
		}
		public String getModele() {
			return modele;
		}
		public void setModele(String modele) {
			this.modele = modele;
		}
		public String getImmatriculation() {
			return immatriculation;
		}
		public void setImmatriculation(String immatriculation) {
			this.immatriculation = immatriculation;
		}
		public String getCouleur() {
			return couleur;
		}
		public void setCouleur(String couleur) {
			this.couleur = couleur;
		}
		@Override
		public String toString() {
			return "Vehicule [id=" + id + ", marque=" + marque + ", modele=" + modele + ", immatriculation="
					+ immatriculation + ", couleur=" + couleur + "]";
		}
}
