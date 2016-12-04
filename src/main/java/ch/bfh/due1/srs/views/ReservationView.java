/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.views;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.due1.srs.data.Reservation;
import ch.bfh.due1.srs.data.Room;
import ch.bfh.due1.srs.service.ReservationController;
import ch.bfh.due1.srs.service.RoomController;
import ch.bfh.due1.time.DateTimeFactory;
import ch.bfh.due1.time.TimeSlot;

@SuppressWarnings("serial")
public class ReservationView extends VerticalLayout implements View {
	public static final String VIEWNAME = "ReservationView";
	public static final int DaysOfWeekToDisplay = 7; // 5 or 7
	public static final String[] DayNames = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
	private TabSheet tabsheet = new TabSheet();
	private RoomController roomController;
	private ReservationController reservationController;
	private Table table;
	private List<Room> rooms; // List of rooms to be displayed in tab view

	public ReservationView(Navigator navigator, RoomController roomController,
			ReservationController reservationController) {
		this.roomController = roomController;
		this.reservationController = reservationController;
		// setSizeFull();
		addComponent(this.tabsheet);
		// Button button = new Button("Go to Room List View", new
		// Button.ClickListener() {
		// @Override
		// public void buttonClick(ClickEvent event) {
		// navigator.navigateTo(RoomListView.VIEWNAME);
		// }
		// });
		// addComponent(button);
		// setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		this.tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				// Find the tab sheet
				TabSheet tabsheet = event.getTabSheet();

				// Find the tab (it's a layout)
				Layout tab = (Layout) tabsheet.getSelectedTab();

				// Get the tab caption from the tab object
				String roomName = tabsheet.getTab(tab).getCaption();

				// Fill the tab content
				tab.removeAllComponents();
				// Create table for selected room
				table = createTimeSlotTable(roomName);
				tab.addComponent(table);
			}
		});

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Notification.show("Welcome to the 'Reservation View'!");
		this.rooms = this.roomController.listRooms();
		for (Room r : rooms) {
			this.tabsheet.addTab(new VerticalLayout(), r.getName());
		}
	}

	private Table createTimeSlotTable(String roomName) {
		Table table = new Table("Reservation Table for " + roomName);
		// first column denotes the time
		table.addContainerProperty("Time", String.class, null);

		// columns for work days
		for (int i = 0; i < DaysOfWeekToDisplay; i++) {
			table.addContainerProperty(DayNames[i], String.class, null);
		}

		// Populate table cells We have 16 time slots starting from 8 am to 12
		// pm.
		int timeSlotCount = 16;
		for (int i = 0; i < timeSlotCount; i++) {
			// Since rooms are displayed from left to right, for each row we
			// have to iterate over each room.
			int startOfFirstTimeSlot = 8; // 8 pm
			int s = startOfFirstTimeSlot + i;
			// Find room with given name:
			Room selectedRoom = null;
			for (Room r : this.rooms) {
				if (r.getName().equals(roomName)) {
					selectedRoom = r;
					break;
				}
			}
			// assert selected room <> null
			// TODO Check necessity of error handling for the case that selected
			// room == null

			String[] rV = getReservationRow(selectedRoom, s);
			String[] itemString = new String[DaysOfWeekToDisplay + 1]; // plus
																		// time
																		// column
			itemString[0] = new Integer(s).toString() + ":00 - " + new Integer(s + 1).toString() + ":00";
			for (int j = 1; j <= DaysOfWeekToDisplay; j++) {
				itemString[j] = rV[j - 1];
			}
			table.addItem(itemString, new Integer(i + 8).toString());
		}
		table.setPageLength(table.size());
		@SuppressWarnings("unused")
		IndexedContainer c = (IndexedContainer) table.getContainerDataSource();
		// TODO Complete...

		return table;
	}

	private String[] getReservationRow(Room r, int startHour) {
		LocalDateTime today = DateTimeFactory.roundTo(LocalDateTime.now(), DateTimeFactory.TimeSlotTypeSpec.DAYS);
		DayOfWeek dow = today.getDayOfWeek();
		int dayOffset;
		if (dow.equals(DayOfWeek.MONDAY)) {
			dayOffset = 0;
		} else if (dow.equals(DayOfWeek.TUESDAY)) {
			dayOffset = 1;
		} else if (dow.equals(DayOfWeek.WEDNESDAY)) {
			dayOffset = 2;
		} else if (dow.equals(DayOfWeek.THURSDAY)) {
			dayOffset = 3;
		} else if (dow.equals(DayOfWeek.FRIDAY)) {
			dayOffset = 4;
		} else if (dow.equals(DayOfWeek.SATURDAY)) {
			dayOffset = 5;
		} else {
			// Sunday
			dayOffset = 6;
		}
		// Let's iterate from Monday to Friday (or Sunday)
		// Let's start with day == Monday
		LocalDateTime day = today.minusDays(dayOffset);
		int noOfWorkDays = DaysOfWeekToDisplay;
		String[] reservationMarks = new String[noOfWorkDays];
		for (int delta = 0; delta < noOfWorkDays; delta++) {
			LocalDateTime currentDay = day.plusDays(delta);
			// Find reservations for each startHour
			LocalDateTime startTime = currentDay.plusHours(startHour);
			LocalDateTime endTime = startTime.plusHours(1);
			TimeSlot ts = this.reservationController.getTimeSlotFactory().createTimeSlot(startTime, endTime);
			if (booked(r, ts)) {
				reservationMarks[delta] = "XXX";
			} else {
				reservationMarks[delta] = "";
			}
		}
		return reservationMarks;
	}

	private boolean booked(Room r, TimeSlot ts) {
		List<Reservation> reservations = r.getReservations();
		boolean booked = false;
		for (Reservation res : reservations) {
			if (ts.overlaps(res.getTimeSlot())) {
				booked = true;
				break;
			}
		}
		return booked;
	}
}
