package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UtilisateurTest {

	@Test
	public void TestUtilisateur() {
		
		Utilisateur u1 = new Utilisateur("10.1.12.14", "alpha");
		Utilisateur u2 = new Utilisateur("10.1.12.18", "delta");
		
		
		// Test GetIP()
		assertEquals("10.1.12.14", u1.GetIP());
		assertEquals("10.1.12.18", u2.GetIP());
		
		
		// Test GetPseudo() et GetPseudo()
		assertEquals("alpha", u1.GetPseudo());
		assertEquals("delta", u2.GetPseudo());
		
		u2.SetPseudo("zulu");
		assertEquals("zulu", u2.GetPseudo());
		
		// Test SetAdmin() et GetAdmin()
		assertEquals(0, u1.GetAdmin());
		
		u2.SetAdmin(1);
		assertEquals(1, u2.GetAdmin());
	}
}
