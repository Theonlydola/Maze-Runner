package model;

//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ArmorPlayerDecorator extends PlayerDecorator {
       int SIZE = 20;
	   public ArmorPlayerDecorator(Player decoratedPlayer) {
	      super(decoratedPlayer);		
	   }

	   @Override
	   public ImageView [] drawPlayer (){
		      return drawArmorPlayer (decoratedPlayer.drawPlayer() ) ;
		   }	
      
	   private ImageView [] drawArmorPlayer (ImageView [] decoratedPlayerView ) {
		   System.out.println("ARMOR NAWW GRRRR !!!!");
		   decoratedPlayerView[0]=  Factory.Create("armorGift");
		   decoratedPlayerView[0].setX(decoratedPlayer.PlayerCol*SIZE);
		   decoratedPlayerView[0].setY(decoratedPlayer.PlayerRow*SIZE);
		   decoratedPlayerView[0].setFitWidth(SIZE);
		   decoratedPlayerView[0].setFitHeight(SIZE);   
		   //decoratedPlayer[1]=  Factory.Create("armorGift");
		return decoratedPlayerView;
	   }
	  
	}
