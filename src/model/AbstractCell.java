package model;

import javafx.scene.image.ImageView;

public abstract class AbstractCell implements InterfaceCell{
	
	final int  SIZE = 20;
    protected char type;
    protected int  x, y;

    
	public AbstractCell(int x, int y, char type) {
        this.type = type;
        this.x    = x;
        this.y    = y;
    }

    public void setType(char type) {
   	 this.type = type;
   }
    
    public char getType() {
    	return type;
    }
    
    public ImageView drawBackground() {
		return null;
    }
    

	
}
