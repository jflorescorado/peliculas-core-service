package com.peliculas.prueba.commons;

public class AppConstants {

	public static final String DESCRIPTION_SUCCESS = "Success";

	public static final String DESCRIPTION_USER_NOT_IN_AD = "Datos incorrectos o usuario no existe!!";

	public static final String DEFAULT_ERROR_DESCRIPTION = "Servicio no disponible ";
	public static final String DEFAULT_FOUND_REGISTRO = "Registro duplicado ";
	public static final String CODE_ERROR = "409";

	// Estados de Factura y alquiler
	public static final String FLOW_STAR = "I";// Iniciada
	public static final String FLOW_FINISHED = "F";// Finalizada
	public static final String FLOW_PENDIENT = "P";// Pendiente

	// Estados de Detalle de Factura
	public static final String RENTAL_STATE = "A";// Alquiler
	public static final String PURCHASE_STATE = "C";// Compra / Venta

	public static final String CODE_SUCCESS = "0";

	// Validacion de token
	public static final String NO_AUTORIZADO = "Usuario no autorizado";

	public static final String DEFAULT_ERROR_REPOSITORY = "Data no disponible "; // Posible error en base de datos

	public static final String DESCRIPTION_DATA_NOTFOUND = "Dato no encontrado ";
	public static final String DESCRIPTION_DATA_NOTEXIST = "No existe registro ";
	public static final String CODE_DATA_NOTFOUND = "404";

	public static final String DATO_INVALIDO = "Ocurrio un error, datos no validos.";
}

