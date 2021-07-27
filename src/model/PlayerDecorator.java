package model;

import javafx.scene.image.ImageView;

public abstract class PlayerDecorator {
	   protected Player decoratedPlayer;

	   public PlayerDecorator(Player decoratedPlayer){
	      this.decoratedPlayer = decoratedPlayer;
	   }

	   public ImageView [] drawPlayer (){	   
	      return decoratedPlayer.drawPlayer();
	   }	
	}