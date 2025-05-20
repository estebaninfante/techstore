import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

class Inventario {

  private double ValorInventario;
  private List<Producto> productos;

  public Inventario() {
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

  public void modificarProducto() {
    StringBuilder sb = verProductos("Selecciona el producto a modificar");
    if (productos.isEmpty())
      return;

    try {
      // Seleccionar producto
      String seleccionStr = JOptionPane.showInputDialog(null, sb.toString());
      if (seleccionStr == null)
        return;

      int seleccionNum = Integer.parseInt(seleccionStr);
      if (seleccionNum < 1 || seleccionNum > productos.size()) {
        JOptionPane.showMessageDialog(null, "Producto inválido");
        return;
      }

      Producto productoAModificar = productos.get(seleccionNum - 1);
      double valorAntiguo = productoAModificar.getPrecio() * productoAModificar.getStock();

      // Menú de modificación
      while (true) {
        String[] opciones = {
            "Modificar Nombre",
            "Modificar Descripción",
            "Modificar Precio",
            "Modificar Stock",
            "Volver"
        };

        int opcion = JOptionPane.showOptionDialog(
            null,
            "¿Qué deseas modificar de " + productoAModificar.getNombre() + "?",
            "Modificar Producto",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]);

        try {
          switch (opcion) {
            case 0:
              productoAModificar.modificarNombre();
              break;
            case 1:
              productoAModificar.modificarDescripcion();
              break;
            case 2:
              productoAModificar.modificarPrecio();
              break;
            case 3:
              productoAModificar.modificarStock();
              break;
            case 4:
            case -1: // Si cierra la ventana
              return;
          }

          // Actualizar valor del inventario
          this.ValorInventario -= valorAntiguo;
          this.ValorInventario += productoAModificar.getPrecio() * productoAModificar.getStock();

          JOptionPane.showMessageDialog(null,
              "Producto actualizado:\n" + productoAModificar.toString());

        } catch (OperacionCancelada e) {
          // Continuar con el menú
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(null,
              "Error: Ingrese un número válido",
              "Error",
              JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(null,
              e.getMessage(),
              "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Selección inválida");
    }
  }

  public List<Producto> getProductos() {
    return productos;
  }

  public double getValorInventario() {
    return this.ValorInventario;
  }

  public StringBuilder verProductos(String message) {
    List<Producto> listaProductos = getProductos();

    if (listaProductos.isEmpty()) {
      JOptionPane.showMessageDialog(null, "No hay productos en el inventario para modificar.", "Inventario Vacío",
          JOptionPane.INFORMATION_MESSAGE);
      return new StringBuilder();
    }

    StringBuilder sb = new StringBuilder("Productos disponibles: \n");
    sb.append("-------------------------------------\n");
    for (int i = 0; i < listaProductos.size(); i++) {
      Producto p = listaProductos.get(i);
      sb.append((i + 1)) // Mostrar número 1-based para el usuario
          .append(". Nombre: ").append(p.getNombre())
          .append(" | Precio: $").append(String.format("%.2f", p.getPrecio()))
          .append(" | Stock: ").append(p.getStock())
          .append("\n");
    }
    sb.append("-------------------------------------\n");
    sb.append(message);
    return sb;
  }

  public void venderProducto() throws NumberFormatException {

    List<Producto> listaProductos = getProductos();

    if (listaProductos.isEmpty()) {
      JOptionPane.showMessageDialog(null, "No hay productos todavía");
    }

    StringBuilder sb = verProductos("¿Qué producto vas a vender?");

    try {
      int numeroProducto = Integer.parseInt(JOptionPane.showInputDialog(null, sb.toString()));
      Producto productoAVender = productos.get(numeroProducto - 1);
      int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null,
          "¿Cuánto quieres vender?\nTu stock actual es: " + productoAVender.getStock()));

      if (cantidad <= 0) {
        JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0");
      }
      if (cantidad > productoAVender.getStock()) {
        JOptionPane.showMessageDialog(null, "No tienes la cantidad suficiente");
      }

      productoAVender.setStock(productoAVender.getStock() - cantidad);

    } catch (NumberFormatException error) {
      JOptionPane.showInputDialog(null, "Por favor, ingresa un número/valor válido.");
    }

  }
}
