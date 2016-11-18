/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import ch.bfh.due1.srs.data.DataAccess;
import ch.bfh.due1.srs.data.Person;
import ch.bfh.due1.srs.data.Room;
import ch.bfh.ti.daterange.DateRange;
import ch.bfh.ti.daterange.DateRangeFactory;

public class DataGenerator {

	public static void create() {
		DataAccess dataAccess = DataAccess.getInstance();
		// make a few persons
		Person due1 = dataAccess.makePerson("Dubuis, Eric", "due1@nodomain.org");
		dataAccess.makePerson("Hett, Werner", "hew1@nodomain.org");
		dataAccess.makePerson("Meyer, Franz", "myf1@nodomain.org");
		dataAccess.makePerson("", "");
		// make a few rooms
		Room n215 = dataAccess.makeRoom("N215", 12);
		dataAccess.makeRoom("N515", 10);
		dataAccess.makeRoom("N615", 20);
		// make a few reservations
		DateRangeFactory dateRangeFactory = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		LocalDateTime ltBegin = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		Date startTime = Date.from(ltBegin.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime ltEnd = ltBegin.plusHours(1);
		Date endTime = Date.from(ltEnd.atZone(ZoneId.systemDefault()).toInstant());
		DateRange timeslot = dateRangeFactory.createDateRange(startTime, endTime);
		dataAccess.makeReservation(due1, n215, timeslot);
	}
}
