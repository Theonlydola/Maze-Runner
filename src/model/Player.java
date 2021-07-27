package model;
import javafx.scene.image.*;
import memento.CareTaker;
import memento.Originator;
import model.Health;
import model.Ammo;
import model.Subject;
import model.Score;

public class Player {
	//Singleton
	private static  Player instance=null;
	
	public static  Player getInstance(int PlayerRow, int PlayerCol,Maze maze, double d, int ammo, int score)
	{
		if(instance==null) return new Player(PlayerRow, PlayerCol, maze, d, ammo, score);
		else return instance;
		
	}
	
  
 
    int current = 0;


    private char   direction     = 'u';
    boolean        isRunning     = true;
    Image[]        pictureUp     = new Image[3];
    Image[]        pictureRight  = new Image[3];
    Image[]        pictureLeft   = new Image[3];
    Image[]        pictureDown   = new Image[3];
    Image          bullet;
    int            totalPictures = 0;
    InterfaceCell[][]       cells;
    public double            healthLeft;
    Maze           maze;
    public int    PlayerRow, PlayerCol;
    public int ammoLeft;
    public int SCORE;
    public int bulletRow = PlayerRow;
	public int bulletCol = PlayerCol;
	
	private int iterator = 0;
	private int CHECKPOINT=0;
	private boolean SENDGIFT = false;
	Subject subject = new Subject();
    Score score = new Score(subject);
	Health health = new Health(subject);
	Ammo ammo = new Ammo(subject);
	
	
	 Originator originator = new Originator();
     CareTaker careTaker = new CareTaker();
	
    Thread         thread;
    ImageView[] i = new ImageView [2] ;
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    
    public Player(int initialRow, int initialColumn, Maze startMaze, double health, int ammo, int score) {
        
    	PlayerRow = initialRow;
        PlayerCol = initialColumn;
        bulletRow = initialRow;
        bulletCol = initialColumn;
        maze      = startMaze;
        healthLeft = health;
        cells     = maze.getCells();
        ammoLeft = ammo;
        SCORE = score;
    }
    
   public void loadsprite()
   {
	 
	pictureUp [0] = new Image(getClass().getClassLoader().getResource("UP.png").toString(), true);
   	pictureUp [1] = new Image(getClass().getClassLoader().getResource("UP1.png").toString(), true);
   	pictureUp [2] = new Image(getClass().getClassLoader().getResource("UP2.png").toString(), true);
   	
   	pictureDown [0] = new Image(getClass().getClassLoader().getResource("DOWN.png").toString(), true);
   	pictureDown [1] = new Image(getClass().getClassLoader().getResource("DOWN1.png").toString(), true);
   	pictureDown [2] = new Image(getClass().getClassLoader().getResource("DOWN2.png").toString(), true);
   	
   	pictureLeft [0] = new Image(getClass().getClassLoader().getResource("LEFT.png").toString(), true);
   	pictureLeft [1] = new Image(getClass().getClassLoader().getResource("LEFT1.png").toString(), true);
   	pictureLeft [2] = new Image(getClass().getClassLoader().getResource("LEFT2.png").toString(), true);
   	
   	pictureRight [0] = new Image(getClass().getClassLoader().getResource("RIGHT.png").toString(), true);
   	pictureRight [1] = new Image(getClass().getClassLoader().getResource("RIGHT1.png").toString(), true);
   	pictureRight [2] = new Image(getClass().getClassLoader().getResource("RIGHT2.png").toString(), true);
   	bullet = new Image(getClass().getClassLoader().getResource("bullet.png").toString(), true);
   }
   
    public ImageView [] drawPlayer() {
   
        if (direction == 'u') {
            if (current > pictureUp.length - 1) {
                current = 0;
            }
            
            i[0] = new ImageView (pictureUp[current]) ;
            i[0].setY(PlayerRow*20);
            i[0].setX(PlayerCol*20); 
            i[0].setFitWidth(21);
            i[0].setFitHeight(30);
            
            i[1] = new ImageView (bullet);
            i[1].setY(bulletRow*20+15);
            i[1].setX(bulletCol*20+5); 
            i[1].setFitWidth(5);
            i[1].setFitHeight(5);
            return i;
           
        }

        if (direction == 'd') {
            if (current > pictureDown.length - 1) {
                current = 0;
            }
            i[0] = new ImageView (pictureDown[current]) ;
            i[0].setY(PlayerRow*20);
            i[0].setX(PlayerCol*20); 
            i[0].setFitWidth(21);
            i[0].setFitHeight(30);
            
            i[1] = new ImageView (bullet);
            i[1].setY(bulletRow*20+15);
            i[1].setX(bulletCol*20+5);  
            i[1].setFitWidth(5);
            i[1].setFitHeight(5);
            return i;
        }

        if (direction == 'l') {
            if (current > pictureLeft.length - 1) {
                current = 0;
            }
            i[0] = new ImageView (pictureLeft[current]) ;
            i[0].setY(PlayerRow*20);
            i[0].setX(PlayerCol*20);   
            i[0].setFitWidth(30);
            i[0].setFitHeight(21);
            
            i[1] = new ImageView (bullet);
            i[1].setY(bulletRow*20+5);
            i[1].setX(bulletCol*20+15);  
            i[1].setFitWidth(5);
            i[1].setFitHeight(5);
            return i;
        }

        if (direction == 'r') {
            if (current > pictureRight.length - 1) {
                current = 0;
            }
            i[0] = new ImageView (pictureRight[current]) ;      
            i[0].setY(PlayerRow*20);
            i[0].setX(PlayerCol*20); 
            i[0].setFitWidth(30);
            i[0].setFitHeight(21);
            
            i[1] = new ImageView (bullet);
            i[1].setY(bulletRow*20+5);
            i[1].setX(bulletCol*20+10); 
            i[1].setFitWidth(5);
            i[1].setFitHeight(5);
            return i;
            
        }
        
       
        return i;
    }


    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return this.direction;
    }

   
    public void eatPellet(int column, int row) {
        if (cells[column][row].getType() == 'd') {
//            subject.setState(10);
        	 cells[column][row].setType('o');

        }

        if (cells[column][row].getType() == 'p') {
//        	 subject.setState(20);
            cells[column][row].setType('o');
        }
    }
    
  
    public void movePlayer(int x, int y) {
    	
        current++;  
        System.out.println("ROW " + PlayerRow + ", COL " + PlayerCol);
        if (isCellNavigable(PlayerRow + x,PlayerCol + y)) 
        {
        	PlayerRow = PlayerRow + x;
            PlayerCol = PlayerCol + y;
            bulletRow = bulletRow + x;
            bulletCol = bulletCol + y;
            
        }
        System.out.println("ROW " + PlayerRow + ", COL " + PlayerCol);
        System.out.println("ROW " + bulletRow + ", COL " +  bulletCol);// print out current row and column to console
    }

    
    public boolean isCellNavigable(int column, int row) {
    	System.out.println(maze.cells[column][row].getType());
        
    	switch (maze.cells[column][row].getType())
        {
        	case 'x': return true;
        	case 't': return false;
        	case 'f': return true;
        	default : return false;
        }
 
    }
    
    public void updateScore(int s,int time) {
    	if (s<0) {SCORE = 0; iterator = 0;}
    	else
    	{
    		subject.setState(0, s, time,0);
    		SCORE += score.score; 
    		iterator +=  score.score;
    	}
    	
    	System.out.println(iterator);
    	
    	if (iterator>=100)
    	{
    		iterator = 0;
    		SCORE = SCORE - SCORE%100;
    		double [] state = new double [5];
    		state[0] = PlayerCol;
    		state[1] = PlayerRow;
    		state[2] = SCORE;
    		state[3] = healthLeft;
    		state[4] = ammoLeft;
    	    originator.setState(state);
            careTaker.add(originator.saveStateToMemento());  
            CHECKPOINT++;
            SENDGIFT = true;
    	}
    	
    }
    
    
    public double [] getPlayerLastState()
    {
     if (CHECKPOINT == 0) return null;
     originator.getStateFromMemento(careTaker.get(CHECKPOINT-1));
     return originator.getState();
    }
    
    public void setScoreCheckPoint(int i)
    {
    	
    	if (CHECKPOINT-i == -1) SCORE = 0;
    	else
    	{
        originator.getStateFromMemento(careTaker.get(CHECKPOINT-i));
        if ((int) originator.getState()[2] >= SCORE) setScoreCheckPoint(i+1);
        else SCORE = (int) originator.getState()[2] ;
    	}
    	
    }
    
    public boolean sendGift()
    {
    	return SENDGIFT;
    }
    
    public void stopsendGift()
    {
    	SENDGIFT = false;
    }
    
    public void updateHealth(int h, int time) {
    	subject.setState(h, 0, time,0);
    	healthLeft += health.health;
    	if (healthLeft > 1) healthLeft = 1;
    	if (healthLeft < 0) healthLeft = 0;
    }
    
    public void updateAmmo(int a) {
    	subject.setState(0, 0,0,a);
    	ammoLeft += ammo.ammo;
    	if (ammoLeft > 9) ammoLeft = 9;
    	if (ammoLeft<0) ammoLeft = 0;
    }
    
    public int getScore() {
        return SCORE;
    }

   
    public double gethealth() {
        return healthLeft;
    }
    
    

    protected void endgame() {
        this.isRunning = false;
    }
}

