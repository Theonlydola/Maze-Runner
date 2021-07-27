package controller;

public class StopState implements State {

	   public boolean doAction(Context context) {
	      System.out.println("Player is in stop state");
	      context.setState(this);
		return false;	
	   }

	   public String toString(){
	      return "Stop State";
	   }
	}