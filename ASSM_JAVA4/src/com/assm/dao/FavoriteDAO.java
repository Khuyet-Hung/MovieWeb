package com.assm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.assm.model.Favorite;
import com.assm.model.Movie;
import com.assm.utils.JpaUtils;

public class FavoriteDAO {

	EntityManager em = JpaUtils.getEntityManager();
	EntityTransaction trans = em.getTransaction();
	
	public void create(Favorite favorite) {
		try {
			trans.begin();
			em.persist(favorite);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
	
	public void delete(Long key){
		try {
			trans.begin();
			Favorite Favorite = em.find(Favorite.class,key);
			if(Favorite!=null) {
				em.remove(Favorite);
			}else {
				throw new Exception("This Favorite does not exist!");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}finally {
			em.close();
		}
	}
	
public void update(Favorite entity) {
		
		try {
			trans.begin();
			em.merge(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
		
	}

	public Favorite findLike(String movieId, String userId) {
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "Select f from Favorite f where f.user.userId = :userID and f.movie.id = :movieId";
		TypedQuery<Favorite> query = em.createQuery(jqpl, Favorite.class);
		query.setParameter("userID", userId);
		query.setParameter("movieId",movieId);
		List<Favorite> result = query.getResultList();
		if(result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
}
