package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Scena extends Canvas {
  private static   int pole[][];
  private static   Figura figura;
  private static   Timer timer;
  private static  ScenaKeyListner scenaKeyListner;
     Scena(){
     super();
     setBounds(0,0,100,300);
     pole = new int[10][30];
     }
    @Override
    public void paint(Graphics g) {
        setIgnoreRepaint(true);
        createBufferStrategy(2);
        BufferStrategy bufferStrategy = getBufferStrategy();
         g =  bufferStrategy.getDrawGraphics();
         for (int y = 0;y<=29;y++ )
             for (int x = 0; x<=9;x++) {
                 if (pole[x][y]==0) g.setColor(Color.white);
                 if (pole[x][y]==1) g.setColor(Color.red);
                 g.fillRect(x * 10, y * 10, x * 10 + 20, y * 10 + 20);
                 if (pole[x][y]==1) g.setColor(Color.BLACK);
                 g.drawRect(x * 10, y * 10, x * 10 + 20, y * 10 + 20);
                 }
                 bufferStrategy.show();
                g.dispose();
     }

    private void MotionFigure(){
         Cube cube[]=figura.getColectionCube();
         for (Cube cub : cube){
             try {
                 pole[cub.getX()][cub.getY()] = 1;
             }catch (Exception e){
                 System.out.println(String.valueOf(cub.getX())+" "+String.valueOf(cub.getY()));
             }
         }
         repaint();
     }
    private void iteration(){
    RowAssembled();
    figura = new Figura(2);
    MotionFigure();
    timer = new Timer(1500,new MoveBottomListner());
    timer.start();
    }

    private void RowAssembled() {
         int i = 0;
         for(int y = 0;y<=29;y++){
             boolean RowAssembled = true;
             for(int x = 0; x<=9; x++){
                 if (pole[x][y]==0) RowAssembled = false;
             }
             if(RowAssembled) ClearAndMove(y);
        }
    }

    private void ClearAndMove(int NumberLine) {

        for (;NumberLine>0;NumberLine--){
            for(int x = 0 ;x<=9;x++){
             pole[x][NumberLine]=pole[x][NumberLine-1];
            }
        }
     }

    public void newGame() {
         if (timer!=null) timer.stop();
         if (scenaKeyListner!=null) removeKeyListener(scenaKeyListner);
         for (int y = 0;y<=29;y++ )
            for (int x = 0; x<=9;x++) pole[x][y]=0;
            repaint();
            iteration();
            scenaKeyListner = new ScenaKeyListner();
            this.addKeyListener(scenaKeyListner);
            requestFocus();
        }
    private  class ScenaKeyListner implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
;
        }
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            if (e.getKeyCode()==37) MoveLeft();
            if (e.getKeyCode()==38) Turn();
            if (e.getKeyCode()==39) MoveRight();
            if (e.getKeyCode()==40) Skip();
        }
        @Override
        public void keyReleased(KeyEvent e) {
;
        }
    }
    private void Turn() {
        Cube[] cube = figura.getColectionCube();
        boolean possibleToTurn = true;
        if (figura.getFigureType() == 0) {
            if (cube[0].getY() == 0) possibleToTurn = false;
            else {
                if (pole[cube[0].getX() + 1][cube[0].getY() - 1] != 0) possibleToTurn = false;
                if (pole[cube[2].getX() - 1][cube[0].getY() + 1] != 0) possibleToTurn = false;
            }
        }
        if (figura.getFigureType() == 2) {
            boolean PossibleTurn = true;
            for (Cube cub: cube) {
                try {
                    if ((figura.getTurn() % 2 == 0) && cub.isRightBorder() && (pole[cub.getX() + 1][cub.getY()] == 1))
                        possibleToTurn = false;
                    if ((figura.getTurn() % 2 != 0) && cub.isLeftBorder() && (pole[cub.getX() - 1][cub.getY()] == 1))
                       possibleToTurn = false;
                }catch (Exception e ){
                }
            }
            if ((figura.getTurn() == 2) && (cube[1].getX() == 9)) {
                for (Cube cub : cube)
                    if (pole[cub.getX() - 1][cub.getY()] == 1 && (cub.isLeftBorder())) PossibleTurn = false;
                if (PossibleTurn) {
                    for (Cube cub : cube) {
                        pole[cub.getX()][cub.getY()] = 0;
                    }
                    figura.MoveLeft();
                    cube = figura.getColectionCube();
                }
            }
            if ((figura.getTurn() == 0) && (cube[0].getX() == 0)) {
                for (Cube cub : cube)
                    if (pole[cub.getX() + 1][cub.getY()] == 1 && (cub.isRightBorder())) PossibleTurn = false;
                if (PossibleTurn) {
                    for (Cube cub : cube) {
                        pole[cub.getX()][cub.getY()] = 0;
                    }
                    figura.MoveRight();
                    cube = figura.getColectionCube();
                }
            }
        }
            if (possibleToTurn) {
                for (Cube cub : cube) {
                    pole[cub.getX()][cub.getY()] = 0;
                }
                figura.Turn();
                MotionFigure();
            }
        }
    private void MoveRight() {
        Cube[] cube =figura.getColectionCube();
        boolean possibleToMoveRight = true;
        for (Cube cub : cube){
            if(cub.isRightBorder()) if ((cub.getX()==9)||pole[cub.getX()+1][cub.getY()]==1) possibleToMoveRight=false;
        }
        if (possibleToMoveRight) {
            for (Cube cub : cube) {
                pole[cub.getX()][cub.getY()] = 0;
            }
            figura.MoveRight();
            MotionFigure();
        }
    }
    private void MoveLeft() {
        Cube[] cube =figura.getColectionCube();
        boolean possibleToMoveLeft = true;
        for (Cube cub : cube){
            if(cub.isLeftBorder()) if ((cub.getX()==0)||pole[cub.getX()-1][cub.getY()]==1) possibleToMoveLeft=false;
        }
        if (possibleToMoveLeft) {
            for (Cube cub : cube) {
                pole[cub.getX()][cub.getY()] = 0;
            }
            figura.MoveLeft();
            MotionFigure();
        }
    }
    private void Skip()  {
         timer.stop();
         boolean possibleToMoveDown = true;
         do {
             Cube[] cube = figura.getColectionCube();
             for (Cube cub : cube) {
                 if (cub.isFoundation())
                     if ((cub.getY() == 29) || (pole[cub.getX()][cub.getY() + 1] == 1)) possibleToMoveDown = false;
             }
             if (possibleToMoveDown){
                 for (Cube cub : cube) {
                     pole[cub.getX()][cub.getY()] = 0;
                 }
                 figura.MoveBottom();
                 MotionFigure();

             }
         }while (possibleToMoveDown);
         iteration();
    }
    private class MoveBottomListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Cube[] cube =figura.getColectionCube();
            boolean possibleToMoveDown = true;
            for (Cube cub : cube){
                if(cub.isFoundation())if ((cub.getY()==29)||(pole[cub.getX()][cub.getY()+1]==1)) possibleToMoveDown=false;
            }
            if (possibleToMoveDown) {
                for (Cube cub : cube) {
                    pole[cub.getX()][cub.getY()] = 0;
                }
                figura.MoveBottom();
                MotionFigure();
            }else {
                    MotionFigure();
                    if(pole[3][0]!=1) {
                        timer.stop();
                        iteration();
                    }else {
                        figura=null;
                        timer.stop();
                        removeKeyListener(scenaKeyListner);
                    }

            }
        }
    }
}
