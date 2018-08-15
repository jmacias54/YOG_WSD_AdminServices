/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsd.yog.adminservices.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.wsd.yog.adminservices.controller.exception.ControllerException;
import mx.com.amx.wsd.yog.adminservices.dao.NNotaDAO;
import mx.com.amx.wsd.yog.adminservices.model.request.ItemsFilterRequest;
import mx.com.amx.wsd.yog.adminservices.model.request.ItemsRequest;
import mx.com.amx.wsd.yog.adminservices.model.request.ItemsRequestByTitle;
import mx.com.amx.wsd.yog.adminservices.model.response.ItemsResponse;

/**
 * @author Jesus Armando Macias Benitez
 *
 */

@Controller
@RequestMapping("/nnota")
public class NNotaController {
	private static Logger logger = Logger.getLogger(NNotaController.class);

	@Autowired
	private NNotaDAO nNotaDAO;

	@RequestMapping(value = "/get_list_items_filter", method = RequestMethod.POST, headers = {"Content-type=application/json" })
	@ResponseBody
	public List<ItemsResponse> getListItemsByFilter(@RequestBody ItemsFilterRequest req) throws ControllerException {
		logger.info("--- get_list_items_filter  [ NNotaController ] ----");
		List<ItemsResponse> lista = null;

		try {
			lista =  nNotaDAO.getListItemsByFilter(req);
		} catch (Exception e) {
			logger.error("---Error get_list_items_filter  [ NNotaController ] :" + e.getMessage());
			throw new ControllerException(e.getMessage());
		}

		return lista;
	}

	@RequestMapping(value = "/get_list_items", method = RequestMethod.POST, headers = {"Content-type=application/json" })
	@ResponseBody
	public List<ItemsResponse> getListItems(@RequestBody ItemsRequest req) throws ControllerException {
		logger.info("--- get_list_items [ NNotaController ] ----");
		List<ItemsResponse> lista = null;

		try {
			lista = nNotaDAO.getListItems(req);
		} catch (Exception e) {

			logger.error("---Error get_list_items  [ NNotaController ] :" + e.getMessage());
			throw new ControllerException(e.getMessage());
		}

		return lista;
	}

	@RequestMapping(value = "/get_list_items_search", method = RequestMethod.POST, headers = {"Content-type=application/json" })
	@ResponseBody
	public List<ItemsResponse> getListItemsByTitle(@RequestBody ItemsRequestByTitle req) throws ControllerException {
		logger.info("--- get_list_items [ NNotaController ] ----");
		List<ItemsResponse> lista = null;

		try {
			lista = nNotaDAO.getListItemsByTitle(req);
		} catch (Exception e) {

			logger.error("---Error get_list_items  [ NNotaController ] :" + e.getMessage());
			throw new ControllerException(e.getMessage());
		}

		return lista;
	}

}
