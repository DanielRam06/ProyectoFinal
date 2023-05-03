import java.awt.Color;                                 // Importa la clase Color de la librería AWT de Java para trabajar con colores.
import java.awt.Graphics;                              // Importa la clase Graphics para realizar operaciones de dibujo básicas.
import java.awt.Graphics2D;                            // Importa la clase Graphics2D para operaciones de dibujo más avanzadas.
import java.awt.event.MouseEvent;                      // Importa las clases de eventos de ratón para manejar la interacción del usuario con el ratón.
import java.awt.event.MouseListener;                   // Importa la clase MouseListener para eventos de clic de ratón.
import java.awt.event.MouseMotionListener;             // Importa la clase MouseMotionListener para eventos de movimiento de ratón.
import java.awt.image.BufferedImage;                   // Importa la clase BufferedImage para trabajar con imágenes en memoria.
import javax.swing.JPanel;                              // Importa la clase JPanel para crear un panel personalizado.

public class Canvas extends JPanel implements MouseListener, MouseMotionListener { // Define la clase Canvas que extiende de JPanel e implementa las interfaces MouseListener y MouseMotionListener.
    private int x, y;                                  // Variables para almacenar la posición actual del ratón.
    private Color currentColor = Color.BLUE;           // Variable para almacenar el color actual.
    public static final int ROWS = 25;                 // Constante para el número de filas.
    public static final int COLS = 25;                 // Constante para el número de columnas.
    public static final int CELL_SIZE = 20;            // Constante para el tamaño de cada celda.
    private Color[][] grid = new Color[ROWS][COLS];    // Matriz para representar la cuadrícula de celdas con valores de color.
    private BufferedImage img;                         // Imagen en el lienzo.
    private Graphics2D g2d;                            // Objeto Graphics2D para dibujar en la imagen.

    public Canvas() {                                  // Constructor de la clase Canvas.
        addMouseListener(this);                        // Agrega un escucha de eventos de ratón al panel.
        addMouseMotionListener(this);                  // Agrega un escucha de eventos de movimiento de ratón al panel.
        setBackground(Color.black);                    // Establece el color de fondo del panel en negro.
        img = new BufferedImage(ROWS * CELL_SIZE, COLS * CELL_SIZE, BufferedImage.TYPE_INT_ARGB); // Crea una imagen en memoria con las dimensiones adecuadas.
        g2d = img.createGraphics();                    // Crea un objeto Graphics2D a partir de la imagen en memoria.
        g2d.setColor(currentColor);                    // Establece el color actual en el objeto Graphics2D.
    }

    @Override
    public void paintComponent(Graphics g) {           // Método para pintar el panel.
        super.paintComponent(g);                       // Llama al método super para pintar el fondo.
        g.drawImage(img, 0, 0, null);                  // Dibuja la imagen en el panel.

        for (int row = 0; row < ROWS; row++) {         // Itera sobre cada fila.
            for (int col = 0; col < COLS; col++) {     // Itera sobre cada columna.
                if (grid[row][col] != null) {         // Si la celda tiene un valor de color.
                    g.setColor(grid[row][col]);        // Establece el color al valor de la celda.
                    g.fillRect(row * CELL_SIZE, col * CELL_SIZE, CELL_SIZE, CELL_SIZE); // Rellena la celda con elcolor establecido.
                }
                g.setColor(Color.GRAY);                // Establece el color a gris para los bordes de las celdas.
                g.drawRect(row * CELL_SIZE, col * CELL_SIZE, CELL_SIZE, CELL_SIZE); // Dibuja el borde de la celda.
            }
        }

        g.setColor(currentColor);                      // Establece el color actual para el marcador de posición del ratón.
        g.fillOval(x - 5, y - 5, 10, 10);              // Dibuja un círculo en la posición del ratón.
    }

    @Override
    public void mouseClicked(MouseEvent e) {           // Método para los clics de ratón.
        x = e.getX() / CELL_SIZE;                     // Obtiene las coordenadas x e y de la celda.
        y = e.getY() / CELL_SIZE;

        if (x < ROWS && y < COLS) {                   // Si el clic está dentro de los límites de la cuadrícula.
            grid[x][y] = currentColor;                // Establece el color de la celda clicada al color actual.
            g2d.setColor(currentColor);
            g2d.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            repaint();                                // Vuelve a pintar el panel para actualizar la cuadrícula con el nuevo color.
        }
    }

    // Métodos vacíos para los eventos de ratón no utilizados.
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {           // Método para los eventos de arrastre del ratón.
    }

    @Override
    public void mouseMoved(MouseEvent e) {             // Método para los eventos de movimiento del ratón.
        x = e.getX();                                  // Actualiza las coordenadas x e y del ratón.
        y = e.getY();
        repaint();                                     // Vuelve a pintar el panel para actualizar la posición del marcador de posición del ratón.
    }

    public void setColor(Color color) {               // Método para establecer el color actual.
        this.currentColor = color;                    // Establece el color actual como el color pasado como argumento.
    }

    public BufferedImage getImg() {                   // Método para obtener la imagen en memoria.
        return img;
    }
    
    public void setImage(BufferedImage image) {       // Establece la imagen (BufferedImage) a la imagen proporcionada.
        img = image;
        g2d = img.createGraphics();                   // Crea un objeto Graphics2D a partir de la imagen proporcionada.
        repaint();                                    // Vuelve a pintar el componente, actualizando su apariencia.
    }

    public void clear() {                             // Método para limpiar el lienzo.
        img = new BufferedImage(ROWS * CELL_SIZE, COLS * CELL_SIZE, BufferedImage.TYPE_INT_ARGB); // Crea una nueva imagen en memoria.
        g2d = img.createGraphics();                   // Crea un objeto Graphics2D a partir de la nueva imagen.
        g2d.setColor(getBackground());                // Establece el color del objeto Graphics2D al color de fondo actual.
        g2d.fillRect(0, 0, ROWS * CELL_SIZE, COLS * CELL_SIZE); // Rellena el rectángulo que representa el área de dibujo con el color de fondo.
        grid = new Color[ROWS][COLS];                // Crea una nueva matriz de colores para representar la cuadrícula.
        repaint();                                   // Vuelve a pintar el componente, actualizando su apariencia.
    }

}