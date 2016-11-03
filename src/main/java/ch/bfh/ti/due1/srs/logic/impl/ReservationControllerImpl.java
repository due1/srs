/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.logic.impl;

import ch.bfh.ti.daterange.DateRange;
import ch.bfh.ti.due1.srs.domain.concept.EntityFactory;
import ch.bfh.ti.due1.srs.domain.concept.Person;
import ch.bfh.ti.due1.srs.domain.concept.Reservation;
import ch.bfh.ti.due1.srs.domain.concept.Room;
import ch.bfh.ti.due1.srs.logic.svc.ReservationController;
import ch.bfh.ti.due1.srs.logic.svc.ReservationControllerException;

public class ReservationControllerImpl implements ReservationController {
	private EntityFactory ef = new EntityFactory();

	@Override
	public Reservation bookRoom(Person person, Room room, DateRange dateRange) throws ReservationControllerException {
		if (room.available(dateRange)) {
			Reservation res = ef.createReservation(person, room, dateRange);
			person.addReservation(res);
			room.addReservation(res);
			return res;
		} else {
			throw new ReservationControllerException("Room is not available in: " + dateRange);
		}
	}
}
