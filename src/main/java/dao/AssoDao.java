package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.AssoM;
import model.Conducteur;
import model.VehiculeM;

public class AssoDao implements IDao<AssoM> {
	Connection connect = getConnection.connect();

	@Override
	public boolean create(AssoM object) {
		boolean msg = false;
		try {
			PreparedStatement req = connect.prepareStatement("INSERT INTO association_vehicule_conducteur (id_conducteur,id_vehicule)"
					+ " VALUES (?,?)");
			req.setInt(1, object.getConducteur().getId());
			req.setInt(2, object.getVehicule().getId());
			
			req.executeUpdate();
			
			System.out.println( "l'asso a été bien ajouté en base");
			msg = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Insertion KO" +e.getMessage());
		}
		return msg;
	}
	
	public List<AssoM> read()
	{
		List<AssoM> listeAsso = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM association_vehicule_conducteur as assoc "
					+ "JOIN vehicule as v ON assoc.id_vehicule = v.id "
					+ "JOIN conducteur as c ON c.id = assoc.id_conducteur");
			System.out.println(req);
			
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				Conducteur us = new Conducteur(rs.getInt("id_conducteur"),rs.getString("prenom"), rs.getString("nom"));
				
				VehiculeM vehi = new VehiculeM(rs.getInt("id_vehicule"),rs.getString("marque"),rs.getString("modele"),rs.getString("couleur"),rs.getString("immatriculation"));
				
				AssoM as = new AssoM(rs.getInt("id"),us,vehi);
				
				listeAsso.add(as);
				
			}
			//System.out.println(listeUser);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Insertion KO - KO - KO");
		}
		
		//System.out.println("la lsite" + listeAsso);
		return listeAsso;
	}
	
	public AssoM findById(int id) {
		AssoM assoM = null;
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM association_vehicule_conducteur as assoc "
							+ "JOIN vehicule as v ON assoc.id_vehicule = v.id "
							+ "JOIN conducteur as c ON c.id = assoc.id_conducteur AND assoc.id = ?");
			req.setInt(1, id);
			
			System.out.println(req +"ddd");
			ResultSet rs = req.executeQuery();
			
			if(rs.next()) {
				
				Conducteur us = new Conducteur(rs.getInt("id_conducteur"),rs.getString("prenom"), rs.getString("nom"));
				
				VehiculeM vehi = new VehiculeM(rs.getInt("id_vehicule"),rs.getString("marque"),rs.getString("modele"),rs.getString("couleur"),rs.getString("immatriculation"));
				
				
				assoM =  new AssoM(rs.getInt("id"),us,vehi);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("aaaaa KO - KO - KO" +e.getMessage());
		}
		return assoM;
	}
	
	public boolean deleteAsso(int id) {
		boolean msg = false;
		try {
			PreparedStatement req = connect.prepareStatement("DELETE FROM association_vehicule_conducteur"
					+ " WHERE id=?");
			
			req.setInt(1, id);
			
			req.executeUpdate();
			
			msg = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DELETE KO" + e.getMessage());
		}
		return msg;
	}
}
