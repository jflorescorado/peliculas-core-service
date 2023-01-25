package com.peliculas.prueba.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.dto.FacturaRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.Factura;
import com.peliculas.prueba.service.FacturaService;
import com.peliculas.prueba.util.ApiResponse;

@RestController
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	FacturaService facturaService;

	private static final Logger logger = LoggerFactory.getLogger(FacturaController.class);

	@PostMapping("/")
	public ResponseEntity<ApiResponse<Factura>> saveFactura(@RequestBody FacturaRequest facturaRequest) {
		try {
			Factura response = facturaService.saveFactura(facturaRequest);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Se guardo factura exitosamente", response),
					HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/")
	public ResponseEntity<ApiResponse<Factura>> updateFactura(@RequestBody FacturaRequest facturaRequest) {
		try {
			Factura response = facturaService.updateFactura(facturaRequest);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Se actualizo factura exitosamente", response),
					HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Factura>> findById(@PathVariable Long id) {
		try {
			Factura response = facturaService.findById(id);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "", response), HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{usuario}")
	public ResponseEntity<ApiResponse<List<Factura>>> findByUsuario(@PathVariable String usuario) {
		try {
			List<Factura> response = facturaService.findByUsuario(usuario);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "", response), HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public ResponseEntity<ApiResponse<List<Factura>>> findAllFactura() {
		try {
			List<Factura> response = facturaService.findAllFactura();
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "", response), HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteFactura(@PathVariable Long id) {
		try {
			facturaService.deleteFactura(id);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Factura eliminada con éxito", null),
					HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

