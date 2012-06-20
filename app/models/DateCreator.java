package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class DateCreator {

	// Get the current system date
	public String getSystemDate(){
		
		  Calendar currentDate = Calendar.getInstance();
		  SimpleDateFormat formatter= 
		  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		  String dateNow = formatter.format(currentDate.getTime());
		  return dateNow;
		  
	}
	
	// A Simple method is implemented here to create the Date from the user Input
	public Date dateCreator(String date){
		
		Locale value=getLocale();
		Date aDate = null;
	    DateFormat fmt = DateFormat.getDateInstance(DateFormat.FULL,value);
	    try {
	      aDate = fmt.parse(date);
	      //fmt.format(aDate));
	    } catch (java.text.ParseException e) {
	     System.out.println(e);
	    }
		
		return aDate;
		
	}
	 
	// To get the locale of the user;
	public Locale getLocale(){
		 String locale = System.getProperty("user.language");
		 Locale localecode = new Locale(locale);
		 
		return localecode;
		
	}
	
	public DateCreator(){
	}
	/*public City getCity(String city){
		City c=new City();
		
		return null;
		
	}*/
}