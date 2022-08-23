package com.assm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.assm.model.User;
import com.assm.utils.JpaUtils;

public class UserDAO {
	public void create(User User) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(User);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(User User) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(User);
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

			User phim = em.find(User.class, id);
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

	public User findByID(String id) {
		EntityManager em = JpaUtils.getEntityManager();
		User user = em.find(User.class, id);
		return user;
	}
	
	public List<User> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}
	
	public List<User> findAll(int page, int size){
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		query.setFirstResult(page * size);
		query.setMaxResults(size);
		return query.getResultList();
	}
	
	public User checkLogin(String UserId, String password){
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select u from User u where u.Id =:UserId and u.Password =:password";
		TypedQuery<User> query = em.createQuery(jqpl, User.class);
		query.setParameter("UserId", UserId);
		query.setParameter("password", password);
		return query.getSingleResult();
	}
	
	public User findByFullName(String fullName) {
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select u from User u where u.Fullname like :fullname";
		TypedQuery<User> query = em.createQuery(jqpl, User.class);
		query.setParameter("fullname", "%" + fullName + "%");
		return query.getSingleResult();
	}
	
	public int count() {
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select count(u) from User u";
		Query query = em.createQuery(jqpl);
		return ((Long)query.getSingleResult()).intValue();
	}
}
