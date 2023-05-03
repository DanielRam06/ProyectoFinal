import javax.swing.JPanel;                  // Biblioteca para crear paneles en interfaces gráficas de usuario (GUI)
import javax.swing.JSlider;                 // Biblioteca para crear deslizadores en interfaces gráficas de usuario (GUI)
import javax.swing.event.ChangeEvent;       // Biblioteca para representar eventos de cambio en la interfaz de usuario
import javax.swing.event.ChangeListener;    // Biblioteca para escuchar eventos de cambio en la interfaz de usuario
import javax.swing.ImageIcon;               // Biblioteca para cargar imágenes en interfaces gráficas de usuario (GUI)
import javax.swing.JButton;                // Biblioteca para crear botones en interfaces gráficas de usuario (GUI)
import javax.swing.JFrame;                  // Biblioteca para crear marcos en interfaces gráficas de usuario (GUI)
import javax.swing.JLabel;                  // Biblioteca para crear etiquetas de texto en interfaces gráficas de usuario (GUI)

import java.awt.Color;                      // Biblioteca para trabajar con colores en interfaces gráficas de usuario (GUI)
import java.awt.Font;                       // Biblioteca para trabajar con fuentes en interfaces gráficas de usuario (GUI)
import java.awt.GradientPaint;              // Biblioteca para crear un degradado de color en interfaces gráficas de usuario (GUI)
import java.awt.Graphics;                   // Biblioteca para dibujar gráficos en interfaces gráficas de usuario (GUI)
import java.awt.Graphics2D;                 // Biblioteca para dibujar gráficos 2D en interfaces gráficas de usuario (GUI)
import java.awt.Image;                      // Biblioteca para trabajar con imágenes en interfaces gráficas de usuario (GUI)
import java.awt.event.ActionEvent;          // Biblioteca para representar eventos de acción en la interfaz de usuario
import java.awt.event.ActionListener;       // Biblioteca para escuchar eventos de acción en la interfaz de usuario
import java.awt.event.MouseEvent;           // Biblioteca para representar eventos del mouse en la interfaz de usuario
import java.awt.event.MouseListener;        // Biblioteca para escuchar eventos del mouse en la interfaz de usuario
import java.awt.image.BufferedImage;        // Biblioteca para representar imágenes y manipularlas
import java.awt.Dimension;                  // Biblioteca para representar las dimensiones de un objeto en la interfaz de usuario

public class MyWindow extends JFrame implements ActionListener, MouseListener, ChangeListener {
    JPanel contentPane; // Panel principal
    Canvas canvas; // Lienzo para dibujar
    JLabel lbl; // Etiqueta para mostrar coordenadas
    static Graphics g; // Objeto Graphics para dibujar en el lienzo
    static BufferedImage img; // Imagen para guardar el contenido del lienzo
    int width, height, x, y; // Dimensiones de la ventana y coordenadas del ratón

    // Declara las variables de instancia para los componentes de la interfaz de usuario
    ColorPalette colorPalette; // Paleta de colores para seleccionar colores
    JPanel selectedColorPanel; // Panel que muestra el color seleccionado
    JSlider redSlider, greenSlider, blueSlider; // Controles deslizantes para ajustar el color seleccionado
    JLabel createArtLabel, selectedColorLabel; // Etiquetas de texto
    JButton gridColorButton1, gridColorButton2, backgroundColorButton1, backgroundColorButton2; // Botones para seleccionar colores
    JButton saveButton, loadButton, clearButton; // Botones para guardar, cargar y borrar imágenes

    // Constructor de la ventana principal
    public MyWindow(int width, int height) {
        this.width = width;
        this.height = height;
      
        // Inicializa y configura los componentes
        components();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("My Drawing App");
        setVisible(true);
    
    }
 // Método para inicializar y agregar los componentes a la ventana
    private void components() {
        contentPane = new JPanel(); // Inicializa el panel principal
        lbl = new JLabel(""); // Inicializa la etiqueta de las coordenadas
        canvas = new Canvas(); // Inicializa el lienzo
        BotonPrin botonPrin = new BotonPrin(this); // Inicializa los botones principales
        Arte arte = new Arte(this); // Inicializa la lógica de dibujo
        
        // Inicializa y configura los botones para guardar, cargar y borrar imágenes
        saveButton = new JButton();
        ImageIcon saveIcon = new ImageIcon("images/save.png");
        Image scaledImage = saveIcon.getImage().getScaledInstance(170, 240, Image.SCALE_SMOOTH);
        saveButton.setIcon(new ImageIcon(scaledImage));
        saveButton.setBounds(canvas.getX() + canvas.getWidth() + 150, canvas.getY() + canvas.getHeight() + 120, 135, 60);
        saveButton.addActionListener(botonPrin.new SaveImageListener(this));
        saveButton.setBorderPainted(false);
        saveButton.setContentAreaFilled(false);


        // boton para limpiar el canvas
        clearButton = new JButton();
        ImageIcon clearIcon = new ImageIcon("images/eraser.png");
        Image scaledImage1 = clearIcon.getImage().getScaledInstance(170, 240, Image.SCALE_SMOOTH);
        clearButton.setIcon(new ImageIcon(scaledImage1));
        clearButton.setBounds(saveButton.getX(), saveButton.getY() + saveButton.getHeight() + 80, 135, 60);
        clearButton.addActionListener(botonPrin.new ClearCanvasListener(this));
        clearButton.setBorderPainted(false);
        clearButton.setContentAreaFilled(false);

        // boton para abrir
        loadButton = new JButton();
        ImageIcon loadIcon = new ImageIcon("images/load.png");
        Image scaledImage2 = loadIcon.getImage().getScaledInstance(170, 240, Image.SCALE_SMOOTH);
        loadButton.setIcon(new ImageIcon(scaledImage2));
        loadButton.setBounds(saveButton.getX(), clearButton.getY() + clearButton.getHeight() + 90, 135, 60);
        loadButton.addActionListener(botonPrin.new LoadImageListener(this));
        loadButton.setBorderPainted(false);
        loadButton.setContentAreaFilled(false);

    contentPane = new JPanel() {
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        
	        int width = getWidth();
	        int height = getHeight();
	       

	        Color color1 = new Color(230, 230, 230);
	        Color color2 = new Color(200, 200, 200);
	        Color color3 = new Color(150, 150, 150);
	        Color color4 = new Color(100, 100, 100);

	        int numSections = 3;
	        int sectionHeight = height / numSections;

	        GradientPaint gp1 = new GradientPaint(0, 0, color1, 0, sectionHeight, color2);
	        GradientPaint gp2 = new GradientPaint(0, sectionHeight, color2, 0, 2 * sectionHeight, color3);
	        GradientPaint gp3 = new GradientPaint(0, 2 * sectionHeight, color3, 0, 3 * sectionHeight, color4);

	        g2d.setPaint(gp1);
	        g2d.fillRect(0, 0, width, sectionHeight);

	        g2d.setPaint(gp2);
	        g2d.fillRect(0, sectionHeight, width, 2 * sectionHeight);


	        g2d.setPaint(gp3);
	        g2d.fillRect(0, 2 * sectionHeight, width, 3 * sectionHeight);
	    }
	};
	// Establece el diseño manual de la interfaz gráfica
	contentPane.setLayout(null);

	// Establece la posición y el tamaño del panel principal
	contentPane.setBounds(0, 0, width, height);

	// Agrega los botones de guardar, cargar y borrar arte al panel principal
	contentPane.add(saveButton);
	contentPane.add(loadButton);
	contentPane.add(clearButton);

	// Configura la etiqueta de título del arte
	lbl.setBounds(0, 0, width, height);
	lbl.setForeground(Color.white);
	lbl.setFont(new Font("Serif", Font.PLAIN, 50));

	// Configura el panel de dibujo "canvas"
	canvas.setBounds((width / 2) - 250, (height / 2) - 300, 500, 500);

	// Configura la etiqueta "CREA TU PROPIO ARTE"
	createArtLabel = new JLabel("CREA TU PROPIO ARTE...");
	createArtLabel.setBounds(canvas.getX() -100, canvas.getY() - 80, 800, 40);
	createArtLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 50));

	// Configura la etiqueta que muestra el color seleccionado
	selectedColorLabel = new JLabel("Color elegido");
	selectedColorLabel.setBounds(canvas.getX() + canvas.getWidth() + 20, canvas.getY() + canvas.getHeight() + 30, 150, 20);
	selectedColorLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		
	// Crea el panel de paleta de colores
	colorPalette = new ColorPalette(arte.new ColorSelectionListener(this), 4, 4);
	colorPalette.setBounds(canvas.getX() + canvas.getWidth() + 20, canvas.getY() + 50, 170, 250);

	// Crea el botón de selección de color de fondo 1
	backgroundColorButton1 = createColorButton(Color.BLACK, canvas.getX() + canvas.getWidth() + 50, colorPalette.getY() + colorPalette.getHeight() + 100);
	backgroundColorButton1.addActionListener(arte.new BackgroundColorSelectionListener(this));

	// Crea el botón de selección de color de fondo 2
	backgroundColorButton2 = createColorButton(Color.WHITE, backgroundColorButton1.getX() + backgroundColorButton1.getWidth() + 10, backgroundColorButton1.getY());
	backgroundColorButton2.addActionListener(arte.new BackgroundColorSelectionListener(this));

	// Configura el panel que muestra el color seleccionado
	selectedColorPanel = new JPanel();
	selectedColorPanel.setBackground(Color.WHITE);
	selectedColorPanel.setBounds(canvas.getX() + canvas.getWidth() + 20, canvas.getY() + canvas.getHeight() + 60, 100, 50);

	// Crea los controles deslizantes de color
	redSlider = createColorSlider(Color.RED, canvas.getX() + canvas.getWidth() / 2 - 150, canvas.getY() + canvas.getHeight() + 8);
	greenSlider = createColorSlider(Color.GREEN, redSlider.getX(), redSlider.getY() + redSlider.getHeight() + 18);
	blueSlider = createColorSlider(Color.BLUE, greenSlider.getX(), greenSlider.getY() + greenSlider.getHeight() + 21);

	// Agrega todos los componentes a la interfaz gráfica
	canvas.addMouseListener(this);
	contentPane.add(canvas);
	contentPane.add(lbl);
	contentPane.add(colorPalette);
	contentPane.add(selectedColorPanel);
	contentPane.add(redSlider);
	contentPane.add(greenSlider);
	contentPane.add(blueSlider);
	contentPane.add(createArtLabel);
	contentPane.add(selectedColorLabel);
	contentPane.add(backgroundColorButton1);
	contentPane.add(backgroundColorButton2);
	contentPane.add(backgroundColorButton2);
	contentPane.add(saveButton);
	contentPane.add(loadButton);
	contentPane.add(clearButton);

	add(contentPane);
	}

 // Crea un JSlider personalizado para controlar el valor del color (rojo, verde o azul) en el rango de 0 a 255
    private JSlider createColorSlider(Color color, int x, int y) {
        // establece los 3 JSlider con un rango de 0 a 255
        JSlider slider = new JSlider(0, 255);

        // Configura la visualización de las marcas y etiquetas
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);

        // Establece el espaciado de las marcas principales y secundarias
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);

        // Establece el color de primer plano y fondo del JSlider
        slider.setForeground(color);
        slider.setBackground(color);

        // Establece la posición y el tamaño del JSlider
        slider.setBounds(x, y, 300, 30);

        // Agrega un ChangeListener para escuchar los cambios en el valor del JSlider
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Actualiza el color seleccionado cuando cambia el valor del JSlider
                actualizarColorSeleccionadoDesdeDeslizadores();
            }
        });

        return slider;
    }

    // Actualiza el color seleccionado a partir de los valores de los deslizadores de color (rojo, verde y azul)
    private void actualizarColorSeleccionadoDesdeDeslizadores() {
        // Crea un nuevo objeto Color usando los valores de los deslizadores
        Color colorSeleccionado = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());

        // Establece el color de fondo del panel de color seleccionado y la herramienta de dibujo en el lienzo
        selectedColorPanel.setBackground(colorSeleccionado);
        canvas.setColor(colorSeleccionado);
    }

    // Método vacío que puede ser sobreescrito para manejar eventos de acción
    public void actionPerformed(ActionEvent event) {
    }

    // Al hacer clic en el lienzo, actualiza las coordenadas (x, y) y muestra las coordenadas en una etiqueta
    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        lbl.setText(x + " " + y);
    }

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
    public void stateChanged(ChangeEvent e) {
    }

    // Crea un botón personalizado para seleccionar un color
    private JButton createColorButton(Color color, int x, int y) {
        // Crea un nuevo JButton
        JButton button = new JButton();

        // Establece el color de fondo del botón
        button.setBackground(color);

        // Establece la posición y el tamaño del botón
        button.setBounds(x, y, 30, 30);

        // Establece las dimensiones preferidas del botón
        button.setPreferredSize(new Dimension(30, 30));

        return button;
    }
}