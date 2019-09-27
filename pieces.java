//Jessica Feng P3
public class pieces {
    int x;
    int y;
    boolean king;
    String letter;
    public pieces (){
        x = 0;
        y = 0;
        king = false;
        letter = "w";
    }
    public pieces (int a, int b, boolean c, String d){
        x = a;
        y = b;
        king = c;
        letter = d;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int a){
        x = a;
    }
    public void setY(int b){
        y = b;
    }
    public boolean getKing(){
        return king;
    }
    public void setKing(boolean c){
        king = c;
    }
    public String getLetter(){
        return letter;
    }
    public void setLetter(String d){
        letter = d;
    }

}
