/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.srs.data.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.bfh.due1.srs.data.DataAccess;
import ch.bfh.due1.srs.data.Person;
import ch.bfh.due1.srs.data.Reservation;
import ch.bfh.due1.srs.data.Room;
import ch.bfh.ti.daterange.DateRange;

public class JPADataAccess extends DataAccess {
	public static final String PERSISTENCE_UNIT = "srs-pu";
	private EntityManager entityManager;

	public JPADataAccess() {
		this.entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
	}

	@Override
	public Person makePerson(String name, String email) {
		this.entityManager.getTransaction().begin();
		PersonEntity person = new PersonEntity(name, email);
		this.entityManager.persist(person);
		this.entityManager.getTransaction().commit();
		return person;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findAllPersons() {
		Query query = this.entityManager.createQuery("select p from Person p");
		return query.getResultList();
	}

	@Override
	public void removePerson(Long id) {
		this.entityManager.getTransaction().begin();
		PersonEntity person = this.entityManager.find(PersonEntity.class, id);
		this.entityManager.remove(person);
		this.entityManager.getTransaction().commit();

	}

	@Override
	public Room makeRoom(String name, int capacity) {
		this.entityManager.getTransaction().begin();
		RoomEntity room = new RoomEntity(name, capacity);
		this.entityManager.persist(room);
		this.entityManager.getTransaction().commit();
		return room;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> findAllRooms() {
		Query query = this.entityManager.createQuery("select r from Room r");
		return query.getResultList();
	}

	@Override
	public void removeRoom(Long id) {
		this.entityManager.getTransaction().begin();
		RoomEntity room = this.entityManager.find(RoomEntity.class, id);
		this.entityManager.remove(room);
		this.entityManager.getTransaction().commit();
	}

	@Override
	public Reservation makeReservation(Person person, Room room, DateRange dateRange) {
		this.entityManager.getTransaction().begin();
		ReservationEntity reservation = new ReservationEntity(person, room, dateRange);
		this.entityManager.persist(reservation);
		person.addReservation(reservation);
		room.addReservation(reservation);
		this.entityManager.merge(person);
		this.entityManager.merge(room);
		this.entityManager.getTransaction().commit();
		return reservation;
	}
}
