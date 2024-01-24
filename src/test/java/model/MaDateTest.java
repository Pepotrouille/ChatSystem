package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.DateInvalideException;

public class MaDateTest {

	
	
	@Test
	public void StringToMaDateTest() {

		MaDate date1 = null;//"2023-12-18 11:35:20"
		MaDate date2 = null;//"2002-06-22 00:00:00"
		MaDate date3 = null;//"1920-12-15 23:35:00"
		try {
			date1 = new MaDate(2023, 12, 18, 11, 35, 20);
			date2 = new MaDate(2002, 06, 22, 0, 0, 0);
			date3 = new MaDate(1920, 12, 15, 23, 35, 0);
		} catch (DateInvalideException e) {
			fail("Date invalide : " + e.toString());
		}
		
		TestStringToMaDatePourUneDate(date1, "2023-12-18 11:35:20");
		TestStringToMaDatePourUneDate(date2, "2002-06-22 00:00:00");
		TestStringToMaDatePourUneDate(date3, "1920-12-15 23:35:00");
	}
	
	@Test
	public void MaDateToStringTest() {

		MaDate date1 = null;
		MaDate date2 = null;
		MaDate date3 = null;
		try {
			date1 = new MaDate(2023, 12, 18, 11, 35, 20);
			date2 = new MaDate(2002, 06, 22, 0, 0, 0);
			date3 = new MaDate(1920, 12, 15, 23, 35, 0);
		} catch (DateInvalideException e) {
			fail("Date invalide : " + e.toString());
		}
		
		
		try {
			assertEquals("2023-12-18 11:35:20", MaDate.MaDateToString(date1));
			assertEquals("2002-06-22 00:00:00", MaDate.MaDateToString(date2));
			assertEquals("1920-12-15 23:35:00", MaDate.MaDateToString(date3));
		} catch (DateInvalideException e) {
			e.printStackTrace();
		}
	}
	
	
	private void TestStringToMaDatePourUneDate(MaDate maDate, String dateString)
	{
		try {
			assertEquals(maDate.GetAnnee(), MaDate.StringToMaDate(dateString).GetAnnee());
			assertEquals(maDate.GetMois(), MaDate.StringToMaDate(dateString).GetMois());
			assertEquals(maDate.GetJour(), MaDate.StringToMaDate(dateString).GetJour());
			assertEquals(maDate.GetHeure(), MaDate.StringToMaDate(dateString).GetHeure());
			assertEquals(maDate.GetMinute(), MaDate.StringToMaDate(dateString).GetMinute());
			assertEquals(maDate.GetSeconde(), MaDate.StringToMaDate(dateString).GetSeconde());
		} catch (DateInvalideException e) {
			fail("Date invalide : " + e.toString());
		}
	}
	
}
