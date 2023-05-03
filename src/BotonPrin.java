import javax.swing.*;                                   // Biblioteca para crear interfaces gráficas de usuario (GUI)
import javax.swing.filechooser.FileNameExtensionFilter; // Biblioteca para filtrar archivos en un diálogo de selección de archivo
import java.awt.*;                                      // Biblioteca para crear interfaces gráficas de usuario (GUI)
import java.awt.event.ActionEvent;                      // Biblioteca para representar eventos de acción en la interfaz de usuario
import java.awt.event.ActionListener;                   // Biblioteca para escuchar eventos de acción en la interfaz de usuario
import java.awt.image.BufferedImage;                    // Biblioteca para representar imágenes y manipularlas
import java.io.File;                                    // Biblioteca para representar archivos en el sistema de archivos
import java.io.IOException;                             // Biblioteca para manejar excepciones de entrada/salida de archivos
import javax.imageio.ImageIO;                           // Biblioteca para leer y escribir imágenes en diferentes formatos de archivo (JPG, PNG, GIF, etc.)

public class BotonPrin { // Define la clase BotonPrin
    private MyWindow myWindow; // Declara una variable de instancia para MyWindow

    public BotonPrin(MyWindow myWindow) { // Constructor de la clase BotonPrin
        this.myWindow = myWindow; // Inicializa la variable de instancia myWindow
    }

    // Clase interna para manejar la acción de guardar una imagen
    public class SaveImageListener implements ActionListener {
        private MyWindow myWindow; // Declara una variable de instancia para MyWindow

        public SaveImageListener(MyWindow myWindow) { // Constructor de la clase SaveImageListener
            this.myWindow = myWindow; // Inicializa la variable de instancia myWindow
        }

        // Método que se llama cuando se hace clic en el botón de guardar
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser(); // Crea un nuevo JFileChooser
            fileChooser.setDialogTitle("Guardar imagen como"); // Establece el título de la ventana
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop/")); // Establece el directorio actual

            // Agrega filtros de archivo para los formatos de imagen JPG, PNG y GIF
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG Image", "jpg"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Image", "png"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("GIF Image", "gif"));

            int returnVal = fileChooser.showSaveDialog(myWindow); // Muestra la ventana de diálogo y obtiene la respuesta del usuario
            if (returnVal == JFileChooser.APPROVE_OPTION) { // Si el usuario selecciona "Guardar"
                File file = fileChooser.getSelectedFile(); // Obtiene el archivo seleccionado por el usuario
                String filePath = file.getAbsolutePath(); // Obtiene la ruta del archivo

                FileNameExtensionFilter selectedFilter = (FileNameExtensionFilter) fileChooser.getFileFilter(); // Obtiene el filtro seleccionado
                String extension = selectedFilter.getExtensions()[0]; // Obtiene la extensión del filtro

                if (!filePath.toLowerCase().endsWith("." + extension)) { // Si el nombre del archivo no termina con la extensión del filtro
                    file = new File(filePath + "." + extension); // Agrega la extensión al nombre del archivo
                }

                try {
                    BufferedImage imgToSave = new BufferedImage(myWindow.canvas.getWidth(), myWindow.canvas.getHeight(), BufferedImage.TYPE_INT_RGB); // Crea una nueva imagen de tamaño igual al lienzo
                    Graphics2D g2d = imgToSave.createGraphics(); // Obtiene un objeto Graphics2D para la imagen

                    g2d.setColor(myWindow.canvas.getBackground()); // Establece el color de fondo en el lienzo como color de fondo de la imagen
                    g2d.fillRect(0, 0, myWindow.canvas.getWidth(), myWindow.canvas.getHeight()); // Rellena la imagen con el color de fondo
                    g2d.drawImage(myWindow.canvas.getImg(), 0, 0, null); // Dibuja la imagen del lienzo en la imagen

                    g2d.setColor(Color.GRAY); // Establece el color gris para las líneas del tablero
                    for (int row = 0; row < myWindow.canvas.ROWS; row++) {
                        for (int col = 0; col < myWindow.canvas.COLS; col++) {
                            g2d.drawRect(row * myWindow.canvas.CELL_SIZE, col * myWindow.canvas.CELL_SIZE, myWindow.canvas.CELL_SIZE, myWindow.canvas.CELL_SIZE); // Dibuja el tablero en la imagen
                        }
                    }

                    ImageIO.write(imgToSave, extension, file); // Guarda la imagen en el archivo
                    g2d.dispose(); // Libera los recursos de Graphics2D
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    // Clase interna para manejar la acción de cargar una imagen
    public class LoadImageListener implements ActionListener {
        private MyWindow myWindow; // Declara una variable de instancia para MyWindow

        public LoadImageListener(MyWindow myWindow) { // Constructor de la clase LoadImageListener
            this.myWindow = myWindow; // Inicializa la variable de instancia myWindow
        }

        // Método que se llama cuando se hace clic en el botón de cargar
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser(); // Crea un nuevo JFileChooser
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF & PNG Images", "jpg", "gif", "png"); // Crea un filtro para los formatos de imagen
            fileChooser.setFileFilter(filter); // Establece el filtro en el JFileChooser
            int returnVal = fileChooser.showOpenDialog(myWindow); // Muestra la ventana de diálogo y obtiene la respuesta del usuario
            if (returnVal == JFileChooser.APPROVE_OPTION) { // Si el usuario selecciona "Abrir"
                File file = fileChooser.getSelectedFile(); // Obtiene el archivo seleccionado por el usuario
                try {
                    BufferedImage image = ImageIO.read(file); // Lee la imagen del archivo
                    myWindow.canvas.setImage(image); // Establece la imagen en el lienzo
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Clase interna para manejar la acción de borrar el lienzo
    public class ClearCanvasListener implements ActionListener {
    	private MyWindow myWindow; // Declara una variable de instancia para MyWindow

        public ClearCanvasListener(MyWindow myWindow) { // Constructor de la clase ClearCanvasListener
            this.myWindow = myWindow; // Inicializa la variable de instancia myWindow
        }

        // Método que se llama cuando se hace clic en el botón de borrar
        @Override
        public void actionPerformed(ActionEvent e) {
            myWindow.canvas.clear(); // Borra el lienzo
        }
    }
    }