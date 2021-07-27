package model;
import javafx.scene.image.*;

public class Bomb extends AbstractCell implements InterfaceCell{
    
	ImageView Bomb; 
    int magnitude;
    
    public Bomb(int x, int y, char type, int magnitude) {
        super(x,y,type);
        this.magnitude = magnitude;
    }
    
    public int getMagnitude()
    {
    	return magnitude;
    }
    
    
    public void updateMagnitude(int m)
    {
    	magnitude = magnitude - m;
    }
  
    @Override
    public ImageView drawBackground() {

        switch (type) {
            
        case 'b' : 
        	Bomb = Factory.Create("Bomb");
            Bomb.setX(x * SIZE);
            Bomb.setY( y * SIZE + SIZE / 2 - 10);
            Bomb.setFitWidth(SIZE);
            Bomb.setFitHeight(SIZE);
            Bomb.setSmooth(true);
            return Bomb;
        case 'd' :
        	Bomb = Factory.Create("Dynamite");
            Bomb.setX(x * SIZE);
            Bomb.setY( y * SIZE + SIZE / 2 - 10);
            Bomb.setFitWidth(SIZE);
            Bomb.setFitHeight(SIZE);
            Bomb.setSmooth(true);
            return Bomb;
        case 'x' :  
        	Bomb = Factory.Create("Ashes");
            Bomb.setX(x * SIZE);
            Bomb.setY( y * SIZE + SIZE / 2 - 10);
            Bomb.setFitWidth(SIZE);
            Bomb.setFitHeight(SIZE);
            Bomb.setSmooth(true);
            return Bomb;
        default :
            break;
        }
		return null;
    }
}
