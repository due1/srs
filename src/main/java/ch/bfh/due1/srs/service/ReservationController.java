/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.service;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.due1.srs.data.DataAccess;
import ch.bfh.due1.srs.data.Person;
import ch.bfh.due1.srs.data.Reservation;
import ch.bfh.due1.srs.data.Room;
import ch.bfh.due1.time.TimeSlot;
import ch.bfh.due1.time.TimeSlotFactory;

public class ReservationController {
	private DataAccess dataAccess;

	public ReservationController() {
		this.dataAccess = DataAccess.getInstance();
	}

	/**
	 * Makes a reservation provided that the room is available for given time
	 * slot. Returns null if room is already booked.
	 *
	 * @param person
	 *            a person
	 * @param room
	 *            a room
	 * @param timeSlot
	 *            a time slot used to book the room
	 * @return the reservation, if booking took place, null otherwise
	 */
	public Reservation makeReservation(Person person, Room room, TimeSlot timeSlot) {
		Reservation res = null;
		if (available(room, timeSlot)) {
			res = this.dataAccess.makeReservation(person, room, timeSlot);
		}
		return res;
	}

	/**
	 * Given a room and a period in terms of a time slot, returns a list of
	 * reservations.
	 *
	 * @param room
	 *            a room
	 * @param timeSlot
	 *            a period within the bookings are looked up
	 * @return a possibly empty list of reservations
	 */
	public List<Reservation> listReservationsByRoom(Room room, TimeSlot timeSlot) {
		// TODO Avoid sub-optimal implementation
		List<Reservation> reservationsByPeriod = new ArrayList<>();
		List<Reservation> reservations = dataAccess.findAllReservationsByRoom(room);
		for (Reservation r : reservations) {
			if (timeSlot.overlaps(r.getTimeSlot())) {
				reservationsByPeriod.add(r);
			}
		}
		return reservationsByPeriod;
	}

	/**
	 * Given a time slot, finds all reservations within, or overlapping that
	 * time slot.
	 *
	 * @param timeSlot
	 *            a time slot
	 * @return a list of reservations, can be empty
	 */
	public List<Reservation> listReservationsByTimeSlot(TimeSlot timeSlot) {
		// TODO Avoid sub-optimal implementation
		List<Reservation> reservationsByPeriod = new ArrayList<>();
		List<Reservation> reservations = this.dataAccess.findAllReservations();
		for (Reservation r : reservations) {
			if (timeSlot.overlaps(r.getTimeSlot())) {
				reservationsByPeriod.add(r);
			}
		}
		return reservationsByPeriod;
	}

	public TimeSlotFactory getTimeSlotFactory() {
		DataAccess dataAccess = DataAccess.getInstance();
		return dataAccess.getTimeSlotFactory();
	}

	private boolean available(Room room, TimeSlot timeSlot) {
		List<Reservation> reservations = room.getReservations();
		boolean available = true;
		for (Reservation res : reservations) {
			if (res.getTimeSlot().overlaps(timeSlot)) {
				available = false;
				break;
			}
		}
		return available;
	}
}
