package com.inventory.myfood.application.output;

/**
 * Esta interfaz define los métodos para formatear y manejar excepciones y
 * mensajes de error
 * relacionados con la huella de carbono.
 */
public interface ExceptionFormatterIntPort {

    /**
     * Método para devolver un mensaje de error cuando ya existe una entidad.
     *
     * @param message Mensaje descriptivo del error.
     */
    public void returnResponseErrorEntityExists(String message);

    /**
     * Método para devolver un mensaje de error cuando no se encuentra una entidad.
     *
     * @param message Mensaje descriptivo del error.
     */
    public void returnResponseErrorEntityNotFound(String message);

    /**
     * Método para devolver un mensaje de error cuando se viola una regla de
     * negocio.
     *
     * @param message Mensaje descriptivo del error.
     */
    public void returnResponseBusinessRuleViolated(String message);

    /**
     * Método para devolver un mensaje de error de formato incorrecto.
     *
     * @param message Mensaje descriptivo del error.
     */
    public void returnResponseBadFormat(String message);

    /**
     * Método para devolver un mensaje de error cuando no hay datos disponibles.
     *
     * @param message Mensaje descriptivo del error.
     */
    public void returNoData(String message);

}
