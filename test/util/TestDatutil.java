package util;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDatutil {

	private Date today;
	private Date tomorrow;

	@Before
	public void setUp() throws Exception {
		Calendar calendar = Calendar.getInstance();
		today = Calendar.getInstance().getTime();
		
		calendar.add(Calendar.DATE, 1);
		tomorrow = calendar.getTime();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDateDuJour() {
		Date date = Datutil.dateDuJour();
		assertTrue("NOK dateDuJour", dateEquals(today, date));
	}

	@Test
	public void testAddAuJour() {
		Datutil.addAuJour(1);
		Date date = Datutil.dateDuJour();
		assertTrue("NOK addAuJour", dateEquals(tomorrow, date));
	}

	/**
	 * False because 
	 * greg.add(Calendar.DATE, nbjour-10);
	 * should be
	 * greg.add(Calendar.DATE, nbjour);
	 */
	@Test
	public void testAddDate() {
		Date date = Datutil.addDate(Datutil.dateDuJour(), 1);
		assertTrue("NOK addDate", dateEquals(tomorrow, date));
	}

	@Test
	public void testDateToString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
		String todayString = format.format(today);
		String dateString = Datutil.dateToString(today);
		assertTrue("NOK dateToString", todayString.equals(dateString));
	}

	@Test
	public void testDateToSqlValues() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String todayString = format.format(today);
		String dateString = Datutil.dateToSqlValues(today);
		assertTrue("NOK dateToSqlValues", todayString.equals(dateString));
		
		Datutil.setDbLocale(new Locale("en"));
		format = new SimpleDateFormat("yyyy-MM-dd");
		todayString = format.format(today);
		dateString = Datutil.dateToSqlValues(today);
		assertTrue("NOK dateToSqlValues", todayString.equals(dateString));
	}

	private boolean dateEquals(Date date1, Date date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date1).equals(format.format(date2));
	}
}
