package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import exceptions.DateInvalideException;

public class MaDateTest {

	
	
	@Test
	public void StringToMaDateTest() {

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
		
		//assertEquals("18-12-2023 11:35:20", MaDate.StringToMaDate("18-12-2023 11:35:20"));
		//assertEquals("22-06-2002 0:0:0", MaDate.StringToMaDate("22-06-2002 0:0:0"));
		//assertEquals("15-12-1920 23:35:0", MaDate.StringToMaDate("15-12-1920 23:35:0"));
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
		
		
		//assertEquals("18-12-2023 11:35:20", MaDate.MaDateToString(date1));
		//assertEquals("22-06-2002 0:0:0", MaDate.MaDateToString(date2));
		//assertEquals("15-12-1920 23:35:0", MaDate.MaDateToString(date3));
	}
	
}
