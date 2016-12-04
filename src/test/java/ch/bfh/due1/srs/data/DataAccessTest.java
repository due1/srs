/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.bfh.due1.time.TimeSlot;
import ch.bfh.due1.time.TimeSlotFactory;

public class DataAccessTest {
	private DataAccess dataAccess;

	@Before
	public void setUp() {
		this.dataAccess = DataAccess.getInstance();
	}

	@Test
	public void testDataLayerAvailable() {
		assertNotNull(dataAccess);
	}

	@Test
	public void testMakePerson() {
		String name = "Albert Lee";
		String email = "albert@lee.org";
		Person p = this.dataAccess.makePerson(name, email);
		List<Person> all = this.dataAccess.findAllPersons();
		assertNotNull(all);
		assertTrue(all.size() > 0);
		assertTrue(all.contains(p));
	}

	@Test
	public void testRemovePerson() {
		String name = "Janis Joplin";
		String email = "janis@joplin.org";
		Person p = this.dataAccess.makePerson(name, email);
		this.dataAccess.removePerson(p.getId());
		List<Person> all = this.dataAccess.findAllPersons();
		assertNotNull(all);
		assertFalse(all.contains(p));
	}

	@Test
	public void testMakeRoom() {
		String name = "Abbey Road";
		int capacity = 10;
		Room r = this.dataAccess.makeRoom(name, capacity);
		List<Room> studios = this.dataAccess.findAllRooms();
		assertNotNull(studios);
		assertTrue(studios.size() > 0);
		assertTrue(studios.contains(r));
	}

	@Test
	public void testRemoveRoom() {
		String name = "LA Studio 6";
		int capacity = 20;
		Room r = this.dataAccess.makeRoom(name, capacity);
		this.dataAccess.removeRoom(r.getId());
		List<Room> studios = this.dataAccess.findAllRooms();
		assertNotNull(studios);
		assertFalse(studios.contains(r));
	}

	@Test
	public void testMakeReservation() {
		Person aPerson = this.dataAccess.makePerson("Dubuis, Eric", "due1@nodomain.org");
		Room aRoom = this.dataAccess.makeRoom("N215", 12);
		LocalDateTime startTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		LocalDateTime endTime = startTime.plusHours(1);
		TimeSlotFactory tsf = this.dataAccess.getTimeSlotFactory();
		TimeSlot timeslot = tsf.createTimeSlot(startTime, endTime);
		this.dataAccess.makeReservation(aPerson, aRoom, timeslot);
		// Test if due1's reservation list has been updated
		List<Reservation> res1 = aPerson.getReservations();
		assertNotNull(res1);
		// Test if n215's reservation list has been updated
		List<Reservation> res2 = aRoom.getReservations();
		assertNotNull(res2);
	}

	@Test
	public void testFindAllReservationsByRoom() {
		Person aPerson = this.dataAccess.makePerson("Dubuis, Eric", "due1@nodomain.org");
		Room aRoom = this.dataAccess.makeRoom("N215", 12);
		LocalDateTime startTime1 = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		LocalDateTime endTime1 = startTime1.plusHours(1);
		TimeSlotFactory tsf = this.dataAccess.getTimeSlotFactory();
		TimeSlot timeslot1 = tsf.createTimeSlot(startTime1, endTime1);
		this.dataAccess.makeReservation(aPerson, aRoom, timeslot1);
		LocalDateTime startTime2 = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(2);
		LocalDateTime endTime2 = startTime2.plusHours(1);
		TimeSlot timeslot2 = tsf.createTimeSlot(startTime2, endTime2);
		this.dataAccess.makeReservation(aPerson, aRoom, timeslot2);
		List<Reservation> reservations = this.dataAccess.findAllReservationsByRoom(aRoom);
		assertNotNull(reservations);
		assertTrue(reservations.size() == 2);
		for (Reservation r : reservations) {
			assertTrue(r.getTimeSlot().exactlyMatches(timeslot1) || r.getTimeSlot().exactlyMatches(timeslot2));
		}
	}
}
