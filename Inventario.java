import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

class Inventario {

  private int cantidadProductos;
  private double ValorInventario;
  private List<Producto> productos;

  public Inventario() {
    this.cantidadProductos = 0;
    this.ValorInventario = 0.0;
    this.productos = new ArrayList<Producto>();
  }

  public String obtenerInput(String message) throws OperacionCancelada {
    String input = JOptionPane.showInputDialog(null, message);
    if (input == null) {
      JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario");
      throw new OperacionCancelada("Operación cancelada por el usuario.");
    }
    return input;
  }

  public void agregarProducto() {
    try {
      String nombre = obtenerInput("Nombre del producto");
      String descripcion = obtenerInput("Describeme el producto.");
      int stock;
      do {
        stock = Integer.parseInt(obtenerInput("¿Cuánta cantidad de producto tienes?"));
        if (stock <= 0) {
          JOptionPane.showMessageDialog(null, "El stock no puede ser negativo, intenta de nuevo.");
        }
      } while (stock <= 0);

      double precio;
      do {
        precio = Double.parseDouble(obtenerInput("¿Cuál es el precio de tu producto?"));
        if (precio <= 0) {
          JOptionPane.showMessageDialog(null, "El precio no puede ser negativo.");
        }
      } while (precio <= 0);
      Producto nuevoProducto = new Producto(nombre, stock, precio, descripcion);
      productos.add(nuevoProducto);

      JOptionPane.showMessageDialog(null, "El producto " + nombre + "ha sido agregado con éxito." + "\nStock: " + stock
          + "\nPrecio: " + precio + "\nDescripción: " + descripcion);

      double precioTotal = precio * stock;
      this.ValorInventario += precioTotal;

    } catch (OperacionCancelada error) {
      JOptionPane.showMessageDialog(null, error.getMessage());
    } catch (NumberFormatException erorr) {
      JOptionPane.showMessageDialog(null, "Ingresa por favor un valor válido.");
    }
  }

  public List<Producto> getProductos() {
    return productos;
  }

  public double getValorInventario() {
    return this.ValorInventario;
  }
}
