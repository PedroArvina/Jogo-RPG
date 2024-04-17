package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Jogar.inicio;

public class App {

    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Menu do Jogo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Configurando o fundo com a imagem convertida
        String imagePath = "C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\Jogo RPG\\Cenário\\Logo Frontal.jpg";
        ImageIcon background = new ImageIcon(imagePath);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0); // Ajusta o espaçamento entre os componentes

        // Configurando os botões
        JButton playButton = createCustomButton("Jogar");
        playButton.addActionListener(e -> iniciarJogo());
        JButton instructionsButton = createCustomButton("Instruções");
        instructionsButton.addActionListener(e -> instrucoes());
        JButton exitButton = createCustomButton("Sair");
        exitButton.addActionListener(e -> System.exit(0));

        frame.add(playButton, gbc);
        frame.add(instructionsButton, gbc);
        frame.add(exitButton, gbc);

        frame.setSize(1024, 768); // Define o tamanho conforme necessário
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBackground(new Color(220, 220, 220)); // Cor de fundo cinza claro
        button.setForeground(Color.BLACK); // Cor do texto branco
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borda visível e grossa
        button.setFocusPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 255, 224)); // Cor levemente amarelada
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(220, 220, 220)); // Retorna à cor cinza claro
            }
        });
        return button;
    }

    private static void iniciarJogo() {
        frame.setVisible(false); // Esconde a janela do menu
        new inicio(); // Cria uma instância da classe Inicio que configura e exibe sua própria GUI
    }


    private static void instrucoes() {
        Instrucoes.instrucoes();
        
    }
}


