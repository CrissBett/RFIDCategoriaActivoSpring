package co.edu.sena.rfid;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Verifica que el contexto principal de Spring Boot
 * pueda iniciarse correctamente.
 */
@SpringBootTest
class RfidCategoriaActivoSpringApplicationTests {

    /**
     * Comprueba el arranque del contexto de la aplicación.
     *
     * <p>La prueba es satisfactoria cuando Spring carga
     * todos los componentes sin producir excepciones.</p>
     */
    @Test
    void contextLoads() {
        // No requiere instrucciones: Spring valida el contexto.
    }
}