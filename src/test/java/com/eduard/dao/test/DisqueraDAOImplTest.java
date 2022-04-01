/**
 * 
 */
package com.eduard.dao.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.eduard.dao.DisqueraDAO;
import com.eduard.dao.impl.DisqueraDAOImpl;
import com.eduard.entity.Disquera;

/**
 * @author trian
 *
 */
public class DisqueraDAOImplTest {

	private DisqueraDAO disqueraDAO = new DisqueraDAOImpl();

	/**
	 * Test method for
	 * {@link com.eduard.dao.impl.DisqueraDAOImpl#guardar(com.eduard.entity.Disquera)}.
	 */
	@Test
	public void testGuardar() {
		Disquera disquera = new Disquera();
		disquera.setDescripcion("Megaforce");
		disquera.setFechaCreacion(LocalDateTime.now());
		disquera.setEstatus(true);
		this.disqueraDAO.guardar(disquera);
	}

	/**
	 * Test method for
	 * {@link com.eduard.dao.impl.DisqueraDAOImpl#actualizar(com.eduard.entity.Disquera)}.
	 */
	@Test
	public void testActualizar() {
		Disquera disqueraConsultada = this.disqueraDAO.consultarById(9L);
		disqueraConsultada.setDescripcion("Disquera IronMaiden");
		this.disqueraDAO.actualizar(disqueraConsultada);
	}

	/**
	 * Test method for
	 * {@link com.eduard.dao.impl.DisqueraDAOImpl#elininar(com.eduard.entity.Disquera)}.
	 */
	@Test
	public void testElininar() {
		Long id = 9L;
		this.disqueraDAO.elininar(id);
	}

	/**
	 * Test method for {@link com.eduard.dao.impl.DisqueraDAOImpl#consultar()}.
	 */
	@Test
	public void testConsultar() {
		List<Disquera> disquerasConsultadas = this.disqueraDAO.consultar();

		assertTrue(disquerasConsultadas.size() > 0);

		disquerasConsultadas.forEach(disquera -> {
			System.out.println("Disquera : " + disquera.getDescripcion());
		});

	}

	/**
	 * Test method for
	 * {@link com.eduard.dao.impl.DisqueraDAOImpl#consultarById(java.lang.Long)}.
	 */
	@Test
	public void testConsultarById() {
		Disquera disquera = this.disqueraDAO.consultarById(19L);
		System.out.println("Disquera Descripcion " + disquera.getDescripcion());
	}

}
