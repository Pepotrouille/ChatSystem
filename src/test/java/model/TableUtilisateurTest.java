package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TableUtilisateurTest {

	@Test
	public void TestTableUtilisateurs() {
		
		TableUtilisateurs table_utilisateurs = new TableUtilisateurs();
		Utilisateur u1 = new Utilisateur("10.1.12.14", "alpha");
		Utilisateur u2 = new Utilisateur("10.1.12.192", "bravo");
		
		// AjouterUtilisateur
		table_utilisateurs.AjouterUtilisateur(u1);
		table_utilisateurs.AjouterUtilisateur("10.1.12.18", "delta");
		
		// AfficherListe
		table_utilisateurs.AfficherListe();
		
		// UtilisateurExiste
		assertEquals(true, table_utilisateurs.UtilisateurExiste(u1));
		assertEquals(false, table_utilisateurs.UtilisateurExiste(u2));
		assertEquals(true, table_utilisateurs.UtilisateurExiste("10.1.12.14"));
		assertEquals(false, table_utilisateurs.UtilisateurExiste("192.168.1.1"));
		
		// PseudoExiste
		assertEquals(true, table_utilisateurs.PseudoExiste("delta"));
		assertEquals(false, table_utilisateurs.PseudoExiste("bravo"));
		
		// GetUtilisateur
		assertEquals(u1, table_utilisateurs.GetUtilisateur("10.1.12.14"));
		
		// GetPseudo() et SetPseudo()
		assertEquals("alpha", table_utilisateurs.GetPseudo("10.1.12.14"));
		table_utilisateurs.SetPseudo("10.1.12.18", "zulu");
		assertEquals("zulu", table_utilisateurs.GetPseudo("10.1.12.18"));
		table_utilisateurs.AfficherListe();
		
		// SupprimerUtilisateur
		assertEquals(true, table_utilisateurs.SupprimerUtilisateur("10.1.12.18"));
		assertEquals(false, table_utilisateurs.SupprimerUtilisateur("192.168.1.1"));
		assertEquals(true, table_utilisateurs.SupprimerUtilisateur(u1));
		assertEquals(false, table_utilisateurs.SupprimerUtilisateur(u2));
		table_utilisateurs.AfficherListe();
				
	}
}
