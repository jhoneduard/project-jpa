/**
 * 
 */
package com.eduard.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.eduard.dao.SubGeneroDAO;
import com.eduard.entity.SubGenero;

/**
 * @author trian Interface DAO que realiza el CRUD con JPA para la tabla de
 *         subgenero
 */
public class SubGeneroDAOImpl implements SubGeneroDAO {

	/*
	 * La clase entity manager factory va permitir obtener la informacion de la
	 * conexion, permitiendo continuar con las transacciones a la base de datos.
	 */
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("persistenceDevPredator");

	@Override
	public void guardar(SubGenero subGenero) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(subGenero);
			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void actualizar(SubGenero subGenero) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(subGenero);
			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void eliminar(Long id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		SubGenero subGeneroConsultado = em.find(SubGenero.class, id);
		try {
			et.begin();
			em.remove(subGeneroConsultado);
			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public List<SubGenero> consultar() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		TypedQuery<SubGenero> queryTyped = (TypedQuery<SubGenero>) em
				.createQuery("FROM SubGenero ORDER BY descripcion");
		return queryTyped.getResultList();
	}

	@Override
	public SubGenero consultarById(Long id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		return em.find(SubGenero.class, id);
	}

}
