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
            String[] opcionesGestionar = { "Ver mis productos", "Valor del inventario", "Añadir Producto", "Volver" };
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
                  int contador = 1;
                  for (Producto producto : inventario.getProductos()) {
                    JOptionPane.showMessageDialog(null,
                        contador + ". " + producto.toString());
                    contador++;
                  }
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
              case 3:
                menuGestionar = false;
                break;
            }
          }
          break;
        // Vender.
        case 1:

          JOptionPane.showMessageDialog(null, "Todavía no está programada esta función, vuelve luego.");
          break;
        case 2:
          JOptionPane.showMessageDialog(null, "Hasta luego...");
          System.exit(0);
          break;
      }
    }
  }
}
