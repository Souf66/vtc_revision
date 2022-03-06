package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.VehiculeM;


public class VehiculeDao implements IDao<VehiculeM>{

	Connection connect = getConnection.connect();

	@Override
	public boolean create(VehiculeM object) {
		boolean msg = false;
		try {
			PreparedStatement req = connect.prepareStatement("INSERT INTO vehicule (marque,modele,immatriculation,couleur)"
					+ " VALUES (?,?,?,?)");
			
			req.setString(1, object.getMarque());
			req.setString(2, object.getModele());
			req.setString(3, object.getImmatriculation());
			req.setString(4, object.getCouleur());
			System.out.println(req);
			req.executeUpdate();
			
			msg = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Insertion KO" + e.getMessage());
		}
		
		return msg;
	}
	
	public List<VehiculeM> read() {
		List<VehiculeM> listeVeh = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM vehicule");
			
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				VehiculeM vehicule = new VehiculeM();
				
				vehicule.setId(rs.getInt("id"));
				vehicule.setMarque(rs.getString("marque"));
				vehicule.setModele(rs.getString("modele"));
				vehicule.setImmatriculation(rs.getString("immatriculation"));
				vehicule.setCouleur(rs.getString("couleur"));
				
				listeVeh.add(vehicule);
			}
			//System.out.println(listeUser);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Liste KO - KO - KO");
		}
		return listeVeh;
	}
	
	public VehiculeM findById(int id) {
		VehiculeM vehic = null;
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM vehicule WHERE id=?");
			req.setInt(1, id);
			
			ResultSet rs = req.executeQuery();
			
			if(rs.next()) {
				
				vehic =  new VehiculeM(rs.getInt("id"),rs.getString("marque"),rs.getString("modele"),
						rs.getString("couleur"),rs.getString("immatriculation"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("aaaaa KO - KO - KO" +e.getMessage());
		}
		return vehic;
	}
	
	public boolean editVehi(VehiculeM object) {
		boolean msg = false;
		try {
			PreparedStatement req = connect.prepareStatement("UPDATE vehicule SET marque=?, modele=?, couleur=?, immatriculation=?"
					+ " WHERE id=?");
			req.setString(1, object.getMarque());
			req.setString(2, object.getModele());
			req.setString(3, object.getCouleur());
			req.setString(4, object.getImmatriculation());
			req.setInt(5, object.getId());
			
			req.executeUpdate();
			System.out.println(req);
			msg = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Update KO" + e.getMessage());
		}
		return msg;
	}
	
	public boolean deleteVehi(VehiculeM object) {
		boolean msg = false;
		try {
			PreparedStatement req = connect.prepareStatement("DELETE FROM vehicule"
					+ " WHERE id=?");
			
			req.setInt(1, object.getId());
			
			req.executeUpdate();
			
			msg = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DELETE KO" + e.getMessage());
		}
		return msg;
	}
}
