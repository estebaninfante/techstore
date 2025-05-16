class Productos {

  private String nombre;
  private Double precio;
  private int stock;
  private String descripcion;

  public Productos(String nombre, int stock, Double precio, String descripcion) {
    this.nombre = nombre;
    this.stock = stock;
    this.precio = precio;
    this.descripcion = descripcion;
  }
  public String conseguirnombre () {
    return nombre;
  }
  public Double conseguirprecio () {
    return precio;
  }
  public int conseguirstock () {
    return stock;
  }
  public String conseguirdescripcion () {
    return descripcion;
  }
}
