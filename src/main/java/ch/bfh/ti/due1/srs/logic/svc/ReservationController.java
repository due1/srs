/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.logic.svc;

import ch.bfh.ti.daterange.DateRange;
import ch.bfh.ti.due1.srs.domain.concept.Person;
import ch.bfh.ti.due1.srs.domain.concept.Reservation;
import ch.bfh.ti.due1.srs.domain.concept.Room;

public interface ReservationController {

	/**
	 * Performs a booking of a given room for the given person. Returns
	 * the Reservation object or throws an exception of the booking
	 * cannot be performed.
	 *
	 * @param person a person making the booking
	 * @param room a room
	 * @param dateRange a date-range
	 * @return a Reservation object
	 * @throws ReservationControllerException if the booking cannot be performed
	 */
	public Reservation bookRoom(Person person, Room room, DateRange dateRange) throws ReservationControllerException;
}
