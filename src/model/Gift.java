package model;
import javafx.scene.image.*;

public class Gift extends AbstractCell {
	ImageView Gift; 

    public Gift(int x, int y, char type) {
        super(x,y,type);
    }
    

    @Override
    public ImageView drawBackground() {

        switch (type) {
        
        case 'g' : 
        	Gift = Factory.Create("Gift");
            Gift.setX(x * SIZE);
            Gift.setY( y * SIZE + SIZE / 2 - 10);
            Gift.setFitWidth(SIZE);
            Gift.setFitHeight(SIZE);
            return Gift;
        case 'h' :
        	Gift = Factory.Create("healthGift");
            Gift.setX(x * SIZE);
            Gift.setY( y * SIZE + SIZE / 2 - 10);
            Gift.setFitWidth(SIZE);
            Gift.setFitHeight(SIZE);
            return Gift;
        case 'a' :
        	Gift = Factory.Create("ammoGift");
            Gift.setX(x * SIZE);
            Gift.setY( y * SIZE + SIZE / 2 - 10);
            Gift.setFitWidth(SIZE);
            Gift.setFitHeight(SIZE);
            return Gift;
        case 'i' :
        	Gift = Factory.Create("armorGift");
            Gift.setX(x * SIZE);
            Gift.setY( y * SIZE + SIZE / 2 - 10);
            Gift.setFitWidth(SIZE);
            Gift.setFitHeight(SIZE);
            return Gift;   
            
        case 'x' :  
        	Gift = Factory.Create("Ground");
            Gift.setX(x * SIZE);
            Gift.setY( y * SIZE + SIZE / 2 - 10);
            Gift.setFitWidth(SIZE);
            Gift.setFitHeight(SIZE);
            return Gift;    
            
        default :
        	return null;
        }
		
    }


	@Override
	public int getMagnitude() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void updateMagnitude(int m) {
		// TODO Auto-generated method stub
		
	}
}
