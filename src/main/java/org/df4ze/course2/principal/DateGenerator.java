package org.df4ze.course2.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateGenerator {


	public static void main(String[] args) {
		LocalDate start = LocalDate.of(2016, 01, 01);
		LocalDate stop  = LocalDate.of(2016, 11, 01);
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyy-MM-dd");

		do{
			String sDate = start.format(f);
			System.out.println("http://www.geny.com/reunions-courses-pmu/"+sDate+"_d"+sDate);
			start = start.plus(1, ChronoUnit.DAYS);
		}while(start.isBefore(stop));

	}
	
	

}
