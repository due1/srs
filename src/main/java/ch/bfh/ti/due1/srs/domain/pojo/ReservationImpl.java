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
import ch.bfh.ti.due1.srs.domain.concept.Person;
import ch.bfh.ti.due1.srs.domain.concept.Reservation;
import ch.bfh.ti.due1.srs.domain.concept.Room;

public class ReservationImpl implements Reservation {
	private Person owner;
	private Room room;
	private DateRange dateRange;
	private Set<Person> involved = new HashSet<>();

	public ReservationImpl(Person owner, Room room, DateRange dateRange) {
		this(owner, room, dateRange, new HashSet<>());
	}

	public ReservationImpl(Person owner, Room room, DateRange dateRange, Set<Person> involved) {
		this.owner = owner;
		this.room = room;
		this.dateRange = dateRange;
		this.involved = involved;
	}

	@Override
	public Person getOwner() {
		return this.owner;
	}

	@Override
	public Set<Person> getInvolved() {
		return Collections.unmodifiableSet(this.involved);
	}

	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	@Override
	public Room getRoom() {
		return this.room;
	}
}
