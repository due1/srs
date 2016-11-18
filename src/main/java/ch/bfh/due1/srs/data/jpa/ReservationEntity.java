/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.data.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ch.bfh.due1.srs.data.Person;
import ch.bfh.due1.srs.data.Reservation;
import ch.bfh.due1.srs.data.Room;
import ch.bfh.ti.daterange.DateRange;

@Entity(name = "Reservation")
public class ReservationEntity implements Reservation {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(targetEntity = PersonEntity.class)
	private Person owner;
	// private List<Person> involved;

	@ManyToOne(targetEntity = RoomEntity.class)
	private Room room;

	private DateRange dateRange;

	public ReservationEntity() {
	}

	public ReservationEntity(Person owner, Room room, DateRange dateRange) {
		this.owner = owner;
		this.room = room;
		this.dateRange = dateRange;
	}

	// public ReservationEntity(Person owner, Room room, DateRange dateRange,
	// List<Person> involved) {
	// this.owner = owner;
	// this.room = room;
	// this.dateRange = dateRange;
	// this.involved = involved;
	// }

	@Override
	public Person getOwner() {
		return this.owner;
	}

	// @Override
	// public List<Person> getInvolved() {
	// return Collections.unmodifiableList(this.involved);
	// }

	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	@Override
	public String toString() {
		return "ReservationEntity [id=" + this.id + ", owner=" + this.owner + ", room=" + this.room + ", dateRange="
				+ this.dateRange + "]";
	}
}
