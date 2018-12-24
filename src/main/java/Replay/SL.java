package Replay;

import Creature.Creature;
import java.util.ArrayList;
import java.util.List;

public class SL{
    public List<List<String>> Actions=new ArrayList<>();
    public void saveActions(boolean Action, Creature creature){//action为true表示移动，为false表明杀人
        char sAction,sname;
        String sx,sy;
        if(Action==true){
            sAction='M';
        }else{
            sAction='K';
        }
        if(creature.getX()>=10){//规范化x便于读取
            sx=String.valueOf((int)creature.getX());
        }else{
            sx='0'+String.valueOf((int)creature.getX());
        }
        sy=String.valueOf((int)creature.getY());//y只有一位，不需要规范化
        sname=creature.getName();
        creature.getActionSave().add(sAction+sx+sy+sname);
        //Actions.add(creature.getActionSave());
    }
    public void setActions(Creature creature){
            Actions.add(creature.getActionSave());

    }

    public String loadActions(Creature creature){
        String s;
        s=creature.getActionSave().get(0).substring(creature.getP(),creature.getP()+5);
        creature.setP(5);
        return s;
    }
}