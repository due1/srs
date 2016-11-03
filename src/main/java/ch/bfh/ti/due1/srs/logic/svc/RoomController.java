/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.logic.svc;

import java.util.Set;

import ch.bfh.ti.daterange.DateRange;
import ch.bfh.ti.due1.srs.domain.concept.Room;

public interface RoomController {
	public void addRoom(Room room);
	public void removeRoom(Room room);
	public void updateRoom(Room room);
	public Set<Room> listRooms();

	/**
	 * Given a date range starting at t1 and ending at t2, i.e., dr(t1,t2), lists all available
	 * rooms having no booking with a dr(x,y) where t1 &le x &le t2 or t1 &le y &le t2 or (x &lt t1 and y &gt t2).
	 *
	 * @param dateRange a date range dr(t1,t2)
	 * @return a (possibly empty) set of rooms
	 */
	public Set<Room> listAvailableRooms(DateRange dateRange);

	/**
	 * Given a date range starting at t1 and ending at t2, i.e., dr(t1,t2), lists all
	 * rooms that are <em>not</em> available.
	 *
	 * @param dateRange a date range dr(t1,t2)
	 * @return a (possibly empty) set of rooms
	 *
	 * @see listAvailableRooms(DateRange)
	 */
	public Set<Room> listBookedRooms(DateRange dateRange);
}
