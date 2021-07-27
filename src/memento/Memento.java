package memento;

public class Memento {
	   private double [] state;

	   public Memento(double [] state){
	      this.state = state;
	   }

	   public double [] getState(){
	      return state;
	   }	
	   
}