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
import mx.com.amx.wsd.yog.adminservices.model.CMSUsuario;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class CMSUsuarioDAO {
private static Logger logger = Logger.getLogger(CMSUsuarioDAO.class);
	
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	

	
	public CMSUsuario findByCN(String cn)throws DAOException {
		logger.debug(" --- findByCN [ CMSUsuarioDAO ] --- ");
		logger.debug(" --- cn : "+cn+" --- ");
		
		
		List<CMSUsuario> lista = null;
		StringBuilder query = new StringBuilder();
		query.append("  SELECT * FROM cms_c_usuario WHERE FC_CN = '" + cn + "' ");

		try {
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<CMSUsuario>(CMSUsuario.class));
			
			
		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findByCN  [ CMSUsuarioDAO ] ! ", npe);
			throw new DAOException(npe.getMessage());
		} catch (Exception e) {
			logger.error(" ¡ Error findByCN  [ CMSUsuarioDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}
		
		if (lista == null ) {
			return null;
		}
		
		return lista.get(0);

	}
	
	
	
	public List<CMSUsuario> findAll() throws DAOException {
		logger.debug(" --- findAll [ CMSUsuarioDAO ] --- ");
		
		
		List<CMSUsuario> lista = null;
		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM cms_c_usuario ");
		

		try {
			
			
			logger.debug(" query : "+query.toString());
			lista = jdbcTemplate.query(query.toString(),
					new BeanPropertyRowMapper<CMSUsuario>(CMSUsuario.class));

		} catch (NullPointerException npe) {
			logger.error(" ¡ Error findAll  [ CMSUsuarioDAO ] ! ", npe);
			return Collections.emptyList();
		} catch (Exception e) {
			logger.error(" ¡ Error findAll  [ CMSUsuarioDAO ] ! ", e);
			throw new DAOException(e.getMessage());
		}

		return lista;
	}


}
