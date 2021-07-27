package model;

public class Health extends Observer {
	      public double health;

		   public Health(Subject subject){
		      this.subject = subject;
		      this.subject.attach(this);
		   }
		   
		   @Override
		   public void update() {
			   
		       if (subject.getState()[2] >= 0 )
			   health = subject.getState()[0] * 0.2 ;
		       
		       if (subject.getState()[2] > 3 )
				   health = subject.getState()[0] * 0.4 ;
		       
		       if (subject.getState()[2] > 6 )
				   health = subject.getState()[0] * 0.6 ;
		       
		     
		   }
		
}
