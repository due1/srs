/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import ch.bfh.due1.srs.service.DataGenerator;
import ch.bfh.due1.srs.service.ReservationController;
import ch.bfh.due1.srs.service.RoomController;
import ch.bfh.due1.srs.views.ReservationView;
import ch.bfh.due1.srs.views.RoomListView;

@SuppressWarnings("serial")
public class MainUI extends UI {
	private Navigator navigator;
	public static final String MAINVIEW = "Main";

	private RoomController roomController = new RoomController();
	private ReservationController reservationController = new ReservationController();

	static {
		DataGenerator.create();
	}

	@Override
	protected void init(VaadinRequest request) {
		//
		getPage().setTitle("Smart Reservation System");
		this.navigator = new Navigator(this, this);
		this.navigator.addView(RoomListView.VIEWNAME, new RoomListView(navigator, roomController));
		this.navigator.addView(ReservationView.VIEWNAME,
				new ReservationView(navigator, roomController, reservationController));

		//
		this.navigator.navigateTo(ReservationView.VIEWNAME);

	}

	@WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
