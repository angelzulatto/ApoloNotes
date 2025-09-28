

public abstract class recurso {

    @Id
    @Getter
    @Setter
    @GenerateValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEliminacion;
    private String descripcion;
    private String etiqueta;
    private String ruta;

    public recurso(String nombre, String descripcion, string, etiqueta, LocalDateTime fechaCreacion, LocalDateTime fechaEliminacion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.etiqueta = etiqueta;
        this.fechaCreacion = fechaCreacion;
        this.fechaEliminacion = fechaEliminacion;
        this.ruta = ruta;

    }

    public abstract void guardar();
    public abstract void eliminar();

}

