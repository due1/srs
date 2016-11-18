/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.data;

import ch.bfh.ti.daterange.DateRange;

public interface Reservation {

	public Person getOwner();

	// public List<Person> getInvolved();

	public DateRange getDateRange();
}
