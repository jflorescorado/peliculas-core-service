package com.peliculas.prueba.commons;

public class AppConstants {

	// Estados de Factura y alquiler
	public static final String FLOW_STAR = "I";// Iniciada
	public static final String FLOW_FINISHED = "F";// Finalizada
	public static final String FLOW_PENDIENT = "P";// Pendiente

	// Estados de Detalle de Factura
	public static final String RENTAL_STATE = "A";// Alquiler
	public static final String PURCHASE_STATE = "C";// Compra / Venta

	//Codigos response http
	public static final String CODE_SUCCESS = "200";
	public static final String CODE_UNAUTHORIZED = "401";
	public static final String CODE_DATA_NOTFOUND = "404";
	public static final String CODE_ERROR = "409";

	// Descripcion errors
	public static final String DESCRIPTION_SUCCESS = "Success";	
	public static final String DESCRIPTION_UNAUTHORIZED = "Token es invalido";
	public static final String DESCRIPTION_DATA_NOTFOUND = "Dato no encontrado ";
	public static final String DEFAULT_ERROR_REPOSITORY = "Data no disponible "; // Posible error en base de datos
	public static final String DEFAULT_ERROR_DESCRIPTION = "Servicio no disponible ";
	
	// Datos no validos
	public static final String DATO_INVALIDO = "Ocurrio un error, datos no validos.";
	
	public static final String APP_EXCEPTION = "AppServiceException: ";
	public static final String EXCEPTION = "Exception: ";
}

