/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.data;

import java.util.List;

public interface Person {

	public Long getId();

	public String getName();

	public String getEmail();

	public List<Reservation> getReservations();

	public void addReservation(Reservation r);

	public void removeReservation(Reservation r);
}
