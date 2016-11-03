/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.domain.svc;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.bfh.ti.daterange.DateFactory;
import ch.bfh.ti.daterange.DateRange;
import ch.bfh.ti.daterange.DateRangeFactory;
import ch.bfh.ti.due1.srs.domain.concept.EntityFactory;
import ch.bfh.ti.due1.srs.domain.concept.Person;
import ch.bfh.ti.due1.srs.domain.concept.Room;
import ch.bfh.ti.due1.srs.logic.svc.ControllerFactory;
import ch.bfh.ti.due1.srs.logic.svc.ReservationController;
import ch.bfh.ti.due1.srs.logic.svc.ReservationControllerException;
import ch.bfh.ti.due1.srs.logic.svc.RoomController;

public class RoomControllerTest {

	@Test
	public void testListRooms() {
		EntityFactory ef = new EntityFactory();
		ControllerFactory cf = new ControllerFactory();
		RoomController rc = cf.getRoomController();
		Room r1 = ef.createRoom("R1", 10);
		Room r2 = ef.createRoom("R2", 20);
		rc.addRoom(r1);
		rc.addRoom(r2);
		assertEquals(2, rc.listRooms().size());
		assertTrue(rc.listRooms().contains(r1));
		assertTrue(rc.listRooms().contains(r2));
	}

	@Test
	public void testLookupAvailableRooms() throws ReservationControllerException {
		EntityFactory ef = new EntityFactory();
		ControllerFactory cf = new ControllerFactory();
		RoomController rc = cf.getRoomController();
		Room r1 = ef.createRoom("R1", 10);
		Room r2 = ef.createRoom("R2", 20);
		Room r3 = ef.createRoom("R3", 20);
		rc.addRoom(r1);
		rc.addRoom(r2);
		rc.addRoom(r3);
		ReservationController resC = cf.getReservationController();
		Person p = ef.createPerson("Harry Potter", "hp@nodomain.org");
		DateRangeFactory drf = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		DateRange dateRange = drf.createDateRange(DateFactory.now(), DateFactory.getTomorrow());
		resC.bookRoom(p, r1, dateRange);
		DateRange all = drf.createDateRange(DateFactory.EPOCH, DateFactory.INFINITY);
		assertEquals(2, rc.listAvailableRooms(all).size());
	}

	@Test
	public void testLookupBookedRooms() throws ReservationControllerException {
		EntityFactory ef = new EntityFactory();
		ControllerFactory cf = new ControllerFactory();
		RoomController rc = cf.getRoomController();
		Room r1 = ef.createRoom("R1", 10);
		Room r2 = ef.createRoom("R2", 20);
		Room r3 = ef.createRoom("R3", 20);
		rc.addRoom(r1);
		rc.addRoom(r2);
		rc.addRoom(r3);
		ReservationController resC = cf.getReservationController();
		Person p = ef.createPerson("Harry Potter", "hp@nodomain.org");
		DateRangeFactory drf = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		DateRange dateRange = drf.createDateRange(DateFactory.now(), DateFactory.getTomorrow());
		resC.bookRoom(p, r1, dateRange);
		DateRange all = drf.createDateRange(DateFactory.EPOCH, DateFactory.INFINITY);
		assertEquals(1, rc.listBookedRooms(all).size());
	}

}
