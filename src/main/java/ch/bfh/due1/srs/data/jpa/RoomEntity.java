/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.data.jpa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ch.bfh.due1.srs.data.Reservation;
import ch.bfh.due1.srs.data.Room;

@Entity(name = "Room")
public class RoomEntity implements Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private int capacity;
	@OneToMany(targetEntity = ReservationEntity.class)
	private List<Reservation> reservations;

	// Required no-argument constructor.
	protected RoomEntity() {
	}

	public RoomEntity(String name, int capacity) {
		super();
		this.name = name;
		this.capacity = capacity;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public List<Reservation> getReservations() {
		return this.reservations;
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
	public String toString() {
		return "RoomEntity [id=" + this.id + ", name=" + this.name + ", capacity=" + this.capacity
				+ ", reservations= {...}" + "]";
	}
}
