package model;



public class Score extends Observer {
     public int score;
	 public Score(Subject subject){
	      this.subject = subject;
	      this.subject.attach(this);
	   }
	   
	   @Override
	   public void update() {   
	      score = subject.getState()[1] * ( 2 / (subject.getState()[2] + 1) ) + 1 ;
	   } 
}
