/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.logic.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.daterange.DateRange;
import ch.bfh.ti.due1.srs.domain.concept.Reservation;
import ch.bfh.ti.due1.srs.domain.concept.Room;
import ch.bfh.ti.due1.srs.logic.svc.RoomController;

public class RoomControllerImpl implements RoomController {
	private Set<Room> rooms = new HashSet<Room>();

	@Override
	public void addRoom(Room room) {
		this.rooms.add(room);
	}

	@Override
	public void removeRoom(Room room) {
		this.rooms.remove(room);
	}

	@Override
	public void updateRoom(Room room) {
		// ?
		throw new UnsupportedOperationException("Not clear what needs to be implemented");
	}

	@Override
	public Set<Room> listRooms() {
		return Collections.unmodifiableSet(this.rooms);
	}

	@Override
	public Set<Room> listAvailableRooms(DateRange dateRange) {
		// TODO Check / test this!!
		Set<Room> availableRooms = new HashSet<>();
		for (Room room: this.rooms) {
			boolean available = true;
			for (Reservation res: room.getReservations()) {
				if (dateRange.overlaps(res.getDateRange())) {
					available = false;
					break;
				}
			}
			if (available) {
				availableRooms.add(room);
			}
		}
		return availableRooms;
	}

	@Override
	public Set<Room> listBookedRooms(DateRange dateRange) {
		// TODO Check / test this!!
		Set<Room> bookedRooms = new HashSet<>();
		for (Room room: this.rooms) {
			for (Reservation res: room.getReservations()) {
				if (dateRange.overlaps(res.getDateRange())) {
					bookedRooms.add(room);
					break;
				}
			}
		}
		return bookedRooms;
	}
}
