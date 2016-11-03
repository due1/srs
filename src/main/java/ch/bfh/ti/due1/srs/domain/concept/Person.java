/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.domain.concept;

import java.util.Set;

public interface Person {

	public String getName();
	public String getEmail();
	public Set<Reservation> getReservations();

	public void addReservation(Reservation reservation);
	public void removeReservation(Reservation reservation);

	// TODO Update?
}
