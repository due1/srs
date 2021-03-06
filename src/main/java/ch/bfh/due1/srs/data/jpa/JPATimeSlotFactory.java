/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.data.jpa;

import java.time.LocalDateTime;

import ch.bfh.due1.time.TimeSlot;
import ch.bfh.due1.time.TimeSlotFactory;

/**
 * Creates a time slot POJO instance.
 *
 * @author Eric Dubuis
 */
public class JPATimeSlotFactory implements TimeSlotFactory {

	/**
	 * Creates a time slot POJO instance.
	 *
	 * @see TimeSlotEmbeddable
	 */
	@Override
	public TimeSlot createTimeSlot(LocalDateTime start, LocalDateTime end) {
		return new TimeSlotEmbeddable(start, end);
	}
}
