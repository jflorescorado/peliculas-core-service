package com.peliculas.prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.consumer.Consumer;
import com.peliculas.prueba.dto.FacturaRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.service.FacturaService;
import com.peliculas.prueba.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/factura")
@Api(value = "/factura", tags = "FacturaController")
public class FacturaController {

	@Autowired
	FacturaService facturaService;
	
	@Autowired
	private Consumer consumer;

	@Autowired
	ResponseEntityUtil responseEntityUtil;

	private static final Logger logger = LoggerFactory.getLogger(FacturaController.class);

	@ApiOperation(value = "Proceso para guardar factura")
	@PostMapping("/")
	public ResponseEntity<?> saveFactura(@RequestHeader(name = "Authorization") String token, 
			@RequestBody FacturaRequest facturaRequest) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(facturaService.saveFactura(facturaRequest),
					AppConstants.CODE_SUCCESS, AppConstants.DESCRIPTION_SUCCESS);
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@ApiOperation(value = "Proceso para actualizar factura")
	@PutMapping("/")
	public ResponseEntity<?> updateFactura(@RequestHeader(name = "Authorization") String token,
			@RequestBody FacturaRequest facturaRequest) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(facturaService.updateFactura(facturaRequest),
					AppConstants.CODE_SUCCESS, AppConstants.DESCRIPTION_SUCCESS);
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@ApiOperation(value = "Proceso para obtener factura por id")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@RequestHeader(name = "Authorization") String token,
			@PathVariable Long id) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(facturaService.findById(id),
					AppConstants.CODE_SUCCESS, AppConstants.DESCRIPTION_SUCCESS);
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@ApiOperation(value = "Proceso para obtener facturas por usuario")
	@GetMapping("/{usuario}")
	public ResponseEntity<?> findByUsuario(@RequestHeader(name = "Authorization") String token,
			@PathVariable String usuario) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(facturaService.findByUsuario(usuario),
					AppConstants.CODE_SUCCESS, AppConstants.DESCRIPTION_SUCCESS);
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@ApiOperation(value = "Proceso para obtener todas las facturas")
	@GetMapping("/")
	public ResponseEntity<?> findAllFactura(@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(facturaService.findAllFactura(),
					AppConstants.CODE_SUCCESS, AppConstants.DESCRIPTION_SUCCESS);
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@ApiOperation(value = "Proceso para eliminar factura por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFactura(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			facturaService.deleteFactura(id);
			
			response = responseEntityUtil.createOkResponse(null,
					AppConstants.CODE_SUCCESS, "Factura eliminada existosamente");
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}
}

