package dao;

import java.sql.Connection;
import java.sql.DriverManager;

//Change le nom
public class getConnection {
	//STATIC ;((((((
	public static Connection connect() {
		
		//Ici, je definis les accés à la BDD ainsi que son nom !
		String url="jdbc:mysql://localhost/";
		String database = "vtc_revision"; // tu l'appelles comme tu veux
		String user = "root"; // windows
		String pwd = ""; // vide pour windows // root pour mac 
		
		// j'importe le package de SQL
		Connection connect = null;
		
		// Oublie de gerer les exceptions !!! 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url+database,user,pwd);
			
			System.out.println("Connection OK");
		} catch (Exception e) {
			System.out.println("Connection OK" + e.getMessage());
		}
		return connect;
	}
}
