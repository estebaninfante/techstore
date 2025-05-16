public class Producto {

  private String nombre;
  private Double precio;
  private int stock;
  private String descripcion;

  public Producto(String nombre, int stock, Double precio, String descripcion) {
    this.nombre = nombre;
    this.stock = stock;
    this.precio = precio;
    this.descripcion = descripcion;
  }

  public String conseguirNombre() {
    return nombre;
  }

  public Double conseguirPrecio() {
    return precio;
  }

  public int conseguirStock() {
    return stock;
  }

  public String conseguirDescripcion() {
    return descripcion;
  }

  public void establecerNombre(String nombre) {
    this.nombre = nombre;
  }

  public void establecerPrecio(Double precio) {
    this.precio = precio;
  }

  public void establecerStock(int stock) {
    this.stock = stock;
  }

  public void establecerDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
