/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.logic.svc;

import ch.bfh.ti.due1.srs.logic.impl.ReservationControllerImpl;
import ch.bfh.ti.due1.srs.logic.impl.RoomControllerImpl;

/**
 * Given an instance, allows to obtain various types of controllers.
 *
 * @author due1
 */
public class ControllerFactory {
	// TODO Introduce indirection:
	//public static final String ReservationControllerClassNaem = "";

	/**
	 * Returns a ReservationController instance. If none exists prior
	 * to this call then an instance will be created. It is an
	 * implementation detail if there is at most one instance or
	 * if there could exist many of them.
	 *
	 * @return a ReservationController instance
	 */
	public ReservationController getReservationController() {
		return new ReservationControllerImpl();
	}

	/**
	 * Returns a RoomManager instance. If none exists prior
	 * to this call then an instance will be created. It is an
	 * implementation detail if there is at most one instance or
	 * if there could exist many of them.
	 *
	 * @return a RoomManager instance
	 */
	public RoomController getRoomController() {
		return new RoomControllerImpl();
	}
}
