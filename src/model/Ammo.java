package model;

public class Ammo extends Observer {
	
	      public int ammo;
		   public Ammo(Subject subject){
		      this.subject = subject;
		      this.subject.attach(this);
		   }
		   
		   @Override
		   public void update() {
		      ammo = subject.getState()[3];
		   }
		
}
