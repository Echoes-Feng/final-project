package gui;

import Creature.Creature;
import Creature.hulubrothers;
import Replay.IOfile;
import Replay.SL;
import javafx.stage.FileChooser;
import Creature.monsters;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;


public class MainCanvas extends Canvas{
    private GraphicsContext gContext;
    private Image map;
    private BattleField initField=new BattleField();
    private boolean isRunning=false,isReplay=false;
    private int tileWidth = 80;
    private int tileHeight = 80;
    private hulubrothers inithulu=new hulubrothers();
    private monsters initmonster=new monsters();
    private IOfile output=new IOfile();
    private SL sl =new SL();
    private Stage stage;
    public void init(){//初始化所有生物
        inithulu.inithulubrothers();
        initmonster.initmonsters();

        for(Creature hulu:inithulu.HuluBrothers){
            initField.setFieldIndex(hulu);
        }
        for(Creature monsters :initmonster.Monsters){
            initField.setFieldIndex(monsters);
        }

        Creature.BattleField=this.initField;
        Creature.sl=this.sl;
        Creature.mainCanvas=this;
    }
    // 主线程
    private Thread MainThread;

    {
        MainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public synchronized void run() {
                        draw();
                    }
                });
                    while(isRunning==false&&isReplay==false) {
                        getParent().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {//键盘事件
                            @Override
                            public void handle(KeyEvent event) {
                                if (event.getCode() == KeyCode.SPACE) {//按空格后启动线程
                                    isRunning = true;
                                    for (Creature hulu : inithulu.HuluBrothers) {
                                        hulu.startThreads();
                                    }
                                    for (Creature monster : initmonster.Monsters) {
                                        monster.startThreads();
                                    }
                                }
                                if (event.getCode() == KeyCode.L&&isRunning==false ) {//按L读取文件,开启回放模式
                                    FileChooser fileChooser = new FileChooser();
                                    File f = fileChooser.showOpenDialog(stage);
                                    for (Creature hulu : inithulu.HuluBrothers) {
                                        output.resultIn(f, hulu);
                                        hulu.startThreads();
                                    }
                                    for (Creature monster : initmonster.Monsters) {
                                        output.resultIn(f, monster);
                                        monster.startThreads();
                                    }
                                    isReplay = true;
                                }
                            }
                        });
                    }
                    while (isRunning||isReplay) {
                        int H = 0, M = 0;//用于计算葫芦娃和妖怪的死亡数
                        Creature Snake = initmonster.Monsters.get(0);
                        Creature Scorpion = initmonster.Monsters.get(1);
                        Platform.runLater(new Runnable() {//画出每个状态的图像，因为javafx的机制需要在主线程中加入
                            @Override
                            public synchronized void run() {
                                draw();
                            }
                        });
                        if (Snake.getBlood() == false && Scorpion.getBlood() == false) {//如果蛇精和蝎子精都死了，所有小喽啰也死亡
                            for (Creature monster : initmonster.Monsters) {
                                monster.setBlood(false);
                                monster.removeCharacters();
                            }
                        }
                        for (Creature hulu : inithulu.HuluBrothers) {//葫芦娃死亡计数
                            if (hulu.getBlood() == false) {
                                H++;
                            }
                        }
                        for (Creature monster : initmonster.Monsters) {//妖怪死亡计数
                            if (monster.getBlood() == false) {
                                M++;
                            }
                        }
                        if (M == initmonster.Monsters.size()) {//如果妖怪全死亡，输出葫芦娃胜利，主线程停止，并且将所有生物血量清零以停止子线程
                            for (Creature hulubrothers : inithulu.HuluBrothers) {
                                hulubrothers.setBlood(false);
                                //sl.saveActions(false,hulubrothers);
                            }
                            isRunning = false;
                            isReplay = false;
                            Platform.runLater(new Runnable() {
                                @Override
                                public synchronized void run() {
                                    gContext.fillText("葫芦娃胜利", 6 * tileWidth, 5 * tileHeight);
                                }
                            });
                            try {
                                output.resultOut(sl.Actions);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (H == inithulu.HuluBrothers.size()) {//如果葫芦娃全死亡，输出妖怪胜利，主线程停止,并且将所有生物血量清零以停止子线程
                            for (Creature monster : initmonster.Monsters) {
                                monster.setBlood(false);
                                //sl.saveActions(false,monster);
                            }
                            isRunning = false;
                            isReplay = false;
                            Platform.runLater(new Runnable() {
                                @Override
                                public synchronized void run() {
                                    gContext.fillText("妖精胜利", 6 * tileWidth, 5 * tileHeight);
                                }
                            });
                            try {
                                output.resultOut(sl.Actions);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }


        });
    }

    public MainCanvas(double width, double height) {
        super(width, height);
        map = new Image(getClass().getClassLoader().getResourceAsStream("map0.jpeg"));
        gContext = getGraphicsContext2D();
        gContext.setFont(Font.font("Verdana",80));
        init();
        MainThread.start();
    }

    public void draw(){//画地图与所有生物的函数
        gContext.drawImage(map,0,0);
        initField.draw(gContext);
    }

    public boolean getisReplay(){
        return isReplay;
    }

    public boolean getisRunning(){
        return isRunning;
    }

}
