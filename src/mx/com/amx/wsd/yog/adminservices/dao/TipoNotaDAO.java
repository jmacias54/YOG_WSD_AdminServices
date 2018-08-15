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
import mx.com.amx.wsd.yog.adminservices.model.TipoNota;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class TipoNotaDAO {
private static Logger logger = Logger.getLogger(TipoNotaDAO.class);
	
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	public TipoNota findById(String idTipoNota)throws DAOException {
		logger.debug(" --- findById [ TipoNotaDAO ] --- ");
		logger.debug(" --- idTipoNota : "+idTipoNota+" --- ");
		
		
		List<TipoNota> lista = null;
		StringBuilder query = new StringBuilder();
		query.append("  SELECT * FROM yog_ba_c_tipo_nota WHERE FC_ID_TIPO_NOTA = '" + idTipoNota + "' ");

		try {
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<TipoNota>(TipoNota.class));
			
			
		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findById  [ TipoNotaDAO ] ! ", npe);
			throw new DAOException(npe.getMessage());
		} catch (Exception e) {
			logger.error(" ¡ Error findById  [ TipoNotaDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}
		
		if (lista == null ) {
			return null;
		}
		
		return lista.get(0);

	}
	
	
	
	public List<TipoNota> findAll() throws DAOException {
		logger.debug(" --- findAll [ TipoNotaDAO ] --- ");
		
		
		List<TipoNota> lista = null;
		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM yog_ba_c_tipo_nota ");
		

		try {
			
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(),
					new BeanPropertyRowMapper<TipoNota>(TipoNota.class));

		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findAll  [ TipoNotaDAO ] ! ", npe);
			return Collections.emptyList();
		} catch (Exception e) {
			logger.error(" ¡ Error findAll  [ TipoNotaDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}

		return lista;
	}
}
