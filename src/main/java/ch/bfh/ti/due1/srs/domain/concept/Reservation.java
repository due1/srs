/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.domain.concept;

import java.util.Set;

import ch.bfh.ti.daterange.DateRange;

public interface Reservation {

	public Person getOwner();
	public Set<Person> getInvolved();
	public DateRange getDateRange();
	public Room getRoom();
}
