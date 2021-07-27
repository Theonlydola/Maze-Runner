package model;

import javafx.scene.image.ImageView;

public interface InterfaceCell {
	 public void setType(char type);
	 public char getType();
	 public ImageView drawBackground();
	 public int getMagnitude();
	 public void updateMagnitude(int m);
}
