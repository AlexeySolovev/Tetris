package Tetris;

public class Figura {
    private static Cube cube[];

    public int getTurn() {
        return turn;
    }

    private int turn;

    public int getFigureType() {
        return figureType;
    }

    private int figureType;
    Figura(int figureType){
        turn=0;
        this.figureType=figureType;
    switch (figureType){
        case 0:{
            cube = new Cube[3];
            cube[0]= new Cube(0,3,true,true,false);
            cube[1]= new Cube(0,4,false,true,false);
            cube[2]= new Cube(0,5,false,true,true);
        }
        break;
        case 1:{
            cube=new Cube[4];
            cube[0]= new Cube(0,3,true,false,false);
            cube[1]= new Cube(0,4,false,false,true);
            cube[2]= new Cube(1,3,true,true,false);
            cube[3]= new Cube(1,4,false,true,true);
        }
        break;
        case 2:{
            cube = new Cube[4];
            cube[0]= new Cube(0,3,true,false,true);
            cube[1]= new Cube(1,3,true,false,true);
            cube[2]= new Cube(2,3,true,true,false);
            cube[3]= new Cube(2,4,false,true,true);
        }
    }
   }
   public Cube[] getColectionCube(){
    return cube;
   }
   public void MoveBottom(){
       for (Cube cube:cube) {
        cube.MoveBottom();
       }
   }
   public void Turn(){
        turn++;
        if (turn==4)turn=0;
       switch (figureType){
           case 0: {
               if (turn == 1 || turn == 3) {
                   cube[0].Turn(cube[0].getY() - 1, cube[0].getX() + 1, true, false, true);
                   cube[1].Turn(cube[1].getY(), cube[1].getX(), true, false, true);
                   cube[2].Turn(cube[2].getY() + 1, cube[2].getX() - 1, true, true, true);
               } else {
                   cube[0].Turn(cube[0].getY() + 1, cube[0].getX() - 1, true, true, false);
                   cube[1].Turn(cube[1].getY(), cube[1].getX(), false, true, false);
                   cube[2].Turn(cube[2].getY() - 1, cube[2].getX() + 1, false, true, true);
               }
           }
               break;
               case 2: {
                   if(turn==1){
                       cube[0].Turn(cube[0].getY() + 2, cube[0].getX() - 1, true, true, false);
                       cube[1].Turn(cube[1].getY(), cube[1].getX()+1, true, false, true);
                       cube[2].Turn(cube[2].getY() , cube[2].getX() , false, true, false);
                       cube[3].Turn(cube[3].getY() , cube[3].getX() , false, true, true);
                   }
                   if(turn==2){
                       cube[0].Turn(cube[0].getY() - 2, cube[0].getX() , true, true, false);
                       cube[1].Turn(cube[1].getY()-1, cube[1].getX()-1, false, false, true);
                       cube[2].Turn(cube[2].getY() , cube[2].getX() , true, true, false);
                       cube[3].Turn(cube[3].getY()-1 , cube[3].getX()-1 , true, false, true);
                   }
                   if(turn==3){
                       cube[0].Turn(cube[0].getY() + 1, cube[0].getX() , true, true, true);//*
                       cube[1].Turn(cube[1].getY(), cube[1].getX(), false, true, false);///*/
                       cube[2].Turn(cube[2].getY()-2 , cube[2].getX()+1 , false, true, true);
                       cube[3].Turn(cube[3].getY()-1 , cube[3].getX()-1 , true, false, false);
                   }
                   if(turn==0){
                       cube[0].Turn(cube[0].getY()-1 , cube[0].getX()+1 , true, false, true);
                       cube[1].Turn(cube[1].getY()+1, cube[1].getX(), true, false, true);
                       cube[2].Turn(cube[2].getY()+2 , cube[2].getX()-1 , true, true, false);
                       cube[3].Turn(cube[3].getY()+2 , cube[3].getX()+2, false, true, true);
                   }
               }
           break;
       }
   }

    public void MoveLeft() {
        cube[0].setX(cube[0].getX()-1);
        cube[1].setX(cube[1].getX()-1);
        cube[2].setX(cube[2].getX()-1);
        if(figureType!=0)cube[3].setX(cube[3].getX()-1);
    }
    public void MoveRight() {
        cube[0].setX(cube[0].getX()+1);
        cube[1].setX(cube[1].getX()+1);
        cube[2].setX(cube[2].getX()+1);
        if(figureType!=0)cube[3].setX(cube[3].getX()+1);
    }
}
