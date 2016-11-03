/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.domain.pojo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.daterange.DateRange;
import ch.bfh.ti.due1.srs.domain.concept.Reservation;
import ch.bfh.ti.due1.srs.domain.concept.Room;

public class RoomImpl implements Room {
	private String name;
	private int capacity;
	private Set<Reservation> reservations = new HashSet<>();

	public RoomImpl(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public Set<Reservation> getReservations() {
		return Collections.unmodifiableSet(this.reservations);
	}

	@Override
	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}

	@Override
	public void removeReservation(Reservation reservation) {
		this.reservations.remove(reservation);
	}

	@Override
	public boolean available(DateRange dateRange) {
		boolean available = true;
		for (Reservation res: this.reservations) {
			if (dateRange.overlaps(res.getDateRange())) {
				available = false;
				break;
			}
		}
		return available;
	}
}
