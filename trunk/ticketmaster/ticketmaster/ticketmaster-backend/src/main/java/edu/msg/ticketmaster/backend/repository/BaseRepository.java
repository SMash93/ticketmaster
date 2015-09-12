package edu.msg.ticketmaster.backend.repository;

import java.util.List;

import edu.msg.ticketmaster.backend.model.AbstractModel;

public interface BaseRepository<T extends AbstractModel, ID> {

	/**
	 * Remove a persistent instance from the datastore. The argument may be an
	 * instance associated with the receiving Session or a transient instance
	 * with an identifier associated with existing persistent state
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * Gets all the objects of class T from the database
	 * 
	 * @param clazz
	 * @return a list of all the entries in the database of type T
	 */
	public List<T> findAll();

	/**
	 * Find an element of type T with the specified id
	 * 
	 * @param clazz
	 * @param id
	 * @return T element with id equal to the parameter id
	 */
	public T findByID(ID id);

	/**
	 * Make a transient instance of and object of type T persistent.
	 * 
	 * @param entity
	 */
	public void persist(T entity);

	/**
	 * Update the persistent instance with the identifier of the given detached
	 * instance.
	 * 
	 * @param entity
	 */
	public void update(T entity);
}