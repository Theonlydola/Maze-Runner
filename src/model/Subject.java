package model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	
   private List<Observer> observers = new ArrayList<Observer>();
   private int [] state =  new int [4];

   public int [] getState() {
      return state;
   }

   public void setState(int health, int score , int time, int ammo) {
      state[0] = health;
      state[1] = score;
      state[2] = time;
      state[3] = ammo;
      notifyAllObservers();
   }

   public void attach(Observer observer){
      observers.add(observer);		
   }

   public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   } 	
}
