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
import com.peliculas.prueba.dto.DetalleFacturaRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.service.DetalleFacturaService;
import com.peliculas.prueba.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/detalleFactura")
@Api(value = "/detalleFactura", tags = "DetalleFacturaController")
public class DetalleFacturaController {

	@Autowired
	DetalleFacturaService detalleFacturaService;
	
	@Autowired
	private Consumer consumer;

	@Autowired
	ResponseEntityUtil responseEntityUtil;

	private static final Logger logger = LoggerFactory.getLogger(DetalleFacturaController.class);

	@ApiOperation(value = "Proceso para guardar detalle de factura")
	@PostMapping("/")
	public ResponseEntity<?> saveDetalleFactura(@RequestHeader(name = "Authorization") String token,
			@RequestBody DetalleFacturaRequest detalleFacturaRequest) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(detalleFacturaService.saveDetalleFactura(detalleFacturaRequest),
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
	
	@ApiOperation(value = "Proceso para actualizar detalle de factura")
	@PutMapping("/")
	public ResponseEntity<?> updateDetalleFactura(@RequestHeader(name = "Authorization") String token,
			@RequestBody DetalleFacturaRequest detalleFacturaRequest) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(detalleFacturaService.updateDetalleFactura(detalleFacturaRequest),
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
	
	@ApiOperation(value = "Proceso para obtener detalle de factura por idFactura")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@RequestHeader(name = "Authorization") String token, @PathVariable Long idFactura) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(detalleFacturaService.findByIdFactura(idFactura),
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
	
	@ApiOperation(value = "Proceso para eliminar detalle de factura por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFactura(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			detalleFacturaService.deleteDetalleFactura(id);
			
			response = responseEntityUtil.createOkResponse(null,
					AppConstants.CODE_SUCCESS, "Detalle de factura eliminado existosamente");
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

