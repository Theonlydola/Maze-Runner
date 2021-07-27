package model;
import javafx.scene.image.*;
public class Factory {
	 
	static Image WALL = new Image("Wall.jpg");
 	static Image TREE = new Image("tree.png");
 	static Image GROUND = new Image("ground3.jpg");
 	static Image COIN = new Image("coin.png");
 	static Image BOMB = new Image("bomb.png"); 
 	static Image DYNAMITE = new Image("dynamite.png"); 
 	static Image ASHES = new Image("Ashes.png"); 
 	static Image GIFT = new Image("gift.png"); 
 	static Image HEALTHGIFT = new Image("healthGift.png"); 
 	static Image AMMOGIFT = new Image("ammo.png"); 
 	static Image ARMORGIFT = new Image("shield.png");
 	static Image KILLER = new Image("killer1.png");
 	static Image THEIF = new Image("theif1.png");
 	static Image SAKHIF = new Image("sakhif1.png");
 	static Image FINISH = new Image("finish.jpg"); 
 	
	public static ImageView Create(String type)
	{
		switch (type)
		{
		case "Wall" : return new ImageView (WALL);
		case "Tree" : return new ImageView (TREE) ;
		case "Ground" : return new ImageView(GROUND);
		case "Coin" : return new ImageView(COIN);
		case "Finish" : return new ImageView(FINISH);
		case "Bomb" : return new ImageView(BOMB);
		case "Dynamite" : return new ImageView(DYNAMITE);
		case "Ashes" : return new ImageView(ASHES);
		case "Gift" : return new ImageView(GIFT);
		case "healthGift" : return new ImageView(HEALTHGIFT);
		case "ammoGift" : return new ImageView(AMMOGIFT);
		case "armorGift" : return new ImageView(ARMORGIFT);
		case "killer" : return new ImageView(KILLER);
		case "theif" : return new ImageView(THEIF);
		case "sakhif" : return new ImageView(SAKHIF);
		default : System.out.print("No component Generated");
		}
		return null;
		
	}
}
