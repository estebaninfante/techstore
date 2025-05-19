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

  public void agregarProducto() {
    String nombre = JOptionPane.showInputDialog(null, "Nombre del producto");
    String descripcion = JOptionPane.showInputDialog(null, "Describeme el producto.");
    double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el precio del producto"));
    int stock = Integer.parseInt(
        JOptionPane.showInputDialog(null, "Ingresa la cantidad de producto que tienes."));

    Producto nuevoProducto = new Producto(nombre, stock, precio, descripcion);
    productos.add(nuevoProducto);

    double precioTotal = precio * stock;
    this.ValorInventario += precioTotal;

  }

  public List<Producto> getProductos() {
    return productos;
  }

  public double getValorInventario() {
    return this.ValorInventario;
  }
}
