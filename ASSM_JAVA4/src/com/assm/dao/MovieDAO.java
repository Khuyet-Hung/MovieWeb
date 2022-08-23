package com.assm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.assm.model.Movie;
import com.assm.utils.JpaUtils;

public class MovieDAO {
	public void create(Movie movie) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(movie);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(Movie movie) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(movie);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(String id) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();

			Movie phim = em.find(Movie.class, id);
			if (phim != null) {
				em.remove(phim);
			} else {
				throw new Exception("ID không tồn tại");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public Movie findByID(String id) {
		EntityManager em = JpaUtils.getEntityManager();
		Movie phim = em.find(Movie.class, id);
		return phim;
	}
	
	public List<Movie> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
		return query.getResultList();
	}
	
	public List<Movie> findAll(int page, int size){
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
		query.setFirstResult(page * size);
		query.setMaxResults(size);
		return query.getResultList();
	}
	
	public Movie findByFullName(String fullName) {
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select u from Movie u where u.Fullname like :fullname";
		TypedQuery<Movie> query = em.createQuery(jqpl, Movie.class);
		query.setParameter("fullname", "%" + fullName + "%");
		return query.getSingleResult();
	}
	
	public int count() {
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select count(u) from Movie u";
		Query query = em.createQuery(jqpl);
		return ((Long)query.getSingleResult()).intValue();
	}
}
