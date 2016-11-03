/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.domain.concept;

import ch.bfh.ti.daterange.DateRange;
import ch.bfh.ti.due1.srs.domain.pojo.PersonImpl;
import ch.bfh.ti.due1.srs.domain.pojo.ReservationImpl;
import ch.bfh.ti.due1.srs.domain.pojo.RoomImpl;

public class EntityFactory {
	public Room createRoom(String name, int capacity) {
		return new RoomImpl(name, capacity);
	}

	public Person createPerson(String name, String email) {
		return new PersonImpl(name, email);
	}

	public Reservation createReservation(Person person, Room rooom, DateRange dateRange) {
		return new ReservationImpl(person, rooom, dateRange);
	}
}
