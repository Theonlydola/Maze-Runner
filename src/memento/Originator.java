package memento;

public class Originator {
	   private double [] state;

	   public void setState(double [] state){
	      this.state = state;
	   }

	   public double [] getState(){
	      return state;
	   }

	   public Memento saveStateToMemento(){
	      return new Memento(state);
	   }

	   public void getStateFromMemento(Memento memento){
	      state  = memento.getState();
	   }
}