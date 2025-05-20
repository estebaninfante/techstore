import javax.swing.JOptionPane;

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

  public void setStock(int nuevoStock) {
    this.stock = nuevoStock;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setPrecio(Double precio) {
    if (precio <= 0) {
      throw new IllegalArgumentException("El precio debe ser mayor que 0");
    }
    this.precio = precio;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String toString() {
    return "Nombre: " + nombre + " | Descripción: " + descripcion + " | Precio:  " + precio + "$" + " | Stock:" + stock;
  }

  public void modificarNombre() throws OperacionCancelada {
    String nuevoNombre = JOptionPane.showInputDialog(
        null,
        "Nuevo nombre para '" + this.nombre + "':",
        "Modificar Nombre",
        JOptionPane.QUESTION_MESSAGE);

    if (nuevoNombre == null) {
      throw new OperacionCancelada("Operación cancelada por el usuario.");
    }

    if (!nuevoNombre.trim().isEmpty()) {
      this.nombre = nuevoNombre.trim();
    }
  }

  public void modificarDescripcion() throws OperacionCancelada {
    String nuevaDescripcion = JOptionPane.showInputDialog(
        null,
        "Nueva descripción para '" + this.nombre + "':",
        "Modificar Descripción",
        JOptionPane.QUESTION_MESSAGE);

    if (nuevaDescripcion == null) {
      throw new OperacionCancelada("Operación cancelada por el usuario.");
    }

    if (!nuevaDescripcion.trim().isEmpty()) {
      this.descripcion = nuevaDescripcion.trim();
    }
  }

  public void modificarPrecio() throws OperacionCancelada, NumberFormatException {
    String nuevoPrecioStr = JOptionPane.showInputDialog(
        null,
        "Nuevo precio para '" + this.nombre + "' (actual: $" + String.format("%.2f", this.precio) + "):",
        "Modificar Precio",
        JOptionPane.QUESTION_MESSAGE);

    if (nuevoPrecioStr == null) {
      throw new OperacionCancelada("Operación cancelada por el usuario.");
    }

    if (!nuevoPrecioStr.trim().isEmpty()) {
      double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
      if (nuevoPrecio <= 0) {
        throw new IllegalArgumentException("El precio no puede ser negativo o cero.");
      }
      this.precio = nuevoPrecio;
    }
  }

  public void modificarStock() throws OperacionCancelada, NumberFormatException {
    String nuevoStockStr = JOptionPane.showInputDialog(
        null,
        "Nuevo stock para '" + this.nombre + "' (actual: " + this.stock + "):",
        "Modificar Stock",
        JOptionPane.QUESTION_MESSAGE);

    if (nuevoStockStr == null) {
      throw new OperacionCancelada("Operación cancelada por el usuario.");
    }

    if (!nuevoStockStr.trim().isEmpty()) {
      int nuevoStock = Integer.parseInt(nuevoStockStr);
      if (nuevoStock < 0) {
        throw new IllegalArgumentException("El stock no puede ser negativo.");
      }
      this.stock = nuevoStock;
    }
  }
}
