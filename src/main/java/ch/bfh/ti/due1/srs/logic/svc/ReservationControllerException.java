/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.due1.srs.logic.svc;

/**
 * An exception that can be raised by a ReservationController instance.
 *
 * @author due1
 */
public class ReservationControllerException extends Exception {

	/**
	 * Not really used.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an instance of this exception.
	 *
	 * @param msg a message indicating the cause of the exception.
	 */
	public ReservationControllerException(String msg) {
		super(msg);
	}

	/**
	 * Creates an instance of this exception.
	 *
	 * @param msg a message indicating the cause of the exception
	 * @param e an exception which in turn raised this exception
	 */
	public ReservationControllerException(String msg, Exception e) {
		super(msg, e);
	}
}
