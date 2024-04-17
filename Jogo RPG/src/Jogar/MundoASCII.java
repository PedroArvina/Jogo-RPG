package Jogar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MundoASCII {
    private static final char PAREDE = '#';
    private static final char GRAMA = ' ';
    private static final char JOGADOR = '@';
    private static final int LARGURA = 50;
    private static final int ALTURA = 50;
    private JFrame frame;
    private JTextArea areaMundo;
    private char[][] mundo;
    private int jogadorX = 25; // Posição central X do jogador
    private int jogadorY = 25; // Posição central Y do jogador

    public MundoASCII() {
        iniciarMundo();
        criarInterfaceGrafica();
    }

    private void iniciarMundo() {
        mundo = new char[ALTURA][LARGURA];
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                mundo[i][j] = GRAMA;
            }
        }

        // Adiciona paredes ao redor das bordas do mundo
        for (int i = 0; i < ALTURA; i++) {
            mundo[i][0] = PAREDE;
            mundo[i][LARGURA - 1] = PAREDE;
        }
        for (int j = 0; j < LARGURA; j++) {
            mundo[0][j] = PAREDE;
            mundo[ALTURA - 1][j] = PAREDE;
        }

        // Coloca o jogador na posição central
        mundo[jogadorY][jogadorX] = JOGADOR;
    }

    private void criarInterfaceGrafica() {
        frame = new JFrame("Mundo ASCII");
        areaMundo = new JTextArea(ALTURA, LARGURA);
        areaMundo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaMundo.setEditable(false);
        areaMundo.setForeground(Color.BLACK);
        areaMundo.setBackground(Color.WHITE);

        // Adicionar barras de rolagem
        JScrollPane scrollPane = new JScrollPane(areaMundo);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // Ajusta o frame ao tamanho preferido dos subcomponentes
        frame.setLocationRelativeTo(null);

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                moverJogador(e.getKeyCode());
            }
        });

        // Inicializa o conteúdo da área de texto com o mundo
        atualizarAreaMundo();
        frame.setVisible(true);
    }

    private void moverJogador(int keyCode) {
        int dx = 0, dy = 0;

        switch (keyCode) {
            case KeyEvent.VK_W: dy = -1; break;
            case KeyEvent.VK_S: dy = +1; break;
            case KeyEvent.VK_A: dx = -1; break;
            case KeyEvent.VK_D: dx = +1; break;
        }

        int novoX = jogadorX + dx;
        int novoY = jogadorY + dy;

        // Verifica se o movimento é válido antes de mover o jogador
        if (novoX >= 0 && novoX < LARGURA && novoY >= 0 && novoY < ALTURA && mundo[novoY][novoX] == GRAMA) {
            // Atualiza a posição antiga do jogador para grama
            mundo[jogadorY][jogadorX] = GRAMA;
            
            // Atualiza a nova posição do jogador
            jogadorX = novoX;
            jogadorY = novoY;
            mundo[jogadorY][jogadorX] = JOGADOR;
            
            // Atualiza a área de texto para refletir a nova posição do jogador
            atualizarAreaMundo();
        }
    }

    private void atualizarAreaMundo() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                sb.append(mundo[i][j]);
            }
            sb.append("\n");
        }
        areaMundo.setText(sb.toString());

        // Mova o cursor para a posição do jogador
        areaMundo.setCaretPosition(jogadorY * (LARGURA + 1) + jogadorX);
        areaMundo.requestFocusInWindow(); // Requisita foco para capturar eventos de teclado
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MundoASCII::new);
    }
}
