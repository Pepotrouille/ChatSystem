package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AllSignalTest {

	@Test
	public void TestSignal() {
		String caractereSignal = "S";
		//-----Test Constructeur
		Signal signal = new Signal();
		
		//-----Test To String
		assertEquals(caractereSignal, signal.ToString());
	}
	

	@Test
	public void TestSignalChangementPseudo() {
		String caractereSignal = "P";
		String pseudoTest = "PseudoTest";

		//-----Test Constructeur
		SignalChangementPseudo signal = new SignalChangementPseudo(pseudoTest);
		
		//-----Test To String
		assertEquals(caractereSignal + pseudoTest, signal.ToString());

		//-----Test Getter
		assertEquals(pseudoTest, signal.GetPseudo());

		//-----Test Setter
		pseudoTest = "NouveauPseudoTest";
		signal.SetPseudo(pseudoTest);
		assertEquals(pseudoTest, signal.GetPseudo() );
		assertEquals(caractereSignal + pseudoTest, signal.ToString());
	}


	@Test
	public void TestSignalConflitPseudo() {
		String caractereSignal = "O";
		String pseudoTest = "PseudoTest";

		//-----Test Constructeur
		SignalConflitPseudo signal = new SignalConflitPseudo(pseudoTest);
		
		//-----Test To String
		assertEquals(caractereSignal + pseudoTest, signal.ToString());

		//-----Test Getter
		assertEquals(pseudoTest, signal.GetPseudo());

		//-----Test Setter
		pseudoTest = "NouveauPseudoTest";
		signal.SetPseudo(pseudoTest);
		assertEquals(pseudoTest, signal.GetPseudo() );
		assertEquals(caractereSignal + pseudoTest, signal.ToString());
	}


	@Test
	public void TestSignalConnexion() {
		String caractereSignal = "C";
		String pseudoTest = "PseudoTest";

		//-----Test Constructeur
		SignalConnexion signal = new SignalConnexion(pseudoTest);
		
		//-----Test To String
		assertEquals(caractereSignal + pseudoTest, signal.ToString());

		//-----Test Getter
		assertEquals(pseudoTest, signal.GetPseudo());

		//-----Test Setter
		pseudoTest = "NouveauPseudoTest";
		signal.SetPseudo(pseudoTest);
		assertEquals(pseudoTest, signal.GetPseudo() );
		assertEquals(caractereSignal + pseudoTest, signal.ToString());
	}


	@Test
	public void TestSignalDeconnexion() {
		String caractereSignal = "D";
		
		//-----Test Constructeur
		SignalDeconnexion signal = new SignalDeconnexion();
		
		//-----Test To String
		assertEquals(caractereSignal, signal.ToString());
	}


	@Test
	public void TestSignalDeconnexionAutreUtilisateur() {
		String caractereSignal = "A";
		String ipTest = "127.0.0.1";

		//-----Test Constructeur
		SignalDeconnexionAutreUtilisateur signal = new SignalDeconnexionAutreUtilisateur(ipTest);
		
		//-----Test To String
		assertEquals(caractereSignal + ipTest, signal.ToString());

		//-----Test Getter
		assertEquals( ipTest, signal.GetIPAutre());

		//-----Test Setter
		ipTest = "NouveauPseudoTest";
		signal.SetIPAutre(ipTest);
		assertEquals( ipTest, signal.GetIPAutre());
		assertEquals(caractereSignal + ipTest, signal.ToString());
	}

	
	@Test
	public void TestSignalMessage() {
		String caractereSignal = "M";
		String messageTest = "Ceci est un message";

		//-----Test Constructeur
		SignalMessage signal = new SignalMessage(messageTest);
		
		//-----Test To String
		assertEquals(caractereSignal + messageTest, signal.ToString());

		//-----Test Getter
		assertEquals(messageTest, signal.GetMessage());

		//-----Test Setter
		messageTest = "NouveauPseudoTest";
		signal.SetMessage(messageTest);
		assertEquals(messageTest, signal.GetMessage());
		assertEquals(caractereSignal + messageTest, signal.ToString());
	}
	

	@Test
	public void TestSignalNouveauClavardage() {
		String caractereSignal = "N";
		int portTest = 6500;

		//-----Test Constructeur
		SignalNouveauClavardage signal = new SignalNouveauClavardage(portTest);
		
		//-----Test To String
		assertEquals(caractereSignal + portTest, signal.ToString());

		//-----Test Getter
		assertEquals(portTest, signal.GetPort());

		//-----Test Setter
		portTest = 55011;
		signal.SetPort(portTest);
		assertEquals(portTest, signal.GetPort());
		assertEquals(caractereSignal + portTest, signal.ToString());
	}


	@Test
	public void TestSignalReponseConnexion() {
		String caractereSignal = "R";
		String pseudoTest = "PseudoTest";

		//-----Test Constructeur
		SignalReponseConnexion signal = new SignalReponseConnexion(pseudoTest);
		
		//-----Test To String
		assertEquals(caractereSignal + pseudoTest, signal.ToString());

		//-----Test Getter
		assertEquals(pseudoTest, signal.GetPseudo());

		//-----Test Setter
		pseudoTest = "NouveauPseudoTest";
		signal.SetPseudo(pseudoTest);
		assertEquals(pseudoTest, signal.GetPseudo() );
		assertEquals(caractereSignal + pseudoTest, signal.ToString());
	}
	
	
	@Test
	public void TestSignalValiderNouveauClavardage() {
		String caractereSignal = "V";
		int portTest = 5500;

		//-----Test Constructeur
		SignalValiderNouveauClavardage signal = new SignalValiderNouveauClavardage(portTest);
		
		//-----Test To String
		assertEquals(caractereSignal + portTest, signal.ToString());

		//-----Test Getter
		assertEquals(portTest, signal.GetPort());

		//-----Test Setter
		portTest = 55011;
		signal.SetPort(portTest);
		assertEquals(portTest, signal.GetPort());
		assertEquals(caractereSignal + portTest, signal.ToString());
	}
}
