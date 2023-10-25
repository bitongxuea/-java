package TankGame;

import java.util.Vector;

public class Hero extends Tank {
    public Hero(int x, int y) {
        super(x, y);
    }

    //定义一个shot对象
    Shot shot=null;
    //可以发射多颗子弹
    Vector<Shot> shots=new Vector<>();

    public void shotEnemyTank()
    {
        //创建Shot,
        switch (getDirect())
        {
            case 0:
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1:
                shot=new Shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot=new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot=new Shot(getX(),getY()+20,3);
                break;
        }
        shots.add(shot);
        //启动shot线程
        new Thread(shot).start();

    }
}
