package model;

import java.util.Random;


import javafx.scene.image.ImageView;

public class Monster  {
	ImageView monster;
	int SIZE  =20;
    public boolean isRunning = true;
    Random  randGen   = new Random();
    public char type;
    private int MonsterRow, MonsterCol;
    public int isded;
    Maze                        maze;

    public Monster(int initialRow, int initialColumn, Maze startMaze, char mtype, int isdead) {
        MonsterRow = initialRow;
        MonsterCol = initialColumn;
        maze     = startMaze;
        type = mtype;
        isded=isdead;
    }

    public ImageView drawMonster() {
        switch (type)
        {
        case 'k' : 
        	monster = Factory.Create("killer");
            monster.setX(MonsterRow * SIZE);
            monster.setY( MonsterCol * SIZE + SIZE / 2 - 10);
            monster.setFitWidth(SIZE);
            monster.setFitHeight(SIZE);
            return monster;
            
        case 't' :
        	monster = Factory.Create("theif");
            monster.setX(MonsterRow * SIZE);
            monster.setY( MonsterCol * SIZE + SIZE / 2 - 10);
            monster.setFitWidth(SIZE);
            monster.setFitHeight(SIZE);
            return monster;
        case 's' :
        	monster = Factory.Create("sakhif");
            monster.setX(MonsterRow * SIZE);
            monster.setY( MonsterCol * SIZE + SIZE / 2 - 10);
            monster.setFitWidth(SIZE);
            monster.setFitHeight(SIZE);
            return monster; 
        }
		return null;
    }

    /*
     * Get the current row
     *
     */
    public int getRow() {
        return MonsterRow;
    }

    public int getCol() {
        return MonsterCol;
    }

    protected void moveRow(int x) {
        if (isCellNavigable(MonsterCol, MonsterRow + x)) {
            MonsterRow = MonsterRow + x;
        }
        
   
    }

    protected void moveCol(int y) {
        if (isCellNavigable(MonsterCol + y, MonsterRow)) {
            MonsterCol = MonsterCol + y;
        }
       
    }

  
    public void move() {
        while (isRunning) {
            // Move
            switch (randGen.nextInt(4) + 1) {
            case (1) :
                moveCol(-1);
                break;

            case (2) :
                moveCol(1);
               
                break;

            case (3) :
            	
                moveRow(-1);
               
                break;

            case (4) :
                moveRow(1);
            
                break;
             default : break;
            }
         isRunning = false;
        }
    }

    public boolean isCellNavigable(int column, int row) {
        return ((maze.cells[column][row].getType() == 'x'));
    }
}
