package com.smoothstack.training.lambdas;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;

public class DateTimeExamples {

	public static void main(String[] args) {
		 Instant instant = Instant.now();
		 ZonedDateTime jpTime = instant.atZone(ZoneId.of("Asia/Tokyo"));
		 
		 Instant newInstant = jpTime.toInstant();
		 

        System.out.println("ZonedDateTime : " + jpTime);
        System.out.println(newInstant);
        
        System.out.println("OffSet : " + jpTime.getOffset());
        
        LocalDate date = LocalDate.now();
//        	System.out.printf("The previous Thursday is: %s%n",
//        			date.with(TemporalAdjuster.previous(DayOfWeek.THURSDAY)));
        System.out.println(weeksInCalendar(YearMonth.now()));
        System.out.println(dateFallsOnAFriday(LocalDate.now()));
        System.out.println(LocalDate.now().getDayOfWeek());
        
        printMonthLengths(2000);
	}
	
	  public static List<LocalDate> weeksInCalendar(YearMonth month) {
		    List<LocalDate> mondaysOfMonth = new ArrayList<>();
		    for (LocalDate day = firstDayOfCalendar(month); stillInCalendar(month, day); day = day.plusWeeks(1)) {
		    	mondaysOfMonth.add(day);
		    }
		    return mondaysOfMonth;
		  }

		  private static LocalDate firstDayOfCalendar(YearMonth month) {
		    DayOfWeek FIRST_DAY_OF_WEEK = DayOfWeek.MONDAY;
		    return month.atDay(1).with(FIRST_DAY_OF_WEEK);
		  }

		  private static boolean stillInCalendar(YearMonth yearMonth, LocalDate day) {
		    return !day.isAfter(yearMonth.atEndOfMonth());
		  }
		  
		  
		  public static boolean dateFallsOnAFriday(LocalDate date)
		  {
			  if (date.getDayOfWeek() == DayOfWeek.FRIDAY && date.getDayOfMonth() == 13)
				  return true;
			  return false;
		  }
		  
		  public static void printMonthLengths(int year)
		  {
			  for (int i = 1; i < 13; i++)
			  {
				  
				  YearMonth month = YearMonth.of(year, i);
				  System.out.println(month.lengthOfMonth());
			  }
		  }

}
