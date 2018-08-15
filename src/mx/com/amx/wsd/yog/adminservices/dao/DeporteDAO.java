/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsd.yog.adminservices.dao;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.amx.wsd.yog.adminservices.dao.exception.DAOException;
import mx.com.amx.wsd.yog.adminservices.model.Deporte;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class DeporteDAO {
private static Logger logger = Logger.getLogger(DeporteDAO.class);
	
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	public Deporte findById(String idDeporte )throws DAOException {
		logger.debug(" --- findById [ DeporteDAO ] --- ");
		logger.debug(" --- idCategoria : "+idDeporte+" --- ");
		
		
		List<Deporte> lista = null;
		StringBuilder query = new StringBuilder();
		query.append("  SELECT * FROM yog_ba_c_deporte WHERE FC_ID_DEPORTE = '" + idDeporte + "' ");

		try {
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<Deporte>(Deporte.class));
			
			
		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findById  [ DeporteDAO ] ! ", npe);
			throw new DAOException(npe.getMessage());
		} catch (Exception e) {
			logger.error(" ¡ Error findById  [ DeporteDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}
		
		if (lista == null ) {
			return null;
		}
		
		return lista.get(0);

	}
	
	
	
	public List<Deporte> findAll() throws DAOException {
		logger.debug(" --- findAll [ DeporteDAO ] --- ");
		
		
		List<Deporte> lista = null;
		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM yog_ba_c_deporte ");
		

		try {
			
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(),
					new BeanPropertyRowMapper<Deporte>(Deporte.class));

		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findAll  [ DeporteDAO ] ! ", npe);
			return Collections.emptyList();
		} catch (Exception e) {
			logger.error(" ¡ Error findAll  [ DeporteDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}

		return lista;
	}
}
