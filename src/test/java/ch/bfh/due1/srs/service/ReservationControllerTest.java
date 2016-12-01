/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.bfh.due1.srs.data.DataAccess;
import ch.bfh.due1.srs.data.Person;
import ch.bfh.due1.srs.data.Reservation;
import ch.bfh.due1.srs.data.Room;
import ch.bfh.due1.time.TimeSlot;
import ch.bfh.due1.time.TimeSlotFactory;

public class ReservationControllerTest {
	private ReservationController reservationController;

	@Before
	public void setUp() {
		this.reservationController = new ReservationController();
	}

	@Test
	public void testListReservationsByRoom() {
		DataAccess dataAccess = DataAccess.getInstance();
		Person aPerson = dataAccess.makePerson("Dubuis, Eric", "due1@nodomain.org");
		Room aRoom = dataAccess.makeRoom("N215", 12);
		LocalDateTime startTime1 = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		LocalDateTime endTime1 = startTime1.plusHours(1);
		TimeSlotFactory tsf = dataAccess.getTimeSlotFactory();
		TimeSlot timeslot1 = tsf.createTimeSlot(startTime1, endTime1);
		this.reservationController.makeReservation(aPerson, aRoom, timeslot1);
		LocalDateTime startTime2 = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(2);
		LocalDateTime endTime2 = startTime2.plusHours(1);
		TimeSlot timeslot2 = tsf.createTimeSlot(startTime2, endTime2);
		this.reservationController.makeReservation(aPerson, aRoom, timeslot2);
		List<Reservation> res1 = this.reservationController.listReservationsByRoom(aRoom, timeslot1);
		assertNotNull(res1);
		assertTrue(res1.get(0).getTimeSlot().equals(timeslot1));
		boolean ok = false;
		for (Reservation r : res1) {
			if (r.getTimeSlot().exactlyMatches(timeslot1)) {
				// There is a reservation with exactly this time slot
				ok = true;
			}
		}
		if (!ok) {
			fail("No reservation with timeslot: " + timeslot1);
		}
		List<Reservation> res2 = this.reservationController.listReservationsByRoom(aRoom, timeslot2);
		assertNotNull(res2);
		for (Reservation r : res2) {
			if (r.getTimeSlot().exactlyMatches(timeslot2)) {
				// There is a reservation with exactly this time slot
				ok = true;
			}
		}
		if (!ok) {
			fail("No reservation with timeslot: " + timeslot1);
		}
	}

	@Test
	public void testListReservationsByTimeSlot() {
		DataAccess dataAccess = DataAccess.getInstance();
		Person aPerson = dataAccess.makePerson("Meyer, Franz", "myf1@nodomain.org");
		Room aRoom = dataAccess.makeRoom("N311", 18);
		LocalDateTime startTime1 = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(4);
		LocalDateTime endTime1 = startTime1.plusHours(1);
		TimeSlotFactory tsf = dataAccess.getTimeSlotFactory();
		TimeSlot timeslot1 = tsf.createTimeSlot(startTime1, endTime1);
		this.reservationController.makeReservation(aPerson, aRoom, timeslot1);
		LocalDateTime startTime2 = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(7);
		LocalDateTime endTime2 = startTime2.plusHours(1);
		TimeSlot timeslot2 = tsf.createTimeSlot(startTime2, endTime2);
		this.reservationController.makeReservation(aPerson, aRoom, timeslot2);
		LocalDateTime startTime0 = startTime1.plusHours(-1);
		LocalDateTime endTime0 = endTime2.plusHours(1);
		TimeSlot overall = tsf.createTimeSlot(startTime0, endTime0);
		List<Reservation> res1 = this.reservationController.listReservationsByTimeSlot(overall);
		assertNotNull(res1);
		boolean ok = false;
		for (Reservation r : res1) {
			if (r.getTimeSlot().equals(timeslot1)) {
				// There is a reservation with exactly this time slot
				ok = true;
				break;
			}
		}
		if (!ok) {
			fail("No reservation with timeslot: " + timeslot1);
		}
		LocalDateTime startTime11 = startTime1.plusHours(-1);
		LocalDateTime endTime11 = endTime1.plusHours(1);
		TimeSlot timeSlot11 = tsf.createTimeSlot(startTime11, endTime11);
		List<Reservation> res2 = this.reservationController.listReservationsByTimeSlot(timeSlot11);
		assertNotNull(res2);
		for (Reservation r : res1) {
			if (r.getTimeSlot().equals(timeslot2)) {
				// There is a reservation with exactly this time slot
				ok = true;
				break;
			}
		}
		if (!ok) {
			fail("No reservation with timeslot: " + timeslot2);
		}
	}

	@Test()
	public void testBookRoom2() {
		DataAccess dataAccess = DataAccess.getInstance();
		Person aPerson = dataAccess.makePerson("Hett, Werner", "hew1@nodomain.org");
		Room aRoom = dataAccess.makeRoom("N2421", 30);
		LocalDateTime startTime1 = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		LocalDateTime endTime1 = startTime1.plusHours(1);
		TimeSlotFactory tsf = dataAccess.getTimeSlotFactory();
		TimeSlot timeslot1 = tsf.createTimeSlot(startTime1, endTime1);
		Reservation res1 = this.reservationController.makeReservation(aPerson, aRoom, timeslot1);
		assertNotNull(res1);
		Person aSecondPerson = dataAccess.makePerson("Lehner, Urs", "lnu1@nodomain.org");
		Reservation res2 = this.reservationController.makeReservation(aSecondPerson, aRoom, timeslot1);
		assertNotNull(res2);
	}
}
