package gui;

import Creature.Creature;

import javafx.scene.canvas.GraphicsContext;

public class BattleField{
    public Creature[][] FieldIndex = new Creature[9][16];

    public BattleField(){
    }


    public void setFieldIndex(Creature creature){
        int x=(int) creature.getX();
        int y=(int) creature.getY();
        FieldIndex[y][x]=creature;
    }

    public void Moveto(Creature creature,double x,double y){
        int oldx=(int) creature.getX();
        int oldy=(int) creature.getY();
        FieldIndex[oldy][oldx]=null;
        int newx=(int) x;
        int newy=(int) y;
        FieldIndex[newy][newx]=creature;
    }
    public void remove(Creature creature){
        int px=(int)creature.getX();
        int py=(int)creature.getY();
        FieldIndex[py][px]=null;
    }

    public void draw(GraphicsContext gc){
            for(int i = 0;i < 9;i++) {
                for(int j = 0;j < 16;j++) {
                    if(FieldIndex[i][j] != null){
                        FieldIndex[i][j].draw(gc);
                    }
                }
            }


    }
    public Creature getCreature(double x,double y){
        return FieldIndex[(int)y][(int)x];
    }
}
