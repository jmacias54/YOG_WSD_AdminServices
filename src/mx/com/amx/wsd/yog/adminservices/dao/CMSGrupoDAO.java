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
import mx.com.amx.wsd.yog.adminservices.model.CMSGrupo;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class CMSGrupoDAO {

private static Logger logger = Logger.getLogger(CMSGrupoDAO.class);
	
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	

	
	public CMSGrupo findByIdGrupo(String idGrupo)throws DAOException {
		logger.debug(" --- findByIdGrupo [ CMSGrupoDAO ] --- ");
		logger.debug(" --- idGrupo : "+idGrupo+" --- ");
		
		
		List<CMSGrupo> lista = null;
		StringBuilder query = new StringBuilder();
		query.append("  SELECT * FROM cms_c_grupo WHERE FC_ID_GRUPO = '" + idGrupo + "' ");

		try {
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<CMSGrupo>(CMSGrupo.class));
			
			
		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findByIdGrupo  [ CMSGrupoDAO ] ! ", npe);
			throw new DAOException(npe.getMessage());
		} catch (Exception e) {
			logger.error(" ¡ Error findByIdGrupo  [ CMSGrupoDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}
		
		if (lista == null ) {
			return null;
		}
		
		return lista.get(0);

	}
	
	
	
	public List<CMSGrupo> findAll() throws DAOException {
		logger.debug(" --- findAll [ CMSGrupoDAO ] --- ");
		
		
		List<CMSGrupo> lista = null;
		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM cms_c_grupo ");
		

		try {
			
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(),
					new BeanPropertyRowMapper<CMSGrupo>(CMSGrupo.class));

		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findAll  [ CMSGrupoDAO ] ! ", npe);
			return Collections.emptyList();
		} catch (Exception e) {
			logger.error(" ¡ Error findAll  [ CMSGrupoDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}

		return lista;
	}

}
