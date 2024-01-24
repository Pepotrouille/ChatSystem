package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClavardageTest {

	static Utilisateur utilisateur1;
	static Utilisateur utilisateur2;
	static Utilisateur utilisateur3;
	@BeforeAll
	public static void InitialisationHistorique()
	{
		utilisateur1 = new Utilisateur("Jean", "10.12.25.20");
		utilisateur2 = new Utilisateur("Marc", "10.12.25.25");
		utilisateur3 = new Utilisateur("Micheline", "10.12.25.30");
	}
	
	
	@Test
	public void TestClavardage() {
		
	}
	
	
	
	
}
