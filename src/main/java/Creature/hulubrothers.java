package Creature;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class hulubrothers extends Creature{//葫芦娃类

    public List<Creature> HuluBrothers = new ArrayList<Creature>();

    public void inithulubrothers() {
        hulubrothers RedRunner = new hulubrothers();//大娃初始化
        Image RedImage = new Image(getClass().getClassLoader().getResourceAsStream("Red.png"));
        RedRunner.setPosition(6,4);
        RedRunner.setImage(RedImage);
        RedRunner.setName('R');
        RedRunner.setJustic(true);
        RedRunner.setBlood(true);

        hulubrothers OrangeRunner = new hulubrothers();//二娃初始化
        Image OrangeImage = new Image(getClass().getClassLoader().getResourceAsStream("Orange.png"));
        OrangeRunner.setPosition(5,3);
        OrangeRunner.setImage(OrangeImage);
        OrangeRunner.setName('O');
        OrangeRunner.setJustic(true);
        OrangeRunner.setBlood(true);

        hulubrothers YellowRunner = new hulubrothers();//三娃初始化
        Image YellowImage = new Image(getClass().getClassLoader().getResourceAsStream("Yellow.png"));
        YellowRunner.setPosition(4,2);
        YellowRunner.setImage(YellowImage);
        YellowRunner.setName('Y');
        YellowRunner.setJustic(true);
        YellowRunner.setBlood(true);

        hulubrothers GreenRunner = new hulubrothers();//四娃初始化
        Image GreenImage = new Image(getClass().getClassLoader().getResourceAsStream("Green.png"));
        GreenRunner.setPosition(3,1);
        GreenRunner.setImage(GreenImage);
        GreenRunner.setName('G');
        GreenRunner.setJustic(true);
        GreenRunner.setBlood(true);

        hulubrothers IndigoRunner = new hulubrothers();//五娃初始化
        Image IndigoImage = new Image(getClass().getClassLoader().getResourceAsStream("Indigo.png"));
        IndigoRunner.setPosition(5,5);
        IndigoRunner.setImage(IndigoImage);
        IndigoRunner.setName('I');
        IndigoRunner.setJustic(true);
        IndigoRunner.setBlood(true);

        hulubrothers BlueRunner = new hulubrothers();//六娃初始化
        Image BlueImage = new Image(getClass().getClassLoader().getResourceAsStream("Blue.png"));
        BlueRunner.setPosition(4,6);
        BlueRunner.setImage(BlueImage);
        BlueRunner.setName('B');
        BlueRunner.setJustic(true);
        BlueRunner.setBlood(true);

        hulubrothers PurpleRunner = new hulubrothers();//七娃初始化
        Image PurpleImage = new Image(getClass().getClassLoader().getResourceAsStream("Purple.png"));
        PurpleRunner.setPosition(3,7);
        PurpleRunner.setImage(PurpleImage);
        PurpleRunner.setName('P');
        PurpleRunner.setJustic(true);
        PurpleRunner.setBlood(true);

        hulubrothers GrandpaRunner = new hulubrothers();//爷爷初始化
        Image GrandpaImage = new Image(getClass().getClassLoader().getResourceAsStream("Grandpa.png"));
        GrandpaRunner.setPosition(2,4);
        GrandpaRunner.setImage(GrandpaImage);
        GrandpaRunner.setName('F');
        GrandpaRunner.setJustic(true);
        GrandpaRunner.setBlood(true);

        HuluBrothers.add(RedRunner);
        HuluBrothers.add(OrangeRunner);
        HuluBrothers.add(YellowRunner);
        HuluBrothers.add(GreenRunner);
        HuluBrothers.add(IndigoRunner);
        HuluBrothers.add(BlueRunner);
        HuluBrothers.add(PurpleRunner);
        HuluBrothers.add(GrandpaRunner);
    }
    public hulubrothers(){
    }


}