package Creature;

import Creature.Creature;
import javafx.scene.image.Image;

import java.lang.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class monsters extends Creature{//妖怪类
    public List<Creature> Monsters = new ArrayList<Creature>();
    public void initmonsters() {
        Image SnakeImage = new Image(getClass().getClassLoader().getResourceAsStream("Snake.png"));
        monsters SnakeRunner = new monsters();
        SnakeRunner.setPosition(14,4);
        SnakeRunner.setImage(SnakeImage);
        SnakeRunner.setName('S');
        SnakeRunner.setJustic(false);
        SnakeRunner.setBlood(true);

        Image ScorpionImage = new Image(getClass().getClassLoader().getResourceAsStream("Scorpion.png"));
        monsters ScorpionRunner = new monsters();
        ScorpionRunner.setPosition(10,4);
        ScorpionRunner.setImage(ScorpionImage);
        ScorpionRunner.setName('s');
        ScorpionRunner.setJustic(false);
        ScorpionRunner.setBlood(true);

        Monsters.add(SnakeRunner);
        Monsters.add(ScorpionRunner);

        Image MinionImage = new Image(getClass().getClassLoader().getResourceAsStream("Minion.png"));
        int[][] Location={{10,3},{10,5},{9,3},{9,4},{9,5},{11,3},{11,4},{11,5}};
        for(int i=0;i<8;i++){
            monsters MinonRunner = new monsters();
            MinonRunner.setPosition(Location[i][0],Location[i][1]);
            MinonRunner.setImage(MinionImage);
            MinonRunner.setName((char)(i+49));
            MinonRunner.setJustic(false);
            MinonRunner.setBlood(true);
            Monsters.add(MinonRunner);
        }
    }
    public monsters(){

    }

}