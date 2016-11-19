/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.service;

import org.junit.Test;

public class ReservationControllerTest {
	@Test
	public void testToBeRemoved() {
		// TODO Replace this test method by a set of useful test method.
	}

	// @Test
	// public void testLookupAvailableRooms() throws
	// ReservationControllerException {
	// EntityFactory ef = new EntityFactory();
	// DateRangeFactory drf = new
	// ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
	// ControllerFactory cf = new ControllerFactory();
	// RoomController rc = cf.getRoomController();
	// Room r1 = ef.createRoom("R1", 10);
	// Room r2 = ef.createRoom("R2", 20);
	// Room r3 = ef.createRoom("R3", 20);
	// rc.addRoom(r1);
	// rc.addRoom(r2);
	// rc.addRoom(r3);
	// DateRange dr = drf.createDateRange(DateFactory.EPOCH,
	// DateFactory.INFINITY);
	// assertEquals(3, rc.listAvailableRooms(dr).size());
	// Person p = ef.createPerson("Harry Potter", "hp@nodomain.org");
	// ReservationController resc = cf.getReservationController();
	// DateRange timeslot = drf.createDateRange(DateFactory.now(),
	// DateFactory.getTomorrow());
	// resc.bookRoom(p, r1, timeslot);
	// assertEquals(2, rc.listAvailableRooms(dr).size());
	// }
	//
	// @Test
	// public void testLookupBookedRooms() throws ReservationControllerException
	// {
	// EntityFactory ef = new EntityFactory();
	// DateRangeFactory drf = new
	// ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
	// ControllerFactory cf = new ControllerFactory();
	// RoomController rc = cf.getRoomController();
	// Room r1 = ef.createRoom("R1", 10);
	// Room r2 = ef.createRoom("R2", 20);
	// Room r3 = ef.createRoom("R3", 20);
	// rc.addRoom(r1);
	// rc.addRoom(r2);
	// rc.addRoom(r3);
	// DateRange dr = drf.createDateRange(DateFactory.EPOCH,
	// DateFactory.INFINITY);
	// assertEquals(3, rc.listAvailableRooms(dr).size());
	// Person p = ef.createPerson("Harry Potter", "hp@nodomain.org");
	// ReservationController resc = cf.getReservationController();
	// DateRange timeslot = drf.createDateRange(DateFactory.now(),
	// DateFactory.getTomorrow());
	// resc.bookRoom(p, r1, timeslot);
	// assertEquals(1, rc.listBookedRooms(dr).size());
	// }
	//
	// @Test(expected = ReservationControllerException.class)
	// public void testBookRoom2() throws ReservationControllerException {
	// EntityFactory ef = new EntityFactory();
	// DateRangeFactory drf = new
	// ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
	// ControllerFactory cf = new ControllerFactory();
	// RoomController rc = cf.getRoomController();
	// Room r1 = ef.createRoom("R1", 10);
	// rc.addRoom(r1);
	// Person p1 = ef.createPerson("Harry Potter", "hp@nodomain.org");
	// Person p2 = ef.createPerson("Hans Muster", "hp@nodomain.org");
	// ReservationController resc = cf.getReservationController();
	// DateRange timeslot = drf.createDateRange(DateFactory.now(),
	// DateFactory.getTomorrow());
	// resc.bookRoom(p1, r1, timeslot);
	// resc.bookRoom(p2, r1, timeslot);
	// }
}