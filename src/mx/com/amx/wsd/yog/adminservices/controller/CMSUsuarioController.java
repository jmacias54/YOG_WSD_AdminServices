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
import mx.com.amx.wsd.yog.adminservices.dao.CMSUsuarioDAO;
import mx.com.amx.wsd.yog.adminservices.model.CMSUsuario;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */

@Controller
@RequestMapping("/catalogos/cms/usuario")
public class CMSUsuarioController {
	
	private static Logger logger = Logger.getLogger(CMSUsuarioController.class);
	
	@Autowired
	private CMSUsuarioDAO cmsUsuarioDAO;
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<CMSUsuario> findAll() throws ControllerException {
		logger.debug("--- findAll [ CMSUsuarioController ]-----");
		

	
		try {
			return cmsUsuarioDAO.findAll();
		} catch (Exception e) {
			logger.error(" -- Error  findAll [ CMSUsuarioController ] :", e);
			throw new ControllerException(e.getMessage());
		}

		
	}
	
	
	@RequestMapping(value = "/{cn}/", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public CMSUsuario findByIdCMSUsuario(@PathVariable String cn) throws ControllerException {
		logger.debug("--- findByIdCMSUsuario [ CMSUsuarioController ]-----");
		logger.debug("--- cn : "+cn+"-----");
		

	
		try {
			return  cmsUsuarioDAO.findByCN(cn);
		} catch (Exception e) {
			logger.error(" -- Error  findByIdCMSUsuario [ CMSUsuarioController ] :", e);
			throw new ControllerException(e.getMessage());
		}

		
	}

}
