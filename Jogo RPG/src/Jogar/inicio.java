package Jogar;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class inicio {

    private JFrame frame;
    private JLabel imageLabel;
    private List<ImageIcon> images;
    private int imageIndex;

    public inicio() {
        images = new ArrayList<>();
        images.add(new ImageIcon("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\Jogo RPG\\Cenário\\1.jpg"));
        images.add(new ImageIcon("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\Jogo RPG\\Cenário\\2.jpg"));
        images.add(new ImageIcon("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\Jogo RPG\\Cenário\\3.jpg"));
        images.add(new ImageIcon("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\Jogo RPG\\Cenário\\4.jpg"));
        images.add(new ImageIcon("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\Jogo RPG\\Cenário\\5.jpg"));
        imageIndex = 0;

        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Início da História");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        imageLabel = new JLabel(images.get(0));
        frame.add(imageLabel);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                advanceImage();
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.requestFocusInWindow(); // Necessário para capturar eventos de teclado
    }

    private void advanceImage() {
        imageIndex++;
        if (imageIndex < images.size()) {
            imageLabel.setIcon(images.get(imageIndex));
            frame.repaint(); // Repinta o frame para mostrar a nova imagem
        } else {
            frame.dispose(); // Fecha a janela de introdução
            iniciarMundo(); // Chama o método para iniciar o mundo ASCII
        }
    }

    private void iniciarMundo() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MundoASCII().iniciarMundo(); // Supondo que a classe MundoASCII tem um método iniciarMundo()
            }
        });
    }
    }

