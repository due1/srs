/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.service;

import java.util.List;

import ch.bfh.due1.srs.data.DataAccess;
import ch.bfh.due1.srs.data.Room;

public class RoomController {

	public Room addRoom(String name, int capacity) {
		DataAccess dataAccess = DataAccess.getInstance();
		return dataAccess.makeRoom(name, capacity);
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
}
