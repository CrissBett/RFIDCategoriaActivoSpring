package co.edu.sena.rfid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la API REST para la gestión
 * de categorías de activos del Sistema RFID.
 *
 * <p>Inicia el contexto de Spring Boot, el servidor web integrado
 * y el escaneo de los componentes de la aplicación.</p>
 */
@SpringBootApplication
public class RfidCategoriaActivoSpringApplication {

    /**
     * Inicia la aplicación Spring Boot.
     *
     * @param args argumentos recibidos desde la línea de comandos
     */
    public static void main(String[] args) {

        SpringApplication.run(
                RfidCategoriaActivoSpringApplication.class,
                args
        );
    }
}