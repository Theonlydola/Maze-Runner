package controller;

public class PauseState implements State {
    
	   public boolean doAction(Context context) {
	      System.out.println("Player is in Pause state");
	      context.setState(this);
		return true;	
	   }

	   public String toString(){
	      return "Stop State";
	   }
	}