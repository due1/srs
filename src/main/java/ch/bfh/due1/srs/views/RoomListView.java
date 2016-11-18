/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.views;

import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.due1.srs.data.Room;
import ch.bfh.due1.srs.service.RoomController;

@SuppressWarnings("serial")
public class RoomListView extends VerticalLayout implements View {

	public static final String VIEWNAME = "RoomListView";

	private Grid grid = new Grid();
	private RoomController roomController;

	public RoomListView(Navigator navigator, RoomController roomController) {
		this.roomController = roomController;
		setSizeFull();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Notification.show("Welcome to the 'Room List View'!");
		List<Room> rooms = this.roomController.listRooms();
		this.grid.setContainerDataSource(new BeanItemContainer<>(Room.class, rooms));
		addComponent(this.grid);
	}
}
