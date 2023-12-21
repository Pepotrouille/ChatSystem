package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;
import model.Historique;
import model.MaDate;
import model.Message;

public class BDDMessageController extends AbstractTableManager {

	public static BDDMessageController self;
	
	public static BDDMessageController GetInstance()
	{
		if(self == null)
		{
			self = new BDDMessageController();
		}
		return self;
	}
	
	/* Créer une base de messages */
	public boolean CreerBaseMessage() throws SQLException
	{
		boolean baseIsCreated = true;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		String sql;
		sql = "CREATE TABLE BaseMessage " +
				"(id INT not NULL, " +
				" message VARCHAR(25555) not NULL, " +
				" ipsrc VARCHAR(255) not NULL, " +
				" ipdst VARCHAR(255) not NULL, " +
				" date DATE not NULL, " +
				" PRIMARY KEY ( id ))"; 
		stmt.executeUpdate(sql);
		
		System.out.println("Created the BaseMessage in the database...");  
		
		return baseIsCreated;
	}

	/* Supprimer une base de messages */
	public boolean SupprimerBaseMessage() throws SQLException
	{
		boolean baseIsDeleted = true;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		String sql;
		sql = "DROP TABLE BaseMessage";
	    stmt.executeUpdate(sql);
	        
		System.out.println("Delete BaseMessage in the database..."); 
		return baseIsDeleted;
	}
	
	

	/* Récupérer historique */
	public ArrayList<Message> GetMessageHistorique(String ipsrc, String ipdst) throws SQLException, MessageInvalideException, DateInvalideException {
		
		ArrayList<Message> messages = new ArrayList<Message>();
		ResultSet rs = null;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		
		
			
		String query = "SELECT message, ipsrc, ipdst, date "
					+ " FROM BaseMessage "
					+ " WHERE ipsrc = '" + ipsrc +  "' AND ipdst='" + ipdst +"'";
			
		rs = stmt.executeQuery(query);

			
		while(rs.next()){
			Message message = new Message(rs.getString("message"), MaDate.StringToMaDate(rs.getString("date")),true);
			messages.add(message);
		}
		
		query = "SELECT message, ipsrc, ipdst, date "
				+ " FROM BaseMessage "
				+ " WHERE ipsrc = '" + ipdst +  "' AND ipdst='" + ipsrc +"'";
		
		rs = stmt.executeQuery(query);

		
		while(rs.next()){
			Message message = new Message(rs.getString("message"), MaDate.DateToMaDate(rs.getDate("date")),false);
			messages.add(message);
		}
			
		
		return messages;
	}
	
	
	
	
	public void AjouterMessage(Message message, Historique historique) throws DateInvalideException, SQLException {
		
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();

		Random random = new Random();
		int id = random.nextInt(2147483646) + 1;
				
		String sql;
		sql = "INSERT INTO BaseMessage VALUES (" + id +",'" + message.GetContenu() +"', '" + historique.GetIPSource() +"', '" + historique.GetIPDestination() +"', '" + MaDate.MaDateToString(message.GetDate())  +"')";
		
		stmt.executeUpdate(sql);
		
		
		
	}
	
	
}
