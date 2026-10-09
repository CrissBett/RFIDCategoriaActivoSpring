package co.edu.sena.rfid.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Representa una categoría de activos del Sistema RFID.
 *
 * <p>La entidad se encuentra asociada con la tabla
 * {@code categoriaactivo} de la base de datos
 * {@code rfid_gestion_activos}.</p>
 */
@Entity
@Table(name = "categoriaactivo")
public class CategoriaActivo {

    /**
     * Identificador único generado automáticamente por MySQL.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    /**
     * Nombre descriptivo de la categoría.
     */
    @NotBlank(
            message = "El nombre es obligatorio."
    )
    @Size(
            max = 100,
            message = "El nombre no puede superar 100 caracteres."
    )
    @Column(
            name = "nombre",
            nullable = false,
            length = 100
    )
    private String nombre;

    /**
     * Descripción de los activos asociados con la categoría.
     */
    @NotBlank(
            message = "La descripción es obligatoria."
    )
    @Size(
            max = 255,
            message = "La descripción no puede superar 255 caracteres."
    )
    @Column(
            name = "descripcion",
            nullable = false,
            length = 255
    )
    private String descripcion;

    /**
     * Indica si la categoría se encuentra activa.
     */
    @Column(
            name = "activo",
            nullable = false
    )
    private boolean activo = true;

    /**
     * Constructor sin argumentos requerido por JPA.
     */
    public CategoriaActivo() {
    }

    /**
     * Crea una categoría nueva sin identificador.
     *
     * <p>El identificador no se recibe porque MySQL
     * lo genera automáticamente.</p>
     *
     * @param nombre nombre de la categoría
     * @param descripcion descripción de la categoría
     * @param activo estado de la categoría
     */
    public CategoriaActivo(
            String nombre,
            String descripcion,
            boolean activo) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    /**
     * Obtiene el identificador de la categoría.
     *
     * @return identificador de la categoría
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }

    /**
     * Asigna el identificador de la categoría.
     *
     * @param idCategoria identificador de la categoría
     */
    public void setIdCategoria(
            Integer idCategoria) {

        this.idCategoria = idCategoria;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return nombre de la categoría
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre de la categoría.
     *
     * @param nombre nombre de la categoría
     */
    public void setNombre(
            String nombre) {

        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la categoría.
     *
     * @return descripción de la categoría
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna la descripción de la categoría.
     *
     * @param descripcion descripción de la categoría
     */
    public void setDescripcion(
            String descripcion) {

        this.descripcion = descripcion;
    }

    /**
     * Indica si la categoría se encuentra activa.
     *
     * @return {@code true} si está activa;
     *         {@code false} si está inactiva
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Asigna el estado de la categoría.
     *
     * @param activo estado de la categoría
     */
    public void setActivo(
            boolean activo) {

        this.activo = activo;
    }
}