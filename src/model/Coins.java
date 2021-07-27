package model;
import javafx.scene.image.*;

public class Coins extends AbstractCell {
	ImageView Coin; 

    public Coins(int x, int y, char type) {
        super(x,y,type);
    }

    @Override
    public ImageView drawBackground() {

        switch (type) {
        case 'c' :
        	Coin = Factory.Create("Coin");
        	Coin.setX(x * SIZE + 5);
        	Coin.setY(( y * SIZE + SIZE / 2 - 10 )+5);
        	Coin.setFitWidth(8);
        	Coin.setFitHeight(8);
            return Coin;
        case 'x' :  
        	Coin = Factory.Create("Ground");
            Coin.setX(x * SIZE);
            Coin.setY( y * SIZE + SIZE / 2 - 10);
            Coin.setFitWidth(SIZE);
            Coin.setFitHeight(SIZE);
            return Coin;
        default : return null;
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
