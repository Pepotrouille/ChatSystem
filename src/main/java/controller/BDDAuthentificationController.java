package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.EchecManipulationBDDException;
import exceptions.ErreurConnexionException;

public class BDDAuthentificationController extends AbstractTableManager {
	
	// Singleton
	// Permettre d'accéder à la BDD pour s'authentifier
	/* A FAIRE : Utiliser PreparedStatement et n'utiliser pas directement les paramètres dans les queries
	pour éviter le problème de SQLInjection */
	
	public static BDDAuthentificationController self;
	
	public static BDDAuthentificationController GetInstance()
	{
		if(self == null)
		{
			self = new BDDAuthentificationController();
		}
		return self;
	}
	
	/* Créer une base d'authentification */
	public boolean CreerBaseAuthentification() throws SQLException, EchecManipulationBDDException
	{
		boolean baseIsCreated = true;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		String sql;
		sql = "CREATE TABLE BaseAuthentification " +
				"(login VARCHAR(255) not NULL, " +
				" password VARCHAR(255), " + 
				" PRIMARY KEY ( login ))"; 
		stmt.executeUpdate(sql);
		
		System.out.println("Created the BaseAuthentification in the database...");  
		AjouterAuthentification("admin", "admin");
		
		return baseIsCreated;
	}
	
	/* Supprimer une base d'authentification */
	public boolean SupprimerBaseAuthentification() throws SQLException
	{
		boolean baseIsDeleted = true;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		String sql;
		sql = "DROP TABLE BaseAuthentification";
	    stmt.executeUpdate(sql);
	        
		System.out.println("Delete BaseAuthentification in the database..."); 
		return baseIsDeleted;
	}
	
	/* Ajouter une authentification */
	public void AjouterAuthentification(String login, String password) throws SQLException, EchecManipulationBDDException 
	{
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
					
				
		String sql;
		sql = "INSERT INTO BaseAuthentification VALUES ('" + login +"', '" + password + "')";
				
				
		int i = stmt.executeUpdate(sql);
		if (i > 0) {
			System.out.println("Add a new authentification successfully! New login : " + login + ", new password : " + password);
		}
		else {
			throw new EchecManipulationBDDException();
		}
	}
	
	/* Supprimer une authentification */
	public void SupprimerAuthentification(String login) throws SQLException, EchecManipulationBDDException
	{
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
					
				
		String sql;
		//Add the ' around the String in argument !!
		sql = "DELETE FROM BaseAuthentification WHERE login = '" + login + "' ";
				
		int i = stmt.executeUpdate(sql);
		if (i > 0) {
			System.out.println("Delete the authentification " + login + " successfully!");
		}
		else {
			System.out.println("Failed to delete an authentification");
			throw new EchecManipulationBDDException();
		}		
		
	}
	
	/* Vérifier si une authentification est bonne */
	public void VerifierAuthentification(String login, String password) throws SQLException, ErreurConnexionException
	{
		
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
		PreparedStatement preparedStatement = conn.prepareStatement("SELECT login FROM BaseAuthentification WHERE login = ? AND password = ?");
		preparedStatement.setString (1, login);
		preparedStatement.setString (2, password);
		ResultSet resultSet = preparedStatement.executeQuery();	
				
		if (!resultSet.next()) {
			// Quest not completed yet
			throw new ErreurConnexionException();
		}
	}
}