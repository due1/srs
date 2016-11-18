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
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ch.bfh.due1.srs.data.Person;
import ch.bfh.due1.srs.data.Reservation;

@Entity(name = "Person")
public class PersonEntity implements Person {
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String email;

	@OneToMany(targetEntity = ReservationEntity.class)
	private List<Reservation> reservations;

	public PersonEntity() {
	}

	public PersonEntity(String name, String email) {
		this.name = name;
		this.email = email;
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
	public String getEmail() {
		return this.email;
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
		return "PersonEntity [id=" + this.id + ", name=" + this.name + ", email=" + this.email + ", reservations= {...}"
				+ "]";
	}
}
