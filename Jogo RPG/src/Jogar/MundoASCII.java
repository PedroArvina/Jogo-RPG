package Jogar;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MundoASCII {

    private PainelJogo paineljogo; // Adicionado como membro da classe

    public void iniciarMundo() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("A Lenda de Elda");
        
        paineljogo = new PainelJogo();
        window.add(paineljogo);
        
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        paineljogo.startGameThread(); // Chamada do método correta, dentro de iniciarMundo
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MundoASCII().iniciarMundo();
            }
        });
    }
}
