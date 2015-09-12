package edu.msg.ticketmaster.backend.repository.beans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.msg.ticketmaster.backend.model.AbstractModel;
import edu.msg.ticketmaster.backend.repository.BaseRepository;

public abstract class BaseRepositoryBean<T extends AbstractModel, ID> implements BaseRepository<T, ID> {

	@PersistenceContext(unitName = "ticketmaster-backend")
	private EntityManager entityManager;

	private Class<T> clazz;

	public BaseRepositoryBean(Class<T> clazz) {
		// EntityManagerFactory emf = Persistence
		// .createEntityManagerFactory("um-backend");
		// EntityManager em = emf.createEntityManager();
		// entityManager = em;
		this.clazz = clazz;

	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Query query = entityManager.createQuery("SELECT e FROM " + clazz.getName() + " e");
		return query.getResultList();
	}

	@Override
	public T findByID(ID id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public void persist(T entity) {
		entityManager.persist(entity);

	}

	public void deactivateMovie(T entity) {

		entityManager.merge(entity);

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}