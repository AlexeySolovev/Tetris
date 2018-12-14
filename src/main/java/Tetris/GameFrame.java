package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
  private static Scena scena;
    GameFrame(){
        super();
        JButton ButtonPlay = new JButton("Play");
        ButtonPlay.addActionListener(new PlayActionListner());
        setBounds(0,0,100,400);
        JPanel panelCentr = new JPanel();
        setLayout(new BorderLayout());
        scena =new Scena();
        panelCentr.add(scena);
        getContentPane().add(panelCentr,BorderLayout.CENTER);
        getContentPane().add(ButtonPlay,BorderLayout.AFTER_LAST_LINE);
        setVisible(true);

    }
    private class PlayActionListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            scena.newGame();
        }
    }
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
    }
}
