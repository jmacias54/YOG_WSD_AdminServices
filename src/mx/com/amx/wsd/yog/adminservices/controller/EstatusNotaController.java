/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsd.yog.adminservices.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.wsd.yog.adminservices.controller.exception.ControllerException;
import mx.com.amx.wsd.yog.adminservices.dao.EstatusNotaDAO;
import mx.com.amx.wsd.yog.adminservices.model.EstatusNota;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */

@Controller
@RequestMapping("/catalogos/estatus-nota")
public class EstatusNotaController {
	
	
	private static Logger logger = Logger.getLogger(EstatusNotaController.class);
	
	@Autowired
	private EstatusNotaDAO estatusNotaDAO;
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<EstatusNota> findAll() throws ControllerException {
		logger.debug("--- findAll [ EstatusNotaController ]-----");
		

	
		try {
			return estatusNotaDAO.findAll();
		} catch (Exception e) {
			logger.error(" -- Error  findAll [ EstatusNotaController ] :", e);
			throw new ControllerException(e.getMessage());
		}

		
	}
	
	
	@RequestMapping(value = "/{idEstatusNota}/", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public EstatusNota findByIdEstatusNota(@PathVariable String idEstatusNota) throws ControllerException {
		logger.debug("--- findByIdEstatusNota [ EstatusNotaController ]-----");
		logger.debug("--- idEstatusNota : "+idEstatusNota+"-----");
		

	
		try {
			return estatusNotaDAO.findById(idEstatusNota);
		} catch (Exception e) {
			logger.error(" -- Error  findByIdEstatusNota [ EstatusNotaController ] :", e);
			throw new ControllerException(e.getMessage());
		}

		
	}

}
