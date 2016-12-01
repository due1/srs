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

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.bfh.due1.srs.data.Room;

public class RoomControllerTest {
	private RoomController roomController;

	@Before
	public void setUp() {
		this.roomController = new RoomController();
	}

	@Test
	public void testAddRoom() {
		String n = "Abbey Road Studios Room Beatles";
		int c = 10;
		Room r = this.roomController.addRoom(n, c);
		List<Room> rooms = this.roomController.listRooms();
		assertNotNull(rooms);
		assertTrue(rooms.size() >= 1);
		assertTrue(rooms.contains(r));
	}

	@Test
	public void testListRooms() {
		String n1 = "Beverly Hills Suite";
		int c1 = 4;
		Room r1 = this.roomController.addRoom(n1, c1);
		String n2 = "Beverly Hill Standard";
		int c2 = 2;
		Room r2 = this.roomController.addRoom(n2, c2);
		List<Room> rooms = this.roomController.listRooms();
		assertNotNull(rooms);
		assertTrue(rooms.size() >= 2);
		assertTrue(rooms.contains(r1));
		assertTrue(rooms.contains(r2));
	}

	// @Test
	// public void testLookupAvailableRooms() throws
	// ReservationControllerException {
	// EntityFactory ef = new EntityFactory();
	// ControllerFactory cf = new ControllerFactory();
	// ReservationController rc = cf.getRoomController();
	// Room r1 = ef.createRoom("R1", 10);
	// Room r2 = ef.createRoom("R2", 20);
	// Room r3 = ef.createRoom("R3", 20);
	// rc.addRoom(r1);
	// rc.addRoom(r2);
	// rc.addRoom(r3);
	// ReservationController resC = cf.getReservationController();
	// Person p = ef.createPerson("Harry Potter", "hp@nodomain.org");
	// DateRangeFactory drf = new
	// ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
	// DateRange dateRange = drf.createDateRange(DateTimeFactory.now(),
	// DateTimeFactory.getTomorrow());
	// resC.bookRoom(p, r1, dateRange);
	// DateRange all = drf.createDateRange(DateTimeFactory.EPOCH,
	// DateTimeFactory.INFINITY);
	// assertEquals(2, rc.listAvailableRooms(all).size());
	// }
	//
	// @Test
	// public void testLookupBookedRooms() throws ReservationControllerException
	// {
	// EntityFactory ef = new EntityFactory();
	// ControllerFactory cf = new ControllerFactory();
	// ReservationController rc = cf.getRoomController();
	// Room r1 = ef.createRoom("R1", 10);
	// Room r2 = ef.createRoom("R2", 20);
	// Room r3 = ef.createRoom("R3", 20);
	// rc.addRoom(r1);
	// rc.addRoom(r2);
	// rc.addRoom(r3);
	// ReservationController resC = cf.getReservationController();
	// Person p = ef.createPerson("Harry Potter", "hp@nodomain.org");
	// DateRangeFactory drf = new
	// ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
	// DateRange dateRange = drf.createDateRange(DateTimeFactory.now(),
	// DateTimeFactory.getTomorrow());
	// resC.bookRoom(p, r1, dateRange);
	// DateRange all = drf.createDateRange(DateTimeFactory.EPOCH,
	// DateTimeFactory.INFINITY);
	// assertEquals(1, rc.listBookedRooms(all).size());
	// }
}
