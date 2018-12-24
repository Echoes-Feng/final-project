package Creature;

//import Replay.IOfile;
import Replay.SL;
import gui.BattleField;
import gui.MainCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.*;
import java.util.Random;


public abstract class Creature implements Runnable{
    protected double x,y;//坐标位置
    protected boolean blood ;//血量，暂时只有存活与死亡
    protected Image image;//图像
    protected boolean isJustic;//确定生物的阵营，true为好，false为坏
    protected char name;
    protected int p=0;//读取文件的指针
    private int tileWidth = 80;//像素宽度
    private int tileHeight = 80;//像素高度
    private List<String> actionSave=new ArrayList<>();


    public static SL sl;//存档与读取类
    public static BattleField BattleField;//战斗区域类，从MainCanvas中获取
    public static MainCanvas mainCanvas;



    public Creature(){
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean getBlood(){return blood;}

    public void setBlood(boolean blood){this.blood=blood;}

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean getJustic(){return isJustic;}

    public void setJustic(boolean isJustic){
        this.isJustic=isJustic;
    }

    public void setName(char name){this.name=name;}

    public char getName(){return name;}

    public int getP(){return p; }

    public void setP(int p){this.p+=p;}

    public List<String> getActionSave(){return actionSave;}

    public void setPosition(double x,double y){
        setX(x);
        setY(y);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.x*tileWidth, this.y*tileHeight);
    }//在画布上画出生物

    public synchronized void MoveToLine(double x,double y){
        try{
            BattleField.Moveto(this, x, y);
            setX(x);
            setY(y);
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }

    }

    public void removeCharacters(){
        BattleField.remove(this);
    }
    @Override
    public synchronized void run(){
        try{
            while(blood){
                if(mainCanvas.getisReplay()){
                    synchronized (sl){
                        String s;
                        s=sl.loadActions(this);
                        if(s.charAt(0)=='M'){
                            System.out.println(Double.valueOf(s.substring(1,3))+Double.valueOf(s.substring(3,4)));
                            MoveToLine(Double.valueOf(s.substring(1,3)),Double.valueOf(s.substring(3,4)));
                        }
                        if (s.charAt(4)=='K'){
                            this.removeCharacters();
                            this.blood=false;
                            break;
                        }
                    }

            }else{
                    Random random = new Random();
                    double r1 = random.nextInt(3)-1;
                    double r2 = random.nextInt(3)-1;
                    double nextX = r1 + this.x;
                    double nextY = r2 + this.y;
                    if (nextX < 0) {//越界处理
                        nextX += 2;
                    }
                    if (nextX > 15) {
                        nextX -= 2;
                    }
                    if (nextY < 0) {
                        nextY += 2;
                    }
                    if (nextY > 8) {
                        nextY -= 2;
                    }
                    synchronized (BattleField) {//将战场锁住，同步线程，避免站到同一个位置
                        Creature isCrashed = BattleField.getCreature(nextX, nextY);
                        if (isCrashed == null) {
                            MoveToLine(nextX, nextY );//移动的位置没有生物时移动过去
                            synchronized (sl){
                                sl.saveActions(true,this);
                            }

                        }
                        else {
                            if (isJustic != isCrashed.isJustic) {//如果目标位置的生物与自己阵营不同，杀掉他，如果相同则不动
                                isCrashed.removeCharacters();
                                isCrashed.blood = false;
                                this.blood=true;
                                synchronized (sl){
                                    sl.saveActions(false,isCrashed);
                                }

                            }
                        }
                    }

                }
                synchronized (this){
                    if(!blood){
                        break;
                    }
                }
                Thread.sleep(100);
            }
            sl.setActions(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void startThreads(){//开始线程
        Thread thread=new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }


}
