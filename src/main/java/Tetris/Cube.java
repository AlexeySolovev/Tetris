package Tetris;
public class Cube {
private int x,y;
private  boolean LeftBorder, RightBorder,Foundation;
    Cube(int y,int x,boolean LeftBorder,boolean Foundation,boolean RightBorder){
    this.x=x;
    this.y=y;
    this.Foundation=Foundation;
    this.LeftBorder=LeftBorder;
    this.RightBorder = RightBorder;
    }
    public void Turn(){

    }
    public void Turn(int y,int x,boolean LeftBorder,boolean Foundation,boolean RightBorder){
        this.x=x;
        this.y=y;
        this.Foundation=Foundation;
        this.LeftBorder=LeftBorder;
        this.RightBorder =RightBorder;
    }

    public boolean isLeftBorder(){
    return LeftBorder;
    }

    public boolean isFoundation() {
        return Foundation;
    }

    public boolean isRightBorder() {
        return RightBorder;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void MoveBottom() {
    setY(y+1);
    }
}
