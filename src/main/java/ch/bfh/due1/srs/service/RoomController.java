/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ch.bfh.due1.srs.data.DataAccess;
import ch.bfh.due1.srs.data.Room;
import ch.bfh.ti.daterange.DateRange;
import ch.bfh.ti.daterange.DateRangeFactory;

public class RoomController {

	public void addRoom(String name, int capacity) {
		DataAccess dataAccess = DataAccess.getInstance();
		dataAccess.makeRoom(name, capacity);
	}

	public void removeRoom(Room room) {
		DataAccess dataAccess = DataAccess.getInstance();
		dataAccess.removeRoom(room.getId());
	}

	public void updateRoom(Room room) {
		// ?
		throw new UnsupportedOperationException("Not clear what needs to be implemented");
	}

	public List<Room> listRooms() {
		DataAccess dataAccess = DataAccess.getInstance();
		List<Room> rooms = dataAccess.findAllRooms();
		return rooms;
	}

	public List<Room> listAvailableRooms(DateRange dateRange) {
		// TODO Check / test this!!
		// Set<Room> availableRooms = new HashSet<>();
		// for (Room room: this.rooms) {
		// boolean available = true;
		// for (ReservationEntity res: room.getReservations()) {
		// if (dateRange.overlaps(res.getDateRange())) {
		// available = false;
		// break;
		// }
		// }
		// if (available) {
		// availableRooms.add(room);
		// }
		// }
		// return availableRooms;
		return null;
	}

	public List<Room> listBookedRooms(DateRange dateRange) {
		// TODO Check / test this!!
		// Set<Room> bookedRooms = new HashSet<>();
		// for (Room room: this.rooms) {
		// for (ReservationEntity res: room.getReservations()) {
		// if (dateRange.overlaps(res.getDateRange())) {
		// bookedRooms.add(room);
		// break;
		// }
		// }
		// }
		// return bookedRooms;
		return null;
	}

	public List<DateRange> listBookedRoomByDate(String name, Date date) {
		// TODO Replace dummy implementation
		DateRangeFactory dateRangeFactory = new ch.bfh.ti.daterange.impl.pojo.DateRangeFactory();
		LocalDateTime ltBegin = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		Date startTime = Date.from(ltBegin.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime ltEnd = ltBegin.plusHours(1);
		Date endTime = Date.from(ltEnd.atZone(ZoneId.systemDefault()).toInstant());
		DateRange timeslot = dateRangeFactory.createDateRange(startTime, endTime);
		return Collections.singletonList(timeslot);
	}
}
