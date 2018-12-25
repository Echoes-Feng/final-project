# final-project  正在不断完善中
## 设计思路
- 生物体为一个抽象类储存着生物的信息，所有的生物体都继承这个类，通过生物体的类来调度生物的行动
- 在生物的各个类中，会以枚举的方式初始化所有生物的图像、名字、初始位置等信息
- 使用JavaFX的Canvas画布功能来实现图像化
- BattleField类为地图信息，包括地图绘制，每个地图点位是不是有生物
## 类
- Main类：主类控制着程序运行
- MainCanvas类：画布类，控制着图像输出
- Creature类：生物的抽象类，寄存着位置等信息
- hulubrothers与monsters：子类，保存了图像信息
- BattleField类：战场信息
- Controller类：未使用
- IOfile类：文件的输出
- SL类：存储于加载存档
## 面向对象变成的体现
### 封装
每一次方法的实现都是一次封装
    
    public synchronized void MoveToLine(double x,double y){
        try{
            BattleField.Moveto(this, x, y);
            setX(x);
            setY(y);
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }

    }
### 继承与多态
![](https://github.com/Echoes-Feng/final-project/blob/master/src/main/resources/Creature.png)
### 多线程
每一个生物体都是一个线程，并且加锁进行了同步确保安全
    
    public abstract class Creature implements Runnable
    
    synchronized (BattleField) {//将战场锁住，同步线程，避免站到同一个位置}
### 异常处理以及IO输出
        try{
            writer=new BufferedWriter(new FileWriter(file));
            Iterator<List<String>> it = Action.iterator();
            while(it.hasNext()){
                for(String s : it.next()){
                    writer.write(s);
                }
                writer.write("\r\n");
                writer.flush();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
### 泛型
     public List<List<String>> Actions=new ArrayList<>();
## 已实现功能
- 按空格开始游戏，按L读取
- 所有生物会随机移动向八个方向移动，会杀死自己移动路径上的敌人，一方全部死亡后，游戏结束。
## 待完善的功能
- 随机阵型的实现，人物血量，攻击力等，UI完善
