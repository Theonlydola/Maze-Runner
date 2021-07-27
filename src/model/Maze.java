package model;

//~--- JDK imports ------------------------------------------------------------


import java.io.File;      
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.image.*;

/**
 * Represents the maze that appears on screen. Creates the maze data using a 2D
 * array of Cell objects, and renders the maze on screen.
 *
 */
public final class Maze {
	
	private static  Maze instance=null;;
	public static  Maze getInstance()
	{
		if(instance==null) return new Maze();
		else
		return instance;
		
	}
	
	public ArrayList <ImageView> drawncells = new ArrayList <ImageView>();
	public ArrayList <ImageView> addedextras = new ArrayList <ImageView>();
    final static int CELL                = 20;
    private int      lives               = 3;
    public int      score                = 0;    
    public InterfaceCell [][] cells;
    public InterfaceCell [][] extras;
    public Player    Player;
    public int      tileHeight;
    public int      tileWidth;



    public void createCellArray(String mapFile) {

        Scanner           fileReader;
        ArrayList<String> lineList = new ArrayList<String>();

        try {
            fileReader = new Scanner(new File(mapFile));

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
            
            tileHeight = lineList.size();
            tileWidth  = lineList.get(0).length();

            // Create the cells
            cells = new Cell[tileHeight][tileWidth];

            for (int row = 0; row < tileHeight; row++) {
                String line = lineList.get(row);

                for (int column = 0; column < tileWidth; column++) {
                    char type = line.charAt(column);
                    cells[row][column] = new Cell(column, row, type); 
                }  
            }
            
            System.out.println("w kaman naw");
        } catch (FileNotFoundException e) {
            System.out.println("Maze map file not found");
        }
    }

    /**
     * Generic paint method Iterates through each cell/tile in the 2D array,
     * drawing each in the appropriate location on screen
     *
     * @param g Graphics object
     */
    
   public void addextras () {
	   int i=0;
	   extras = new InterfaceCell[tileHeight][tileWidth];
	   for (int row = 0; row < tileHeight; row++) {

           // Inner loop loops through each column in the array
           for (int column = 0; column < tileWidth; column++) {
           	if (cells[row][column].getType() == 'x')
           	{
           		if (i==20)
           		{
           		 extras[row][column] = createBomb(column,row);
               	 addedextras.add(extras[row][column].drawBackground());
               	 i=0;
           		}
           		else 
           		{
           		 extras[row][column] = new Coins(column, row,'c');
               	 addedextras.add(extras[row][column].drawBackground());
               	 i++;
           		}
           	}
           	else
           	{
           		 extras[row][column] = new Cell(column, row, 'n');
              	 addedextras.add(extras[row][column].drawBackground());
           	}
           	
           }
       }
   }
    public void paintArray() {
    	
        // Outer loop loops through each row in the array
        for (int row = 0; row < tileHeight; row++) {

            // Inner loop loops through each column in the array
            for (int column = 0; column < tileWidth; column++) {
            	// System.out.println(cells[row][column].getType());
                drawncells.add(cells[row][column].drawBackground());
              
            }
        }
//        
    }

    public InterfaceCell [][] getCells() {
        return cells;
    }
   
    
    char [] gifts = {'i','g','h','a'};
    char [] bombs = {'b','d'};
    int current = -1 ;
    int bcurrent = -1;
    public Gift createGift(int x , int y)
    {
    	current++;
    	if (current >= gifts.length) current = 0 ;
    	return new Gift (x,y,gifts[current]);
    }
    
    public Bomb createBomb(int x , int y)
    {
    	bcurrent++;
    	if (bcurrent >= bombs.length) bcurrent = 0 ;
    	
    	if (bombs[bcurrent] == 'd')  return new Bomb (x,y,bombs[bcurrent],2);
    	
    	return new Bomb (x,y,bombs[bcurrent],1);
    }
 
    public void loseLife() {
        lives--;

        // TODO - Need to integrate an actual death.
        if (lives <= 0) {
            Player.endgame();
            System.out.println("Game Over!");
        }
    }
}

