/**
 * 
 */
package com.eduard.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.eduard.dao.DisqueraDAO;
import com.eduard.entity.Disquera;

/**
 * @author trian Clase que implementa las transacciones para la tabla de
 *         Disquera
 */
public class DisqueraDAOImpl implements DisqueraDAO {

	/*
	 * La clase entity manager factory va permitir obtener la informacion de la
	 * conexion, permitiendo continuar con las transacciones a la base de datos.
	 */
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("persistenceDevPredator");

	@Override
	public void guardar(Disquera disquera) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		// Siempre que vamos a hacer una transsaccion que altere la informacion de la
		// bd, utilizamos el EntityTransaction
		// PAra habilitar la transaccion
		EntityTransaction et = em.getTransaction();
		et.begin();

		try {

			em.persist(disquera);
			et.commit();
		} catch (Exception ex) {
			/* El rollback permitira devolver Rechazar el insert en la base de datos... */
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			/* Siempre se desconecte la conexion a la bd, al finalizar el try y cath.. */
		}
	}

	@Override
	public void actualizar(Disquera disquera) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		// Siempre que vamos a hacer una transsaccion que altere la informacion de la
		// bd, utilizamos el EntityTransaction
		// PAra habilitar la transaccion
		EntityTransaction et = em.getTransaction();
		et.begin();

		try {
			em.merge(disquera);
			et.commit();
		} catch (Exception ex) {
			/* El rollback permitira devolver Rechazar el insert en la base de datos... */
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			/* Siempre se desconecte la conexion a la bd, al finalizar el try y cath.. */
		}
	}

	@Override
	public void elininar(Long id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

		Disquera disqueraConsultada = em.find(Disquera.class, id);

		// Siempre que vamos a hacer una transsaccion que altere la informacion de la
		// bd, utilizamos el EntityTransaction
		// PAra habilitar la transaccion
		EntityTransaction et = em.getTransaction();
		et.begin();

		try {
			em.remove(disqueraConsultada);
			et.commit();
		} catch (Exception ex) {
			/* El rollback permitira devolver Rechazar el insert en la base de datos... */
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			/* Siempre se desconecte la conexion a la bd, al finalizar el try y cath.. */
		}
	}

	@Override
	public List<Disquera> consultar() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

		TypedQuery<Disquera> queryDisquera = (TypedQuery<Disquera>) em
				.createQuery("FROM Disquera ORDER BY descripcion");
		return queryDisquera.getResultList();
	}

	@Override
	public Disquera consultarById(Long id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

		// Clase y primary Key
		Disquera disqueraConsultado = em.find(Disquera.class, id);

		if (disqueraConsultado == null) {
			throw new EntityNotFoundException("La disquera con id " + id + " no se encontro");
		}
		return disqueraConsultado;
	}

	@Override
	public Disquera consultarByDescripcionJPQL(String descripcion) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		TypedQuery<Disquera> queryDisquera = (TypedQuery<Disquera>) em
				.createQuery("FROM Disquera WHERE descripcion =:desc");
		queryDisquera.setParameter("desc", descripcion);

		// Regresamos un solo resultado
		return queryDisquera.getSingleResult();
	}

	@Override
	public Disquera consultarByDescripcionNative(String descripcion) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		TypedQuery<Disquera> queryDisquera = (TypedQuery<Disquera>) em
				.createNativeQuery("SELECT * FROM disquera WHERE descripcion =?", Disquera.class);
		queryDisquera.setParameter(1, descripcion);

		// Regresamos un solo resultado
		return queryDisquera.getSingleResult();
	}

}
