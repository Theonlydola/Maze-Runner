package model;
import javafx.scene.image.*;

public class Cell extends AbstractCell{
    
	ImageView cell; 

    public Cell(int x, int y, char type) {
  		super(x, y, type);
  	}
    
    @Override
    public ImageView drawBackground() {

        switch (type) {
        case 'w' :   
            cell = Factory.Create("Wall");
            cell.setX(x * SIZE);
            cell.setY( y * SIZE + SIZE / 2 - 10);
            cell.setFitWidth(SIZE);
            cell.setFitHeight(SIZE);
            return cell;

        case 't' :    
        	cell = Factory.Create("Tree");
            cell.setX(x * SIZE);
            cell.setY( y *SIZE + SIZE / 2 - 10);
            cell.setFitWidth(SIZE);
            cell.setFitHeight(SIZE);
            return cell;
            
        case 'x' :  
        	cell = Factory.Create("Ground");
            cell.setX(x * SIZE);
            cell.setY( y * SIZE + SIZE / 2 - 10);
            cell.setFitWidth(SIZE);
            cell.setFitHeight(SIZE);
            return cell;

        case 'f' :
        	cell = Factory.Create("Finish");
            cell.setX(x * SIZE);
            cell.setY( y * SIZE + SIZE / 2 - 10);
            cell.setFitWidth(SIZE);
            cell.setFitHeight(SIZE);
            return cell;
            
        default :
        	return cell;
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
