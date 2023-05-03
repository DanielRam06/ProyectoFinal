import javax.swing.*;                              // Biblioteca para crear interfaces gráficas de usuario (GUI)
import java.awt.*;                                 // Biblioteca para crear interfaces gráficas de usuario (GUI)
import java.awt.event.ActionEvent;                 // Biblioteca para representar eventos de acción en la interfaz de usuario
import java.awt.event.ActionListener;              // Biblioteca para escuchar eventos de acción en la interfaz de usuario

public class Arte { // Define la clase Arte
    private MyWindow myWindow; // Declara una variable de instancia para MyWindow

    public Arte(MyWindow myWindow) { // Constructor de la clase Arte
        this.myWindow = myWindow; // Inicializa la variable de instancia myWindow
    }

    // Clase interna para manejar la selección de color de fondo en el lienzo
    public class BackgroundColorSelectionListener implements ActionListener {
        private MyWindow myWindow; // Declara una variable de instancia para MyWindow

        public BackgroundColorSelectionListener(MyWindow myWindow) { // Constructor de la clase BackgroundColorSelectionListener
            this.myWindow = myWindow; // Inicializa la variable de instancia myWindow
        }

        // Método que se llama cuando se selecciona un botón de color de fondo
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource(); // Obtiene el botón que disparó el evento
            Color selectedColor = source.getBackground(); // Obtiene el color de fondo del botón
            myWindow.canvas.setBackground(selectedColor); // Establece el color de fondo del lienzo al color seleccionado
        }
    }

    // Clase interna para manejar la selección de color en el lienzo
    public class ColorSelectionListener implements ActionListener {
        private MyWindow myWindow; // Declara una variable de instancia para MyWindow

        public ColorSelectionListener(MyWindow myWindow) { // Constructor de la clase ColorSelectionListener
            this.myWindow = myWindow; // Inicializa la variable de instancia myWindow
        }

        // Método que se llama cuando se selecciona un botón de color
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource(); // Obtiene el botón que disparó el evento
            Color selectedColor = source.getBackground(); // Obtiene el color de fondo del botón
            myWindow.canvas.setColor(selectedColor); // Establece el color de dibujo en el lienzo al color seleccionado
            myWindow.selectedColorPanel.setBackground(selectedColor); // Establece el color de fondo del panel de color seleccionado
            myWindow.redSlider.setValue(selectedColor.getRed()); // Establece el valor del deslizador rojo
            myWindow.greenSlider.setValue(selectedColor.getGreen()); // Establece el valor del deslizador verde
            myWindow.blueSlider.setValue(selectedColor.getBlue()); // Establece el valor del deslizador azul
        }
    }
}