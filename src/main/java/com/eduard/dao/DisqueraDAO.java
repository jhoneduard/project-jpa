/**
 * 
 */
package com.eduard.dao;

import java.util.List;

import com.eduard.entity.Disquera;

/**
 * @author trian
 *
 *         Interface que genera el DAO para las transacciones del CRUD a la
 *         tabla de disquera
 */
public interface DisqueraDAO {
	void guardar(Disquera disquera);

	void actualizar(Disquera disquera);

	void elininar(Long id);

	List<Disquera> consultar();

	Disquera consultarById(Long id);

}
