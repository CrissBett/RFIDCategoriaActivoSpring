package co.edu.sena.rfid.excepcion;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Centraliza el manejo de excepciones generadas
 * por los controladores REST de la aplicación.
 *
 * <p>Evita exponer trazas técnicas al cliente y devuelve
 * respuestas JSON uniformes y comprensibles.</p>
 */
@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    private static final Logger LOGGER =
            Logger.getLogger(
                    ManejadorGlobalExcepciones.class.getName()
            );

    /**
     * Procesa los errores producidos por las anotaciones
     * de validación aplicadas sobre las entidades.
     *
     * @param excepcion excepción con los errores de validación
     * @return respuesta HTTP 400 con los errores por campo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>>
            manejarErroresValidacion(
                    MethodArgumentNotValidException excepcion) {

        Map<String, String> errores =
                obtenerErroresPorCampo(excepcion);

        Map<String, Object> respuesta =
                crearRespuestaBase(
                        HttpStatus.BAD_REQUEST,
                        "Los datos enviados no son válidos."
                );

        respuesta.put(
                "errores",
                errores
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);
    }

    /**
     * Procesa excepciones inesperadas que no fueron atendidas
     * por un manejador más específico.
     *
     * <p>El detalle técnico se registra en la consola del servidor,
     * pero no se expone en la respuesta enviada al cliente.</p>
     *
     * @param excepcion excepción inesperada
     * @return respuesta HTTP 500 sin detalles internos
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>
            manejarErrorGeneral(
                    Exception excepcion) {

        LOGGER.log(
                Level.SEVERE,
                "Error inesperado al procesar la solicitud.",
                excepcion
        );

        Map<String, Object> respuesta =
                crearRespuestaBase(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Ocurrió un error interno al procesar "
                        + "la solicitud."
                );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(respuesta);
    }

    /**
     * Obtiene los mensajes asociados con cada campo inválido.
     *
     * @param excepcion excepción producida por la validación
     * @return mapa con el nombre del campo y su mensaje de error
     */
    private Map<String, String> obtenerErroresPorCampo(
            MethodArgumentNotValidException excepcion) {

        Map<String, String> errores =
                new LinkedHashMap<>();

        for (FieldError error
                : excepcion.getBindingResult()
                        .getFieldErrors()) {

            errores.put(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }

        return errores;
    }

    /**
     * Construye la estructura común para las respuestas
     * de error de la API REST.
     *
     * @param estado estado HTTP que se devolverá
     * @param mensaje descripción comprensible del error
     * @return mapa con fecha, estado, error y mensaje
     */
    private Map<String, Object> crearRespuestaBase(
            HttpStatus estado,
            String mensaje) {

        Map<String, Object> respuesta =
                new LinkedHashMap<>();

        respuesta.put(
                "fechaHora",
                LocalDateTime.now()
        );

        respuesta.put(
                "estado",
                estado.value()
        );

        respuesta.put(
                "error",
                estado.getReasonPhrase()
        );

        respuesta.put(
                "mensaje",
                mensaje
        );

        return respuesta;
    }
}