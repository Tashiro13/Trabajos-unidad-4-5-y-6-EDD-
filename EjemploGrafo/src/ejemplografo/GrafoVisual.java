/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplografo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
/**
  * Numero de Control: 23550373
 * @author Alexis Cruz Martinez
 * Materia: Estructura de Datos
 */
public class GrafoVisual extends javax.swing.JFrame {

    private Grafo grafo;
    private Map<String, Nodo> nodos;
    private Nodo nodoSeleccionado = null;
    private boolean modoAgregarNodo = false;
    private boolean modoAgregarArista = false;
    /**
     * Creates new form GrafoVisual
     */
    public GrafoVisual() {
        grafo = new Grafo();
        nodos = new HashMap<>();

        setTitle("Grafo Visual");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g; // Usamos Graphics2D para ajustar el grosor de líneas
                
                // Configurar la fuente para el texto de ID y peso
                Font fuenteGrande = new Font("Arial", Font.BOLD, 16); // Fuente grande para el texto
                g2d.setFont(fuenteGrande);

                // Dibujar aristas
                g2d.setColor(Color.PINK); // Color de la línea de la arista
                g2d.setStroke(new BasicStroke(3));
                for (Arista arista : grafo.getAristas()) {
                    Nodo origen = arista.getOrigen();
                    Nodo destino = arista.getDestino();
                    g2d.drawLine(origen.getX(), origen.getY(), destino.getX(), destino.getY());

                    // Cambiar color del texto del peso de la arista
                    g2d.setColor(Color.YELLOW); // Cambia Color.GREEN al color deseado
                    g2d.drawString(String.valueOf(arista.getPeso()),
                            (origen.getX() + destino.getX()) / 2,
                            (origen.getY() + destino.getY()) / 2);

                    g2d.setColor(Color.GRAY); // Restablece el color para las líneas de aristas
                }

                // Dibujar nodos con borde negro y grosor de borde adicional
                for (Nodo nodo : grafo.getNodos()) {
                    g2d.setColor(Color.BLACK); // Color del borde del nodo
                    g2d.setStroke(new BasicStroke(5)); // Grosor del borde del nodo
                    g2d.drawOval(nodo.getX() - 15, nodo.getY() - 15, 30, 30); // Borde negro del nodo
                    g2d.setColor(Color.RED); // Color de relleno del nodo
                    g2d.fillOval(nodo.getX() - 15, nodo.getY() - 15, 30, 30); // Relleno del nodo

                    // Mostrar el ID del nodo sobre el nodo
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(nodo.getId(), nodo.getX() - 5, nodo.getY() - 20);
                }
            }
        };
        
        // Listener para el panel de dibujo
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modoAgregarNodo) {
                    // Agregar un nodo en la posición del clic
                    String id = JOptionPane.showInputDialog("Ingrese el ID del nodo:");
                    if (id != null && !id.trim().isEmpty()) { // Validar si el ID no es nulo o vacío
                        Nodo nodo = new Nodo(id, e.getX(), e.getY());
                        nodos.put(id, nodo);
                        grafo.agregarNodo(nodo);
                        repaint();
                    }
                } else if (modoAgregarArista) {
                    // Selección de nodos para crear una arista
                    Nodo nodoClic = obtenerNodoCercano(e.getX(), e.getY());
                    if (nodoClic != null) {
                        if (nodoSeleccionado == null) {
                            nodoSeleccionado = nodoClic;
                        } else {
                            int peso;
                            try {
                                peso = Integer.parseInt(JOptionPane.showInputDialog("Peso de la arista entre " + nodoSeleccionado.getId() + " y " + nodoClic.getId() + ":"));
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                                nodoSeleccionado = null; // Reiniciar selección
                                return;
                            }

                            grafo.agregarArista(nodoSeleccionado, nodoClic, peso);
                            nodoSeleccionado = null;
                            repaint();
                        }
                    }
                }
            }
        });
        
        // Botón para activar el modo "Agregar Nodo"
        JButton botonAgregarNodo = new JButton("Agregar Nodo");
        botonAgregarNodo.addActionListener(e -> {
            modoAgregarNodo = true;
            modoAgregarArista = false;
            nodoSeleccionado = null;
        });
        
        // Botón para activar el modo "Agregar Arista"
        JButton botonAgregarArista = new JButton("Agregar Arista");
        botonAgregarArista.addActionListener(e -> {
            modoAgregarNodo = false;
            modoAgregarArista = true;
            nodoSeleccionado = null;
        });
        
        // Botón para calcular la ruta más corta
        JButton botonDijkstra = new JButton("Calcular Ruta Más Corta");
        botonDijkstra.addActionListener(e -> {
            String inicioId = JOptionPane.showInputDialog("ID del nodo inicial:");
            Nodo inicio = nodos.get(inicioId);

            if (inicio != null) {
                Map<Nodo, Integer> distancias = grafo.dijkstra(inicio);
                StringBuilder resultado = new StringBuilder("Distancias desde " + inicioId + ":\n");
                for (Map.Entry<Nodo, Integer> entry : distancias.entrySet()) {
                    resultado.append(entry.getKey().getId()).append(": ").append(entry.getValue()).append("\n");
                }
                JOptionPane.showMessageDialog(this, resultado.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Nodo inicial no encontrado.");
            }
        });
        
        JButton botonLimpiar = new JButton("Limpiar Grafo");
        botonLimpiar.addActionListener(e -> {
            grafo = new Grafo(); // Reinicia la estructura de datos del grafo
            nodos.clear(); // Limpia el mapa de nodos
            nodoSeleccionado = null;
            repaint(); // Redibuja el panel para que esté vacío
        });
        
        JPanel botones = new JPanel();
        botones.add(botonAgregarNodo);
        botones.add(botonAgregarArista);
        botones.add(botonDijkstra);
        botones.add(botonLimpiar);

        add(panel, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }
    
    // Método auxiliar para encontrar el nodo más cercano a las coordenadas clicadas
    private Nodo obtenerNodoCercano(int x, int y) {
        for (Nodo nodo : grafo.getNodos()) {
            int dx = nodo.getX() - x;
            int dy = nodo.getY() - y;
            if (Math.sqrt(dx * dx + dy * dy) < 15) { // Umbral de cercanía
                return nodo;
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GrafoVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GrafoVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GrafoVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GrafoVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            SwingUtilities.invokeLater(() -> {
            GrafoVisual frame = new GrafoVisual();
            frame.setVisible(true);
        });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
