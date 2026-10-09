package co.edu.sena.rfid.repositorio;

import co.edu.sena.rfid.modelo.CategoriaActivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Proporciona las operaciones de persistencia para las categorías
 * de activos mediante Spring Data JPA.
 *
 * <p>Spring genera automáticamente la implementación de esta
 * interfaz durante el inicio de la aplicación.</p>
 */
@Repository
public interface CategoriaActivoRepository
        extends JpaRepository<CategoriaActivo, Integer> {
}