/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.views;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.due1.srs.service.RoomController;

@SuppressWarnings("serial")
public class ReservationView extends VerticalLayout implements View {
	public static final String VIEWNAME = "ReservationView";
	private TabSheet tabsheet = new TabSheet();
	@SuppressWarnings("unused")
	private RoomController roomController;
	private Table table;

	public ReservationView(Navigator navigator, RoomController roomController) {
		this.roomController = roomController;
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
				String caption = tabsheet.getTab(tab).getCaption();

				// Fill the tab content
				tab.removeAllComponents();
				table = createTimeSlotTable(caption);
				tab.addComponent(table);
			}
		});

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Notification.show("Welcome to the 'Reservation View'!");
		String[] tabs = { "N215", "N315", "N632", "N721" };
		for (String caption : tabs) {
			this.tabsheet.addTab(new VerticalLayout(), caption);
		}
	}

	private Table createTimeSlotTable(String caption) {
		Table table = new Table("Reservation Table for " + caption);
		// first column denotes the time
		table.addContainerProperty("Time", String.class, null);

		// columns for work days
		String workDays[] = { "Mon", "Tue", "Wed", "Thu", "Fri" };
		for (String name : workDays) {
			table.addContainerProperty(name, String.class, null);
		}

		// Populate table cells
		for (int i = 0; i < 12; i++) {
			int s = 8 + i;

			table.addItem(new String[] { new Integer(s).toString() + ":00 - " + new Integer(s + 1).toString() + ":00",
					"", "", "", "XXX", "" }, new Integer(i + 8));
		}
		table.setPageLength(table.size());
		@SuppressWarnings("unused")
		IndexedContainer c = (IndexedContainer) table.getContainerDataSource();
		// TODO Complete...

		return table;
	}
}
