
import java.awt.Color;                      // Biblioteca para trabajar con colores en interfaces gráficas de usuario (GUI)
import java.awt.GridLayout;                 // Biblioteca para crear diseños de cuadrícula en interfaces gráficas de usuario (GUI)
import java.awt.event.ActionListener;       // Biblioteca para escuchar eventos de acción en la interfaz de usuario
import javax.swing.JButton;                 // Biblioteca para crear botones en interfaces gráficas de usuario (GUI)
import javax.swing.JPanel;                  // Biblioteca para crear paneles en interfaces gráficas de usuario (GUI)

public class ColorPalette extends JPanel {

    // Definir un arreglo de colores predefinidos
    private static final Color[] COLORS = {
        Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
        Color.CYAN, Color.MAGENTA, Color.PINK, Color.LIGHT_GRAY, Color.GRAY,
        Color.DARK_GRAY, Color.BLACK, Color.WHITE, new Color(139, 69, 19), new Color(128, 0, 128),new Color(255, 165, 0)
    };

    // Constructor de la clase ColorPalette
    public ColorPalette(ActionListener listener, int rows, int cols) {
        // Configurar el diseño de la paleta de colores
        setLayout(new GridLayout(rows, cols));

        // Crear botones de colores y añadirlos a la paleta
        for (Color color : COLORS) {
            JButton colorButton = new JButton();
            colorButton.setBackground(color);
            colorButton.addActionListener(listener);
            add(colorButton);
        }
    }
}