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
import mx.com.amx.wsd.yog.adminservices.model.EstatusNota;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class EstatusNotaDAO {
private static Logger logger = Logger.getLogger(EstatusNotaDAO.class);
	
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	
	public EstatusNota findById(String idEstatusNota)throws DAOException {
		logger.debug(" --- findById [ EstatusNotaDAO ] --- ");
		logger.debug(" --- idEstatusNota : "+idEstatusNota+" --- ");
		
		
		List<EstatusNota> lista = null;
		StringBuilder query = new StringBuilder();
		query.append("  SELECT * FROM yog_ba_c_estatus_nota WHERE FC_ID_ESTATUS_NOTA = '" + idEstatusNota + "' ");

		try {
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<EstatusNota>(EstatusNota.class));
			
			
		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findById  [ EstatusNotaDAO ] ! ", npe);
			throw new DAOException(npe.getMessage());
		} catch (Exception e) {
			logger.error(" ¡ Error findById  [ EstatusNotaDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}
		
		if (lista == null ) {
			return null;
		}
		
		return lista.get(0);

	}
	
	
	
	public List<EstatusNota> findAll() throws DAOException {
		logger.debug(" --- findAll [ EstatusNotaDAO ] --- ");
		
		
		List<EstatusNota> lista = null;
		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM yog_ba_c_estatus_nota ");
		

		try {
			
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(),
					new BeanPropertyRowMapper<EstatusNota>(EstatusNota.class));

		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findAll  [ EstatusNotaDAO ] ! ", npe);
			return Collections.emptyList();
		} catch (Exception e) {
			logger.error(" ¡ Error findAll  [ EstatusNotaDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}

		return lista;
	}

}
