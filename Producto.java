
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

  public String getNombre() {
    return nombre;
  }

  public Double getPrecio() {
    return precio;
  }

  public int getStock() {
    return stock;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String toString() {
    return nombre + " - " + descripcion + " - " + precio + "$" + "| Stock:" + stock;
  }
}
