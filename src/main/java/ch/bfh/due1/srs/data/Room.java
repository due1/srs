/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.data;

import java.util.List;

public interface Room {

	public Long getId();

	public String getName();

	public void setName(String name);

	public int getCapacity();

	public void setCapacity(int capacity);

	public List<Reservation> getReservations();

	public void addReservation(Reservation reservation);

	public void removeReservation(Reservation reservation);
}
