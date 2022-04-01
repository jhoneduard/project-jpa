/**
 * 
 */
package com.eduard.dao.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.eduard.dao.SubGeneroDAO;
import com.eduard.dao.impl.SubGeneroDAOImpl;
import com.eduard.entity.Genero;
import com.eduard.entity.SubGenero;

/**
 * @author trian Clase TEST para comprobar el funcionamiento de los metodos del
 *         CRUD de subgenero
 */
public class SubGeneroDAOImplTest {

	private SubGeneroDAO subGeneroDAO = new SubGeneroDAOImpl();

	/**
	 * Test method for
	 * {@link com.eduard.dao.impl.SubGeneroDAOImpl#guardar(com.eduard.entity.SubGenero)}.
	 */
	@Test
	public void testGuardar() {
		SubGenero subGenero = new SubGenero();
		subGenero.setDescripcion("Hard Core");
		subGenero.setFechaCreacion(LocalDateTime.now());
		subGenero.setEstatus(true);

		Genero genero = new Genero();
		genero.setDescripcion("Metal");
		genero.setFechaCreacion(LocalDateTime.now());
		genero.setEstatus(true);

		subGenero.setGenero(genero);
		this.subGeneroDAO.guardar(subGenero);
	}

	/**
	 * Test method for
	 * {@link com.eduard.dao.impl.SubGeneroDAOImpl#actualizar(com.eduard.entity.SubGenero)}.
	 */
	@Test
	public void testActualizar() {
		SubGenero subGeneroConsultado = this.subGeneroDAO.consultarById(8L);
		subGeneroConsultado.setDescripcion("Trash MEtal");
		subGeneroConsultado.getGenero().setDescripcion("METAL TRASH");
		this.subGeneroDAO.actualizar(subGeneroConsultado);
	}

	/**
	 * Test method for
	 * {@link com.eduard.dao.impl.SubGeneroDAOImpl#eliminar(java.lang.Long)}.
	 */
	@Test
	public void testEliminar() {
		this.subGeneroDAO.eliminar(8L);
	}

	/**
	 * Test method for {@link com.eduard.dao.impl.SubGeneroDAOImpl#consultar()}.
	 */
	@Test
	public void testConsultar() {
		List<SubGenero> subGenerosConsultados = this.subGeneroDAO.consultar();
		assertTrue(subGenerosConsultados.size() > 0);
		for (SubGenero subGenero : subGenerosConsultados) {
			System.out.println("SubGenero : " + subGenero.getDescripcion());
			System.out.println("Genero : " + subGenero.getGenero().getDescripcion());
		}
	}

	/**
	 * Test method for
	 * {@link com.eduard.dao.impl.SubGeneroDAOImpl#consultarById(java.lang.Long)}.
	 */
	@Test
	public void testConsultarById() {
		fail("Not yet implemented");
	}

}
