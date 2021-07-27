package controller;

public class StartState implements State {
	   
	public boolean doAction(Context context) {
	      
		  System.out.println("Player is in start state");
	      context.setState(this);
	      return false;
	   }

	   public String toString(){
	      return "Start State";
	   }
	}
