/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ch.bfh.due1.srs.data.DataAccess;
import ch.bfh.due1.srs.data.Person;
import ch.bfh.due1.srs.data.Room;
import ch.bfh.due1.srs.data.jpa.JPATimeSlotFactory;
import ch.bfh.due1.time.TimeSlot;
import ch.bfh.due1.time.TimeSlotFactory;

public class DataGenerator {

	public static void create() {
		DataAccess dataAccess = DataAccess.getInstance();
		// make a few persons
		Person due1 = dataAccess.makePerson("Dubuis, Eric", "due1@nodomain.org");
		dataAccess.makePerson("Hett, Werner", "hew1@nodomain.org");
		dataAccess.makePerson("Meyer, Franz", "myf1@nodomain.org");
		dataAccess.makePerson("", "");
		// make a few rooms
		Room n215 = dataAccess.makeRoom("N215", 10);
		dataAccess.makeRoom("N311", 18);
		dataAccess.makeRoom("N319", 18);
		dataAccess.makeRoom("N321", 30);
		dataAccess.makeRoom("N521", 28);
		// make a few reservations
		TimeSlotFactory tsf = new JPATimeSlotFactory();
		LocalDateTime startTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		LocalDateTime endTime = startTime.plusHours(1);
		TimeSlot timeslot = tsf.createTimeSlot(startTime, endTime);
		dataAccess.makeReservation(due1, n215, timeslot);
	}
}
