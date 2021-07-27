package controller;

import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Maze;
import model.Monster;
import model.Player;
import model.ArmorPlayerDecorator;


import model.Gift;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;


import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.*;
  

public class Controller implements InterfaceController {
	public boolean initialized;
	public boolean paused;
	private static  Controller instance=null;;
	public static  Controller getInstance()
	{
		if(instance==null) return new Controller();
		else
		return instance;
		
	}
	    @FXML
	    private Pane pane,mainpane,btnpane,losepane,winpane;
	    @FXML
	    private Label TIME,SCORE,AMMOLEFT;
	    @FXML
	    private ProgressBar HEALTH;
	    @FXML
	    private Button startbtn;
	    @FXML
		private Stage stage;
	    
	    
	    private int      PlayerInitialColumn = 15;
	    private int      PlayerInitialRow    = 29;
	    Maze M =  Maze.getInstance();
	    Player p = Player.getInstance(PlayerInitialRow, PlayerInitialColumn, M, 1,6,0);
	    ArmorPlayerDecorator ap;
	    
	    Monster km = new Monster (7, 7, M, 'k',3);
	    Monster tm = new Monster (15, 15, M, 't',3);
	    Monster sm = new Monster (25, 25, M, 's',3);
	    
	    String mapFile = "Maze2.txt";
	    ImageView player[] = new ImageView [2];
	    ImageView[] s  = new ImageView [2];
	    
	    
	    ImageView killer = new ImageView();
	    ImageView theif = new ImageView();
	    ImageView sakhif = new ImageView();
	    
	   
        int minTime = 0;
        int secTime=  0;
        boolean ArmorON = false;
        int ArmorTime = 0;
        Timer timer = new Timer();
        
        Context context =  new Context ();
        StartState startstate = new StartState();
        PauseState pausestate = new PauseState();
        StopState stopstate = new StopState();
        
        
	  public void init()
	   {    		 
		  initialized = startstate.doAction(context);
		  if (!initialized)
		  {
			M.createCellArray(mapFile);
			M.paintArray();
			M.addextras();
			p.loadsprite();
			startTimer();  
		    pane.setMinSize(M.tileWidth*21, M.tileHeight*21);
		    
		    for (int i=0; i<M.drawncells.size();i++)		   
			  {
			   pane.getChildren().add(M.drawncells.get(i));
			   if (M.addedextras.get(i) != null)
			   pane.getChildren().add(M.addedextras.get(i));
			  }
		    
		      player = p.drawPlayer();    
		      pane.getChildren().add(player[1]);
		      pane.getChildren().add(player[0]);
		      
		      pane.setMinSize(M.tileWidth*21,  M.tileHeight*21);
		      
		      Activatebutton();
			  AMMOLEFT.setText(Integer.toString(p.ammoLeft));
			  
			  killer = km.drawMonster();
			  pane.getChildren().add(killer);
			  
			  theif = tm.drawMonster();
			  pane.getChildren().add(theif);
			  
			  sakhif = sm.drawMonster();
			  pane.getChildren().add(sakhif);
			  
			  MonsterTimer();
		      initialized = true;  
              
	   }
	}
	  
	  public void shoot (int x, int y) throws InterruptedException {
		  if (p.ammoLeft>0)
		  {
	     
			 while (p.isCellNavigable(p.bulletRow + x,p.bulletCol + y) 
					 && M.extras[p.bulletRow + x][p.bulletCol + y].getType() != 'b' 
					 && M.extras[p.bulletRow + x][p.bulletCol + y].getType() != 'd'  
					 && !(p.bulletRow+x == tm.getCol() && p.bulletCol+y == tm.getRow())
					 && !(p.bulletRow+x == sm.getCol() && p.bulletCol+y == sm.getRow())
					 && !(p.bulletRow+x == km.getCol() && p.bulletCol+y == km.getRow())  ) {
	         p.bulletRow = p.bulletRow + x;
	         p.bulletCol = p.bulletCol + y; 
	         redrawpane();
			 }
			 
			 p.bulletRow = p.bulletRow + x;
	         p.bulletCol = p.bulletCol + y;
	         redrawpane();
	         
	        System.out.println("pew pew pew");
	        System.out.println("ROW " + p.bulletRow + ", COL " + p.bulletCol);
	        
	        
	        checkbulletcollision(p.bulletRow,p.bulletCol);
	        
	        System.out.println("TAKH");
	        
	        p.bulletRow = p.PlayerRow;
	        p.bulletCol = p.PlayerCol; 
	        
	        p.updateAmmo(-1);
	        
	        Activatebutton();
	        AMMOLEFT.setText(Integer.toString(p.ammoLeft));
	    }
	 }
	  
	  public void Activatebutton() {
		  mainpane.setOnKeyReleased(
	              new EventHandler<KeyEvent>()
	              {
	            	  public void handle(KeyEvent e)
	                  {
	            		  switch (e.getCode()) {
	                	    case DOWN:
	                	    	
	                	        p.setDirection('d');
	                	        p.movePlayer(1,0);
	                	        checkplayercollision(p.PlayerRow,p.PlayerCol );
	                	        redrawpane();
	                	        break;
	                	        		                	       
	                	    case UP:
	                	    	System.out.println("psst");
	                	    	 p.setDirection('u');
	                	    	 p.movePlayer(-1, 0);
	                	    	 checkplayercollision(p.PlayerRow,p.PlayerCol );
	                	    	 redrawpane();
	                	    	 
	                	        break;
	                	    case LEFT:
	                	    	 p.setDirection('l');
	                	    	 p.movePlayer(0, -1);
	                	    	 checkplayercollision(p.PlayerRow,p.PlayerCol );
	                	    	  redrawpane();
	                	    	 
	                	        break;
	                	    case RIGHT:
	                	    	 p.setDirection('r');
	                	    	 p.movePlayer(0, 1);
	                	    	 checkplayercollision(p.PlayerRow,p.PlayerCol );
	                	    	 redrawpane();
	                	        break;
	                	    case P:
							try {
								pause();
							} catch (InterruptedException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
	                	    	break;
	                	    	
	                	    case S:
	                	       try {
	                	    	pause();
								Save();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                	    case X:
	                
	                	    	switch (p.getDirection()) {
	                	    case 'u' : try {
										shoot(-1,0);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} break;
	                	    case 'd' : try {
										shoot(1,0);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} break;
	                	    case 'l' : try {
										shoot(0, -1);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} break;
	                	    case 'r' : try {
										shoot(0, 1);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} break;
	                	    default : break;
	                	    }  	
	                	   
	                
						default:
							break;
	                  }
	                }
	              });
	  }
	  
	  public void checkbulletcollision(int column, int row)
	  {
		  if (p.bulletCol == sm.getRow() && p.bulletRow == sm.getCol())  
			  {
			  sm.isded--;
			  if (sm.isded == 0) {p.updateScore(500,0);  SCORE.setText(Integer.toString(p.getScore()));}
			  }
		  if (p.bulletCol == tm.getRow() && p.bulletRow == tm.getCol())  {
			  tm.isded--;
			  if (tm.isded == 0) {p.updateScore(500,0); SCORE.setText(Integer.toString(p.getScore()));}
		  }
		  if (p.bulletCol == km.getRow() && p.bulletRow == km.getCol())  {
			  km.isded--;
			  if (km.isded == 0) {p.updateScore(500,0);  SCORE.setText(Integer.toString(p.getScore()));}
		  }
		  
		  switch (M.cells[column][row].getType())
	        {
	        	case 't': M.cells[column][row].setType('x');            
	        	          pane.getChildren().add(M.cells[column][row].drawBackground()); 
	        	          break;
	        	default : break;
	        } 
		  
		  switch (M.extras[column][row].getType())
	        {
	        	        	          
	        	case 'b': M.extras[column][row].setType('x');
	        	          pane.getChildren().add(M.cells[column][row].drawBackground());
	                      pane.getChildren().add(M.extras[column][row].drawBackground()); 
	                      break; 
	                      
	        	case 'd':  M.extras[column][row].updateMagnitude(1);
	        	
	        	          if (M.extras[column][row].getMagnitude() == 0)
	        	          {
	        	          M.extras[column][row].setType('x');
  	                      pane.getChildren().add(M.cells[column][row].drawBackground());
                          pane.getChildren().add(M.extras[column][row].drawBackground()); 
	        	          }
	        	          
                          break;           
	                      
	                      
	        	default : break;
	        }	
	       
	  }
	  
	  public void pause() throws InterruptedException
	  {
		  
		 Button unpause = new Button(); 
		 unpause.setLayoutX(15*20);
		 unpause.setLayoutY(15*20);
		 unpause.setMinWidth(50);
		 unpause.setMinHeight(25);
		 unpause.setText("Pause");
		 unpause.setOnAction(e ->{
			  mainpane.setOnKeyReleased(
		              new EventHandler<KeyEvent>()
		              {
		            	  public void handle(KeyEvent x)
		                  {
		            		  //System.out.println("ACTIVATED");
		            		  switch (x.getCode())
		            		  {
		            		  case P: 
		            			  unpause(unpause);
								  System.out.println("ACTIVATED");
		            			  //System.out.println("p");
		            			  break;
		            		  default : break; 
		            		  }
		                  }
		              }); 
		  });
		  
		  pane.getChildren().add(unpause);
		  unpause.fire();
		  paused  = pausestate.doAction(context);
		  System.out.println(paused);
		  
		  if (paused)
		  {
			  startbtn.setVisible(false);
			  startbtn.setDisable(true);
			  //onPause();
	      }
	  }
	  
	  public void unpause(Button unpaused)
	  {
		  if (unpaused.isVisible())
			{
				unpaused.setVisible(false);
				startbtn.setVisible(true);
				startbtn.setDisable(false);
				Activatebutton();
				paused = false;
			 }	
			else 
				{
				unpaused.setVisible(true);
				startbtn.setVisible(false);
				
				}
	  }
	  
	  public void checkplayercollision(int column, int row) 
	  {
		  
		  if (M.cells[column][row].getType() == 'f')  {paused = true; winpane.setVisible(true);}
		  
		  switch (M.extras[column][row].getType())
	        {
	        	case 'c': M.extras[column][row].setType('x');            
	        	          pane.getChildren().add(M.extras[column][row].drawBackground()); 
	        	          p.updateScore(1, getminTime());
	        	          SCORE.setText(Integer.toString(p.getScore()));
	        	          //System.out.println(p.getScore());
	        	          int i = row , j = column; 
	        	          
	        	          while (p.sendGift()) 
	        	           {
	        	          
	        	        	if  (M.extras [i] [j].getType() == 'n')
	        	           {
	        	        		j++;
	        	        		if (j>= M.tileWidth) {j=0;  i++;}
	        	        		if (i>= M.tileHeight) {j=0; i=0;}
	        	           }
	        	        	else
	        	        		{
	        	        		    System.out.println(i);
	        	        		    System.out.println(j);
	        	        		    M.extras[i][j] = M.createGift(j, i);
		        	        	    pane.getChildren().add(M.extras[i][j].drawBackground()); 
		        	        	    p.stopsendGift();
	        	        		}
	        	           }
	        
	        	          break;
	        	          
	        	case 'b': if (ArmorON == false)
	        	{
	        		      M.extras[column][row].setType('x'); 
	        	          pane.getChildren().add(M.cells[column][row].drawBackground());
  	                      pane.getChildren().add(M.extras[column][row].drawBackground()); 
  	                      p.updateHealth(-1, getminTime());
	        	          HEALTH.setProgress(p.gethealth());
	        	          if (p.gethealth() == 0) {paused = true; losepane.setVisible(true);}
	        	          System.out.println(p.getScore());
	        	}
	        	else {
	        		  M.extras[column][row].setType('x'); 
      	              pane.getChildren().add(M.cells[column][row].drawBackground());
                      pane.getChildren().add(M.extras[column][row].drawBackground()); 
	        	}
  	                      break;
	        	case 'd': if (ArmorON == false)
	        	{
	        		      M.extras[column][row].setType('x'); 
  	                      pane.getChildren().add(M.cells[column][row].drawBackground());
                          pane.getChildren().add(M.extras[column][row].drawBackground()); 
                          p.updateHealth(-2, getminTime());
  	                      HEALTH.setProgress(p.gethealth());
  	                      if (p.gethealth() == 0) {paused = true; losepane.setVisible(true);}
  	                      System.out.println(p.getScore());
	        	}
	        	else {
	        		      M.extras[column][row].setType('x'); 
                          pane.getChildren().add(M.cells[column][row].drawBackground());
                          pane.getChildren().add(M.extras[column][row].drawBackground()); 
                          p.updateHealth(-1, getminTime());
                          HEALTH.setProgress(p.gethealth());
                          if (p.gethealth() == 0) {paused = true; losepane.setVisible(true);}
	        	}
                          break;
	        	case 'h': M.extras[column][row].setType('x'); 
  	                      //pane.getChildren().add(M.cells[column][row].drawBackground());
                          pane.getChildren().add(M.extras[column][row].drawBackground()); 
                          p.updateHealth(2, getminTime());
	                      HEALTH.setProgress(p.gethealth());
	                      
	                      //System.out.println(p.getScore());
                          break;   
                          
	        	case 'g': M.extras[column][row].setType('x'); 
                          //pane.getChildren().add(M.cells[column][row].drawBackground());
                          pane.getChildren().add(M.extras[column][row].drawBackground()); 
                          p.updateScore(100, 0);
	        	          SCORE.setText(Integer.toString(p.getScore()));
	        	          //System.out.println(p.getScore());
	        	           i = row; 
	        	           j = column; 
	        	          
	        	          while (p.sendGift()) 
	        	           {
	        	          
	        	        	if  (M.extras [i] [j].getType() == 'n')
	        	           {
	        	        		j++;
	        	        		if (j>= M.tileWidth) {j=0;  i++;}
	        	        		if (i>= M.tileHeight) {j=0; i=0;}
	        	           }
	        	        	else
	        	        		{
	        	        		    System.out.println(i);
	        	        		    System.out.println(j);
	        	        		    M.extras[i][j] = M.createGift(j, i);
		        	        	    pane.getChildren().add(M.extras[i][j].drawBackground()); 
		        	        	    p.stopsendGift();
	        	        		}
	        	           }
	        
	        	          break;
	        	case 'a' : M.extras[column][row].setType('x'); 
                           //pane.getChildren().add(M.cells[column][row].drawBackground());
                           pane.getChildren().add(M.extras[column][row].drawBackground()); 
                           p.updateAmmo(6);
                           AMMOLEFT.setText(Integer.toString(p.ammoLeft)); 
                           break;
                           
	        	case 'i' : M.extras[column][row].setType('x'); 
                           pane.getChildren().add(M.extras[column][row].drawBackground()); 
                           
                           Player clone = new Player (p.PlayerRow,p.PlayerCol, M, p.gethealth(),p.ammoLeft,p.getScore());
                           
                           ap = new ArmorPlayerDecorator(clone);
                           
                           p = clone;
                           
                           ArmorON = true;
                           ArmorTime = 10;
                           break;
          
	        	default : break;
	    
	        }  
		  
	  }
	  
	  private void startTimer() {
	                System.out.println("yanaaas");      
	                timer.scheduleAtFixedRate(new TimerTask() {
	                    @Override
	                    public void run() {
	                    	if (!paused)
	                    	{
	                    	secTime++;
	                    	if (secTime %60 == 0) {
	                    		minTime++;
	                    		secTime =0;
	                    	}
	                    	if (ArmorON == true)
	                    	{
	                    	  	ArmorTime--;
	                    	  	if (ArmorTime == 0) {
	                    	  		ArmorON = false;	
	                    	  		p =  new Player (p.PlayerRow,p.PlayerCol, M, p.gethealth(),p.ammoLeft,p.getScore());
	                    	  		p.loadsprite();                             
	                    	  	}
	                    	}
	      	                    	
	                    	Platform.runLater( () -> {
	                    		
	                    	TIME.setText(String.format("%02d", minTime) + ":" +String.format("%02d", secTime));
	                    	});
	                    	
	            			
	                    }
	                  }
	                }, 0, 1000);      
	                
	                
	                
	}
       
	  
	  public int getTime()
	  {
		  return minTime*60 + secTime ;
	  }
	  
	  public int getminTime()
	  {
		  return minTime;
	  }
	  private void MonsterTimer() {
                
          timer.scheduleAtFixedRate(new TimerTask() {
              @Override
              public void run() {
              	if (!paused)
              	{  	
              	Platform.runLater( () -> {
              	
              	pane.getChildren().remove(killer);
              	pane.getChildren().remove(theif);
              	pane.getChildren().remove(sakhif);
      			
              	if (km.isded > 0)
              	{
              	km.move();
              	killer = km.drawMonster();
      		    pane.getChildren().add(killer);  
                km.isRunning = true;    
                if (p.PlayerCol == km.getRow() && p.PlayerRow == km.getCol()) 
  			    {
  			     p.updateHealth(-5,0);
  			     HEALTH.setProgress(p.gethealth());
  			     if (p.gethealth() == 0) {paused = true; losepane.setVisible(true);}
  			    }
              	}
                if (tm.isded > 0)
                {
      			tm.move();
      			theif = tm.drawMonster();
      			pane.getChildren().add(theif);
      			tm.isRunning = true;  
      			if (p.PlayerCol == tm.getRow() && p.PlayerRow == tm.getCol()) 
  		        {
  			    p.updateAmmo(-6);
                AMMOLEFT.setText(Integer.toString(p.ammoLeft)); 
  		        }
                }
                if (sm.isded > 0)
                {
      			sm.move();
      			sakhif = sm.drawMonster();
      			pane.getChildren().add(sakhif);
      			sm.isRunning = true;
      			if (p.PlayerCol == sm.getRow() && p.PlayerRow == sm.getCol()) 
   			    {
   			    p.setScoreCheckPoint(1);
   			    SCORE.setText(Integer.toString(p.getScore()));
   			    } 
                }
              	});
      			
              }
            }
          }, 0, 150);      
          
}
	  
		public void redrawpane ()
		{
			if (ArmorON == true)
			{
				pane.getChildren().remove(player[1]);
				pane.getChildren().remove(player[0]);
				player = ap.drawPlayer();
				pane.getChildren().add(player[1]);
			    pane.getChildren().add(player[0]);	
			    System.out.println("YES ARMOR");
			}
			
			else {
			    pane.getChildren().remove(player[1]);
			    pane.getChildren().remove(player[0]);
			    player = p.drawPlayer();
			    pane.getChildren().add(player[1]);
		        pane.getChildren().add(player[0]);
		        System.out.println("NO ARMOR");
			}
			
		 }
		
		
		private void Save() throws IOException {
			double [] playerstate = new double[5];
			playerstate = p.getPlayerLastState();
			
			if (playerstate == null) 
				{
				Alert alert = new Alert(AlertType.ERROR, "YOU HAVE NOT PASSED ANY CHECKPOINTS YET!");
				alert.showAndWait();
				}
			
			else {
			FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(stage);
            
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(playerstate[0]+","+playerstate[1]+","+playerstate[2]+","+playerstate[3]+","+playerstate[4]+","+minTime+","+secTime);
            fileWriter.write(System.getProperty( "line.separator" ));
            
            for (int row = 0; row < M.tileHeight; row++) {
                for (int column = 0; column < M.tileWidth; column++) {   
                	fileWriter.write(M.cells[row][column].getType()); 
                }  
               fileWriter.write(System.getProperty( "line.separator" ));
            }
            
            for (int row = 0; row < M.tileHeight; row++) {
                for (int column = 0; column < M.tileWidth; column++) {   
                	fileWriter.write(M.extras[row][column].getType()); 
                }    
             fileWriter.write(System.getProperty( "line.separator" ));   
            }
            fileWriter.close();
		  }
	}
		
		public void Load() {
			FileChooser openChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            openChooser.getExtensionFilters().add(extFilter);  
            File file = openChooser.showOpenDialog(stage);
            startbtn.fire();
            
            Scanner fileReader; 
            ArrayList<String> lineList = new ArrayList<String>();
            try {
                fileReader = new Scanner(file);
                while (true) {
                    String line = null;

                    try {
                        line = fileReader.nextLine();
                    } catch (Exception eof) {

                    }

                    if (line == null) {
                        break;
                    }

                    lineList.add(line);
                    System.out.println(line);
                }


                for (int row = 0; row < M.tileHeight; row++) {
                    String line = lineList.get(row+1);
                    for (int column = 0; column < M.tileWidth; column++) {
                       
                    	char type = line.charAt(column);
                        M.cells[row][column].setType(type);
                        pane.getChildren().add(M.cells[row][column].drawBackground());
                    }  
                }
              
                for (int row = M.tileHeight; row < M.tileHeight *2 ; row++) {
                    String line = lineList.get(row+1);
                    for (int column = 0; column < M.tileWidth; column++) {
                        char type = line.charAt(column);
                        M.extras[row-M.tileHeight][column].setType(type);
                        if (type == 'g' || type == 'i' || type == 'a' || type == 'h' )
                        {
                        	 M.extras[row-M.tileHeight][column] = new Gift (column,row-M.tileHeight,type);
                        }
                        
                        if (type != 'n')
                        {	
                        pane.getChildren().add(M.cells[row-M.tileHeight][column].drawBackground());
                        pane.getChildren().add(M.extras[row-M.tileHeight][column].drawBackground()); 
                        }
                    }  
                }
                

              String [] data = lineList.get(0).split(",");
              p.PlayerCol = (int )Double.parseDouble(data[0]);
              p.PlayerRow = (int )Double.parseDouble(data[1]);
              p.bulletCol = (int )Double.parseDouble(data[0]);
              p.bulletRow = (int )Double.parseDouble(data[1]);
              redrawpane();
              p.SCORE = (int )Double.parseDouble(data[2]); 
              SCORE.setText(Integer.toString(p.getScore()));
              p.healthLeft = Double.parseDouble(data[3]);
              HEALTH.setProgress(p.gethealth());
              p.ammoLeft = (int )Double.parseDouble(data[4]);
              AMMOLEFT.setText(Integer.toString(p.ammoLeft));
              minTime = (int)Double.parseDouble(data[5]);
              secTime = (int)Double.parseDouble(data[6]);
             
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            }
		}
		
}