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

import ch.bfh.ti.due1.srs.domain.concept.Person;
import ch.bfh.ti.due1.srs.domain.concept.Reservation;

public class PersonImpl implements Person {
	private String name;
	private String email;
	private Set<Reservation> reservations = new HashSet<>();

	public PersonImpl(String name, String email) {
		this.name = name;
		this.email = email;
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
	public Set<Reservation> getReservations() {
		return Collections.unmodifiableSet(this.reservations);
	}

	@Override
	public void addReservation(Reservation r) {
		this.reservations.add(r);
	}

	@Override
	public void removeReservation(Reservation r) {
		this.reservations.remove(r);
	}
}
