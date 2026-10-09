package co.edu.sena.rfid.servicio;

import co.edu.sena.rfid.modelo.CategoriaActivo;
import co.edu.sena.rfid.repositorio.CategoriaActivoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Contiene la lógica de negocio para gestionar
 * las categorías de activos del Sistema RFID.
 *
 * <p>La clase actúa como intermediaria entre el controlador REST
 * y el repositorio de persistencia.</p>
 */
@Service
@Transactional
public class CategoriaActivoService {

    private final CategoriaActivoRepository categoriaRepository;

    /**
     * Crea el servicio con el repositorio requerido.
     *
     * <p>Spring proporciona automáticamente la implementación
     * del repositorio mediante inyección por constructor.</p>
     *
     * @param categoriaRepository repositorio de categorías
     */
    public CategoriaActivoService(
            CategoriaActivoRepository categoriaRepository) {

        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Consulta todas las categorías registradas.
     *
     * @return lista de categorías
     */
    @Transactional(readOnly = true)
    public List<CategoriaActivo> listarCategorias() {

        return categoriaRepository.findAll();
    }

    /**
     * Busca una categoría mediante su identificador.
     *
     * @param idCategoria identificador de la categoría
     * @return categoría encontrada o un {@link Optional} vacío
     */
    @Transactional(readOnly = true)
    public Optional<CategoriaActivo> buscarPorId(
            Integer idCategoria) {

        return categoriaRepository.findById(idCategoria);
    }

    /**
     * Registra una categoría nueva.
     *
     * <p>El identificador se establece como {@code null}
     * para asegurar que MySQL genere un registro nuevo.</p>
     *
     * @param categoria categoría que se desea registrar
     * @return categoría almacenada con el identificador generado
     */
    public CategoriaActivo registrarCategoria(
            CategoriaActivo categoria) {

        categoria.setIdCategoria(null);

        normalizarDatos(categoria);

        return categoriaRepository.save(categoria);
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param idCategoria identificador de la categoría
     * @param datosActualizados nuevos datos de la categoría
     * @return categoría actualizada o un {@link Optional} vacío
     *         cuando el identificador no existe
     */
    public Optional<CategoriaActivo> actualizarCategoria(
            Integer idCategoria,
            CategoriaActivo datosActualizados) {

        return categoriaRepository
                .findById(idCategoria)
                .map(categoriaExistente -> {

                    categoriaExistente.setNombre(
                            datosActualizados.getNombre()
                    );

                    categoriaExistente.setDescripcion(
                            datosActualizados.getDescripcion()
                    );

                    categoriaExistente.setActivo(
                            datosActualizados.isActivo()
                    );

                    normalizarDatos(categoriaExistente);

                    return categoriaRepository.save(
                            categoriaExistente
                    );
                });
    }

    /**
     * Elimina una categoría mediante su identificador.
     *
     * @param idCategoria identificador de la categoría
     * @return {@code true} si la categoría fue eliminada;
     *         {@code false} si no existe
     */
    public boolean eliminarCategoria(
            Integer idCategoria) {

        if (!categoriaRepository.existsById(idCategoria)) {
            return false;
        }

        categoriaRepository.deleteById(idCategoria);

        return true;
    }

    /**
     * Elimina espacios innecesarios al inicio y al final
     * de los valores textuales de una categoría.
     *
     * @param categoria categoría que se desea normalizar
     */
    private void normalizarDatos(
            CategoriaActivo categoria) {

        categoria.setNombre(
                categoria.getNombre().trim()
        );

        categoria.setDescripcion(
                categoria.getDescripcion().trim()
        );
    }
}