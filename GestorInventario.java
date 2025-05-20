import javax.swing.JOptionPane;

class GestorInventario {

  private final Inventario inventario;

  public GestorInventario() {
    this.inventario = new Inventario();
  }

  public static void main(String[] args) {
    GestorInventario programa = new GestorInventario();
    programa.iniciar();
  }

  public void iniciar() {
    String[] opciones = { "Gestionar inventario", "Vender", "Salir" };
    while (true) {
      int opcion = JOptionPane.showOptionDialog(null,
          "¿Qué quieres hacer hoy?",
          "Menú principal",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          opciones,
          opciones[0]);

      switch (opcion) {
        case 0:
          boolean menuGestionar = true;
          while (menuGestionar) {
            String[] opcionesGestionar = { "Ver mis productos", "Valor del inventario", "Añadir Producto",
                "Modificar producto", "Volver" };
            int opcionGestionar = JOptionPane.showOptionDialog(
                null,
                "¿Qué quieres hacer?",
                "Submenú",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesGestionar,
                opcionesGestionar[0]);

            switch (opcionGestionar) {
              // Ver productos
              case 0:
                if (inventario.getProductos().isEmpty()) {
                  JOptionPane.showMessageDialog(null, "No tienes productos todavía, añade productos.");
                } else {
                  StringBuilder listaProductos = inventario.verProductos("");
                  JOptionPane.showMessageDialog(null, listaProductos.toString());
                }
                break;
              // Mostrar el valor del inventario
              case 1:
                JOptionPane.showMessageDialog(null,
                    "El valor total de tu inventario es: " + inventario.getValorInventario());
                break;
              // Añadir un producto
              case 2:
                inventario.agregarProducto();
                break;
              // Modificar productos
              case 3:
                inventario.modificarProducto();
                break;
              // Volver al menú anterior
              case 4:
                menuGestionar = false;
                break;
            }
          }
          break;
        // Vender.
        case 1:
          inventario.venderProducto();
          break;
        // Salir
        case 2:
          JOptionPane.showMessageDialog(null, "Hasta luego...");
          System.exit(0);
          break;
        // Cerrar ventana
        case -1:
          System.exit(0);
          break;
      }
    }
  }
}
