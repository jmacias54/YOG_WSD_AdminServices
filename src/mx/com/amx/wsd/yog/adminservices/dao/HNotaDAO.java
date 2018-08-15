/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsd.yog.adminservices.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.amx.wsd.yog.adminservices.dao.exception.DAOException;
import mx.com.amx.wsd.yog.adminservices.model.request.ItemsFilterRequest;
import mx.com.amx.wsd.yog.adminservices.model.request.ItemsRequest;
import mx.com.amx.wsd.yog.adminservices.model.request.ItemsRequestByTitle;
import mx.com.amx.wsd.yog.adminservices.model.response.ItemsResponse;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class HNotaDAO {
private static Logger logger = Logger.getLogger(HNotaDAO.class);
	
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	

	/**
	 * Gets the list items by filter.
	 *
	 * @param ItemsFilterRequest
	 * @return List<ItemsResponse>
	 * @throws DAOException
	 */
	public List<ItemsResponse> getListItemsByFilter(ItemsFilterRequest req) throws DAOException {
		logger.debug(" --- getListItemsByFilter  [ HNotaDAO ]  --- " );
		logger.debug(" --- request :  "+req.toString()+"  --- " );
		
		
		List<ItemsResponse> lista = null;
		StringBuilder query = new StringBuilder();

		int limit = req.getLimit();
		int page = req.getPage();

		if (limit == 0 || page == 0) {
			return null;
		}

		int to = limit * page;
		int from = (to - limit) + 1;

		query.append("  SELECT * FROM (SELECT @rownum:=@rownum+1 rank , q.* ");
		query.append(" 		FROM (   SELECT n.FC_ID_CONTENIDO AS id  , ");
		query.append(" 						n.FC_TITULO AS title, ");
        query.append("        			    n.FC_CN AS user , ");
		query.append(" 						n.FC_DESCRIPCION AS description , ");
		query.append(" 						n.FD_FECHA_PUBLICACION AS date   , ");
		query.append(" 						n.FC_ID_TIPO_NOTA AS typeItem, ");
		query.append(" 						n.FC_FRIENDLY_URL  AS url_item , ");
		query.append(" 						n.FC_ID_ESTATUS_NOTA AS status  , ");
		query.append(" 						n.FC_IMAGEN_PRINCIPAL AS image   , ");
		query.append(" 						categoria.FC_ID_CATEGORIA AS idCategories , ");
		query.append(" 						categoria.FC_DESCRIPCION AS descCategories, ");
		query.append(" 						deporte.FC_DESCRIPCION AS descSport, ");
		query.append(" 						deporte.FC_ID_DEPORTE AS idSport ");
		query.append(" 				FROM  yog_ba_h_nota n       ");
		query.append(" 				LEFT JOIN yog_ba_c_categoria categoria ON n.FC_ID_CATEGORIA = categoria.FC_ID_CATEGORIA    ");
		query.append(" 				LEFT JOIN yog_ba_c_deporte deporte ON n.FC_ID_DEPORTE = deporte.FC_ID_DEPORTE  ");
		query.append(" 		  		 WHERE  1=1   ");
		
		if (req.getType().equals("categoria")) {
			query.append("     AND categoria.FC_ID_CATEGORIA = '" + req.getId() + "' ");
		}
		
		
		if (req.getType().equals("deporte")) {
			query.append("     AND deporte.FC_ID_DEPORTE = '" + req.getId() + "' ");
		}
		
		if (req.getAuthor() != null && !req.getAuthor().equals(""))
			query.append(" 	   AND n.FC_CN = '" + req.getAuthor() + "' ");


		if (req.getDateFrom() != null && !req.getDateFrom().equals(""))
			query.append(" 	    AND n.FD_FECHA_PUBLICACION >= '" + req.getDateFrom() + "' ");

		if (req.getDateTo() != null && !req.getDateTo().equals(""))
			query.append("      AND n.FD_FECHA_PUBLICACION <= '" + req.getDateTo() + "' ");

		if (req.getStatus() != null && !req.getStatus().equals(""))
			query.append("      AND n.FC_ID_ESTATUS_NOTA = '" + req.getStatus() + "' ");

		query.append(" ORDER BY FD_FECHA_PUBLICACION DESC ) AS q ,(SELECT @rownum:=0) num ) r  ");
		query.append(" WHERE r.rank >= " + from + " AND r.rank <= " + to + " ");
		
		
		
		try {

			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<ItemsResponse>(ItemsResponse.class));

		} catch (Exception e) {

			logger.error(" --- Error getListItemsByFilter  [ HNotaDAO ] : ", e);

			throw new DAOException(e.getMessage());

		}

		return lista;

	}

	/**
	 * Gets the list items by title.
	 *
	 * @param ItemsRequestByTitle
	 * @return List<ItemsResponse>
	 * @throws DAOException
	 */
	public List<ItemsResponse> getListItemsByTitle(ItemsRequestByTitle req) throws DAOException {
		logger.debug(" --- getListItemsByTitle  [ HNotaDAO ]  --- " );
		logger.debug(" --- request :  "+req.toString()+"  --- " );
		
		
		List<ItemsResponse> lista = null;
		StringBuilder query = new StringBuilder();

		int limit = req.getLimit();
		int page = req.getPage();

		if (limit == 0 || page == 0) {
			return null;
		}

		int to = limit * page;
		int from = (to - limit) + 1;

		query.append("  SELECT * FROM (SELECT @rownum:=@rownum+1 rank , q.* ");
		query.append(" 		FROM (   SELECT n.FC_ID_CONTENIDO AS id  , ");
		query.append(" 						n.FC_TITULO AS title, ");
        query.append("        			    n.FC_CN AS user , ");
		query.append(" 						n.FC_DESCRIPCION AS description , ");
		query.append(" 						n.FD_FECHA_PUBLICACION AS date   , ");
		query.append(" 						n.FC_ID_TIPO_NOTA AS typeItem, ");
		query.append(" 						n.FC_FRIENDLY_URL  AS url_item , ");
		query.append(" 						n.FC_ID_ESTATUS_NOTA AS status  , ");
		query.append(" 						n.FC_IMAGEN_PRINCIPAL AS image   , ");
		query.append(" 						categoria.FC_ID_CATEGORIA AS idCategories , ");
		query.append(" 						categoria.FC_DESCRIPCION AS descCategories, ");
		query.append(" 						deporte.FC_DESCRIPCION AS descSport, ");
		query.append(" 						deporte.FC_ID_DEPORTE AS idSport ");
		query.append(" 				FROM  yog_ba_h_nota n       ");
		query.append(" 				LEFT JOIN yog_ba_c_categoria categoria ON n.FC_ID_CATEGORIA = categoria.FC_ID_CATEGORIA    ");
		query.append(" 				LEFT JOIN yog_ba_c_deporte deporte ON n.FC_ID_DEPORTE = deporte.FC_ID_DEPORTE  ");
		query.append(" 		  		 WHERE  1=1   ");
		
		if (req.getType().equals("categoria")) {
			query.append("    	AND categoria.FC_ID_CATEGORIA = '" + req.getId() + "' ");
		}

		if (req.getType().equals("deporte")) {
			query.append("     AND deporte.FC_ID_DEPORTE = '" + req.getId() + "' ");
		}
		
		if (req.getAuthor() != null && !req.getAuthor().equals(""))
			query.append(" 	   AND n.FC_CN = '" + req.getAuthor() + "' ");
		

		if (req.getTitle() != null && !req.getTitle().equals(""))
			query.append(" 		AND N.FC_TITULO LIKE '%" + req.getTitle() + "%' ");

		if (req.getStatus() != null && !req.getStatus().equals(""))
			query.append("      AND n.FC_ID_ESTATUS_NOTA = '" + req.getStatus() + "' ");

		
		query.append(" ORDER BY FD_FECHA_PUBLICACION DESC ) AS q ,(SELECT @rownum:=0) num ) r  ");
		query.append(" WHERE r.rank >= " + from + " AND r.rank <= " + to + " ");
		
		
		

		try {

			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<ItemsResponse>(ItemsResponse.class));

		} catch (Exception e) {

			logger.error(" ---  Error getListItemsByTitle  [ HNotaDAO ] : ", e);

			throw new DAOException(e.getMessage());

		}

		return lista;

	}

	/**
	 * Gets the list items.
	 *
	 * @param ItemsRequest
	 * @return List<ItemsResponse>
	 * @throws DAOException
	 */
	public List<ItemsResponse> getListItems(ItemsRequest req) throws DAOException {
		logger.debug(" --- getListItems  [ HNotaDAO ]  --- " );
		logger.debug(" --- request :  "+req.toString()+"  --- " );
		
		
		List<ItemsResponse> lista = null;
		StringBuilder query = new StringBuilder();

		int limit = req.getLimit();
		int page = req.getPage();

		if (limit == 0 || page == 0) {
			return null;
		}

		int to = limit * page;
		int from = (to - limit) + 1;

		query.append("  SELECT * FROM (SELECT @rownum:=@rownum+1 rank , q.* ");
		query.append(" 		FROM (   SELECT n.FC_ID_CONTENIDO AS id  , ");
		query.append(" 						n.FC_TITULO AS title, ");
        query.append("        			    n.FC_CN AS user , ");
		query.append(" 						n.FC_DESCRIPCION AS description , ");
		query.append(" 						n.FD_FECHA_PUBLICACION AS date   , ");
		query.append(" 						n.FC_ID_TIPO_NOTA AS typeItem, ");
		query.append(" 						n.FC_FRIENDLY_URL  AS url_item , ");
		query.append(" 						n.FC_ID_ESTATUS_NOTA AS status  , ");
		query.append(" 						n.FC_IMAGEN_PRINCIPAL AS image   , ");
		query.append(" 						categoria.FC_ID_CATEGORIA AS idCategories , ");
		query.append(" 						categoria.FC_DESCRIPCION AS descCategories, ");
		query.append(" 						deporte.FC_DESCRIPCION AS descSport, ");
		query.append(" 						deporte.FC_ID_DEPORTE AS idSport ");
		query.append(" 				FROM  yog_ba_h_nota n       ");
		query.append(" 				LEFT JOIN yog_ba_c_categoria categoria ON n.FC_ID_CATEGORIA = categoria.FC_ID_CATEGORIA    ");
		query.append(" 				LEFT JOIN yog_ba_c_deporte deporte ON n.FC_ID_DEPORTE = deporte.FC_ID_DEPORTE  ");
		query.append(" 		  		 WHERE  1=1   ");
		
		if (req.getType().equals("categoria")) {
			query.append("    AND categoria.FC_ID_CATEGORIA = '" + req.getId() + "' ");
		}
		
		
		if (req.getType().equals("deporte")) {
			query.append("     AND deporte.FC_ID_DEPORTE = '" + req.getId() + "' ");
		}
		
		if (req.getStatus() != null && !req.getStatus().equals(""))
			query.append("      AND n.FC_ID_ESTATUS_NOTA = '" + req.getStatus() + "' ");

		query.append(" ORDER BY FD_FECHA_PUBLICACION DESC ) AS q ,(SELECT @rownum:=0) num ) r  ");
		query.append(" WHERE r.rank >= " + from + " AND r.rank <= " + to + " ");
		
		
		
		

		try {

			lista = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<ItemsResponse>(ItemsResponse.class));

		} catch (Exception e) {

			logger.error("---  Error getListItems  [ HNotaDAO ] :", e);

			throw new DAOException(e.getMessage());

		}

		return lista;

	}

}
