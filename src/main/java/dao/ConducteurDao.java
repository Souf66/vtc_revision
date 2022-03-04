package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conducteur;

public class ConducteurDao implements IDao<Conducteur>{

	Connection connect = getConnection.connect();
	
	ResultSet rs;
	@Override
	public boolean create(Conducteur object) {
		//Je crée une variable que initialise à false que je vais
		// utiliser plus tard pour savoir si la creation(insert en BDD) s'est bien passée ou pas 
		boolean added = false;
		
		//ICI, je prepare ma requete SQL avec un prepareStatement
		//afin d'éviter les injections SQL
		
		try {
			PreparedStatement req = connect.prepareStatement("INSERT INTO conducteur (prenom,nom) VALUES (?,?)");
			req.setString(1, object.getPrenom());
			req.setString(2, object.getNom());
			
			req.executeUpdate();
			
			// Si tout s'est bien passé, je change la valeur à true
			added = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return added;
	}

	@Override
	public List<Conducteur> read() {

		List<Conducteur> listCon = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM conducteur");
			
			rs = req.executeQuery();
			
			while(rs.next()) {
				Conducteur cond = new Conducteur(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"));
				
				listCon.add(cond);
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCon;
	}
	
	public Conducteur findById(int id) {
		Conducteur conducteur =null;
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM conducteur WHERE id=?");
			req.setInt(1, id);
			
			ResultSet rs = req.executeQuery();
			
			if(rs.next()) {
				
				conducteur =  new Conducteur(rs.getInt("id"),rs.getString("prenom"),rs.getString("nom"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("aaaaa KO - KO - KO");
		}
		return conducteur;
	}
	
	public boolean editConducteur(Conducteur object) {
		boolean msg = false;
		try {
			PreparedStatement req = connect.prepareStatement("UPDATE conducteur SET prenom=?, nom=?"
					+ " WHERE id=?");
			req.setString(1, object.getPrenom());
			req.setString(2, object.getNom());
			req.setInt(3, object.getId());
			
			req.executeUpdate();

			msg = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Update KO " +e.getMessage());
		}
		return msg;
	}
	
	public boolean delete(Conducteur object) {
		boolean msg = false;
		try {
			PreparedStatement req = connect.prepareStatement("DELETE FROM conducteur"
					+ " WHERE id_conducteur=?");
			
			req.setInt(1, object.getId());
			
			req.executeUpdate();
			
			msg = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DELETE KO");
		}
		return msg;
	}
}
