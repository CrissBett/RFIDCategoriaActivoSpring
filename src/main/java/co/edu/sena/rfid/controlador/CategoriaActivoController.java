package co.edu.sena.rfid.controlador;

import co.edu.sena.rfid.modelo.CategoriaActivo;
import co.edu.sena.rfid.servicio.CategoriaActivoService;
import jakarta.validation.Valid;
import java.util.List;
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

/**
 * Expone los servicios REST para gestionar las categorías
 * de activos del Sistema RFID.
 *
 * <p>Las operaciones se encuentran disponibles mediante
 * la ruta base {@code /api/categorias}.</p>
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaActivoController {

    private final CategoriaActivoService categoriaService;

    /**
     * Crea el controlador con el servicio requerido.
     *
     * <p>Spring proporciona automáticamente el servicio
     * mediante inyección por constructor.</p>
     *
     * @param categoriaService servicio de categorías
     */
    public CategoriaActivoController(
            CategoriaActivoService categoriaService) {

        this.categoriaService = categoriaService;
    }

    /**
     * Consulta todas las categorías registradas.
     *
     * @return respuesta HTTP 200 con la lista de categorías
     */
    @GetMapping
    public ResponseEntity<List<CategoriaActivo>>
            listarCategorias() {

        List<CategoriaActivo> categorias =
                categoriaService.listarCategorias();

        return ResponseEntity.ok(categorias);
    }

    /**
     * Consulta una categoría mediante su identificador.
     *
     * @param idCategoria identificador de la categoría
     * @return respuesta HTTP 200 con la categoría encontrada
     *         o HTTP 404 si el identificador no existe
     */
    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaActivo> buscarPorId(
            @PathVariable Integer idCategoria) {

        return categoriaService
                .buscarPorId(idCategoria)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity
                                .notFound()
                                .build()
                );
    }

    /**
     * Registra una categoría nueva.
     *
     * @param categoria datos recibidos en formato JSON
     * @return respuesta HTTP 201 con la categoría registrada
     */
    @PostMapping
    public ResponseEntity<CategoriaActivo> registrarCategoria(
            @Valid
            @RequestBody CategoriaActivo categoria) {

        CategoriaActivo categoriaRegistrada =
                categoriaService.registrarCategoria(
                        categoria
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaRegistrada);
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param idCategoria identificador de la categoría
     * @param categoria datos actualizados en formato JSON
     * @return respuesta HTTP 200 con la categoría actualizada
     *         o HTTP 404 si el identificador no existe
     */
    @PutMapping("/{idCategoria}")
    public ResponseEntity<CategoriaActivo> actualizarCategoria(
            @PathVariable Integer idCategoria,
            @Valid
            @RequestBody CategoriaActivo categoria) {

        return categoriaService
                .actualizarCategoria(
                        idCategoria,
                        categoria
                )
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity
                                .notFound()
                                .build()
                );
    }

    /**
     * Elimina una categoría mediante su identificador.
     *
     * @param idCategoria identificador de la categoría
     * @return respuesta HTTP 204 si la categoría fue eliminada
     *         o HTTP 404 si el identificador no existe
     */
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(
            @PathVariable Integer idCategoria) {

        boolean eliminada =
                categoriaService.eliminarCategoria(
                        idCategoria
                );

        if (!eliminada) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();
    }
}