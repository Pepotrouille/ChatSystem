package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Utilisateur;

public class BDDAuthentificationController {
	
	// Singleton
	// Permettre d'accéder à la BDD pour s'authentifier
	
	public static BDDAuthentificationController self;
	
	static final String DB_URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_010";
	static final String USER = "projet_gei_010";
	static final String PASS = "eeP9nuha";
	
	public static BDDAuthentificationController GetInstance()
	{
		if(self == null)
		{
			self = new BDDAuthentificationController();
		}
		return self;
	}
	
	/* Créer une base d'authentification */
	private boolean CreerBaseAuthentification()
	{
		boolean baseIsCreated = true;
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				) {	
			String sql;
			
			
			sql = "CREATE TABLE BaseAuthentification " +
					"(login VARCHAR(255) not NULL, " +
					" password VARCHAR(255), " + 
					" PRIMARY KEY ( login ))"; 
			stmt.executeUpdate(sql);
			System.out.println("Created the BaseAuthentification in the database...");  
		}
		catch(Exception e)
		{
			baseIsCreated = false;
			e.printStackTrace();
		}
		return baseIsCreated;
	}
	
	/* Supprimer une base d'authentification */
	private boolean SupprimerBaseAuthentification()
	{
		boolean baseIsDeleted = true;
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				) {	
			String sql;
			
			sql = "DROP TABLE BaseAuthentification";
	        stmt.executeUpdate(sql);
	        
			System.out.println("Delete BaseAuthentification in the database..."); 
		}
		catch(Exception e)
		{
			baseIsDeleted = false;
			System.out.println("La BaseAuthentification n'existe pas");
		}
		return baseIsDeleted;
	}
	
	/* Ajouter une authentification */
	public boolean AjouterAuthentification(String login, String password) throws SQLException 
	{
		Utilisateur user;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
					
				
		String sql;
		//Add the ' around the String in argument !!
		sql = "INSERT INTO BaseAuthentification VALUES ('" + login +"', '" + password + ")";
		//user = new Utilisateur(login,password,foreName,lastName,localisation,phone, accountType);
				
				
		stmt.executeUpdate(sql);
		if (stmt.getMoreResults()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* Supprimer une authentification */
	private boolean SupprimerAuthentification(String login) throws SQLException
	{
		Utilisateur user;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
					
				
		String sql;
		//Add the ' around the String in argument !!
		sql = "DELETE FROM BaseAuthentification" + "WHERE login = " + login;
				
				
		stmt.executeUpdate(sql);
		if (stmt.getMoreResults()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* Vérifier si une authentification est bonne */
	public boolean VerifierAuthentification(String login, String password) throws SQLException
	{
		boolean authIsInTable = false;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
			
		PreparedStatement preparedStatement = conn.prepareStatement("SELECT login FROM BaseAuthentification WHERE login = ? AND password = ?");
		preparedStatement.setString (1, login);
		preparedStatement.setString (2, password);
		ResultSet resultSet = preparedStatement.executeQuery();	
			
		if (resultSet.next()) {
			    // Quest already completed
			authIsInTable = true;
		} else {
			    // Quest not completed yet
			authIsInTable = false;
		}

		return authIsInTable;
	}
	
	
}