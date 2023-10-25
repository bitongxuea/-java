package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//绘图区
public class MyPanel extends JPanel implements KeyListener,Runnable {
    Hero hero=null;
    Vector<EnemyTank> et=new Vector<>();
    //定义一个存放node的Vector
    Vector<Node> nodes=new Vector<>();
    int enenmtTankSize=3;
    //定义一个vector用于存放炸弹
    Vector<Bomb> bombs=new Vector<>();
    //定义3张图片用于显示爆炸效果
    Image image1=null;
    Image image2=null;
    Image image3=null;
    public MyPanel(String s)
    {
        nodes=Recorder.getNodesAndEnemyTanks();
        Recorder.setEnemyTanks(et);
        hero=new Hero(800,100);
        switch(s)
        {

            case "1":
                //初始化敌人的坦克
                for(int i=0;i<enenmtTankSize;i++)
                {
                    EnemyTank enemyTank = new EnemyTank((100*(i+1)),0);
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(et);

                    enemyTank.setDirect(2);
                    //启动敌人坦克线程，让其动起来
                    new Thread(enemyTank).start();
                    et.add(enemyTank);
                    //给该enemy加入一颗子弹
                    Shot shot=new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();

                }
                break;
            case "2":
                //初始化敌人的坦克
                for(int i=0;i<nodes.size();i++)
                {
                    Node node=nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(),node.getY());
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(et);

                    enemyTank.setDirect(node.getDirect());
                    //启动敌人坦克线程，让其动起来
                    new Thread(enemyTank).start();
                    et.add(enemyTank);
                    //给该enemy加入一颗子弹
                    Shot shot=new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();

                }
                break;

        }

        //初始化图片对象
        image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
        //播放音乐
        new AePlayWave("src\\111.wav").start();


    }
    //编写方法，显示我方击毁坦克信息
    public void showInfo(Graphics g)
    {
        g.setColor(Color.blue);
        Font font=new Font("宋体",Font.BOLD,25);
        g.setFont(font);

        g.drawString("您累积击毁敌方坦克",1020,30);
        drawTank(1020,60,g,0,1);
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnemyTankNmu()+"",1080,100);
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色,游戏界面
        showInfo(g);
        //画出坦克--方法
        if(hero!=null&&hero.isLive)
        {
            drawTank(hero.getX(),hero.getY(),g, hero.getDirect(), 0);
        }

//        //画出子弹
//        if(hero.shot!=null&&hero.shot.isLive==true)
//        {
//            //
//            // g.fill3DRect(hero.shot.x,hero.shot.y,3,3,false);
//            g.draw3DRect(hero.shot.x,hero.shot.y,2,2,false);
//        }
        for(int i=0;i<hero.shots.size();i++)
        {
            Shot shot=hero.shots.get(i);
            if(shot!=null&&shot.isLive==true)
            {
                g.draw3DRect(shot.x,shot.y,2,2,false);
            }
            else
            {
                hero.shots.remove(shot);
            }
        }
        for(int i=0;i<bombs.size();i++)
        {
            //取出炸弹
            Bomb bomb=bombs.get(i);
            if(bomb.life>6)
            {
                g.drawImage(image1, bomb.x,bomb.y,60,60,this);
            }
            else if(bomb.life>3)
            {
                g.drawImage(image2, bomb.x,bomb.y,60,60,this);
            }
            else
            {
                g.drawImage(image3, bomb.x,bomb.y,60,60,this);
            }
            bomb.lifeDown();
            if(bomb.life==0)
            {
                bombs.remove(bomb);
            }

        }
        for (int i=0;i<et.size();i++)
        {
            EnemyTank enemyTank = et.get(i);
            if(enemyTank.isLive==true)
            {
                drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
                for(int j=0;j<enemyTank.shots.size();j++)
                {
                    Shot shot=enemyTank.shots.get(j);
                    //绘制子弹
                    if(shot.isLive==true)
                    {
                        g.draw3DRect(shot.x,shot.y,1,1,false);
                    }
                    else {
                        //从vector移除
                        enemyTank.shots.remove(shot);
                    }
                }

            }

        }
    }

    /**
     *
     * @param x
     * @param y
     * @param g
     * @param direction 坦克方向
     * @param type  坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direction,int type)
    {
        switch(type)
        {
            case 0://自己的坦克

                g.setColor(Color.cyan);
                break;
            case 1://敌人坦克

                g.setColor(Color.yellow);
                break;
        }
        //根据坦克方向来绘制坦克 4种不同的形态。
        switch(direction)
        {
            case 0://向上
                g.fill3DRect(x,y,10,60,false);//画出坦克左边轮子
                g.fill3DRect(x+30,y,10,60,false);//右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克中间部分
                g.fillOval(x+10,y+20,20,20);//坦克的圆盖
                g.drawLine(x+20,y,x+20,y+30);//坦克的炮筒，用直线来表示

                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);//画出坦克左边轮子
                g.fill3DRect(x,y+30,60,10,false);//右边轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克中间部分
                g.fillOval(x+20,y+10,20,20);//坦克的圆盖
                g.drawLine(x+60,y+20,x+30,y+20);//坦克的炮筒，用直线来表示
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);//画出坦克左边轮子
                g.fill3DRect(x+30,y,10,60,false);//右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克中间部分
                g.fillOval(x+10,y+20,20,20);//坦克的圆盖
                g.drawLine(x+20,y+60,x+20,y+30);//坦克的炮筒，用直线来表示
                break;
            case 3://向左
                g.fill3DRect(x,y,60,10,false);//画出坦克左边轮子
                g.fill3DRect(x,y+30,60,10,false);//右边轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克中间部分
                g.fillOval(x+20,y+10,20,20);//坦克的圆盖
                g.drawLine(x,y+20,x+30,y+20);//坦克的炮筒，用直线来表示
                break;
            default:
                System.out.println("等待处理！");
        }
    }
    //监听有字符输入
    @Override
    public void keyTyped(KeyEvent e) {

    }
    public void hitEnemyTank()
    {
        for(int j=0;j<hero.shots.size();j++)
        {
            Shot shot=hero.shots.get(j);
            //在此处判断是击中敌人坦克
            if(shot!=null&&shot.isLive==true)
            {
                for(int i=0;i<et.size();i++)
                {
                    EnemyTank enemyTank=et.get(i);
                    hitTank(shot,enemyTank);
                }
            }
        }
    }
    //编写方法判断我方子弹是否击中敌人坦克
    public void hitTank(Shot shot,Tank enemyTank)
    {
        switch(enemyTank.getDirect())
        {
            case 0:
                if(shot.x> enemyTank.getX()&&shot.x< enemyTank.getX()+40&&shot.y> enemyTank.getY()&&shot.y< enemyTank.getY()+60)
                {
                    shot.isLive=false;
                    enemyTank.isLive=false;
                    et.remove(enemyTank);
                    if(enemyTank instanceof EnemyTank)
                    {
                        Recorder.addAllEnemyTank();
                    }

                    //创建一个bomb对象
                    Bomb bomb = new Bomb(enemyTank.getX(),enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
                if(shot.x> enemyTank.getX()&&shot.x< enemyTank.getX()+60&&shot.y> enemyTank.getY()&&shot.y< enemyTank.getY()+40)
                {
                    shot.isLive=false;
                    enemyTank.isLive=false;
                    if(enemyTank instanceof EnemyTank)
                    {
                        Recorder.addAllEnemyTank();
                    }
                    Bomb bomb = new Bomb(enemyTank.getX(),enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 2:
                if(shot.x> enemyTank.getX()&&shot.x< enemyTank.getX()+40&&shot.y> enemyTank.getY()&&shot.y< enemyTank.getY()+60)
                {
                    shot.isLive=false;
                    enemyTank.isLive=false;
                    if(enemyTank instanceof EnemyTank)
                    {
                        Recorder.addAllEnemyTank();
                    }
                    Bomb bomb = new Bomb(enemyTank.getX(),enemyTank.getY());
                    bombs.add(bomb);
                }
            case 3:
                if(shot.x> enemyTank.getX()&&shot.x< enemyTank.getX()+60&&shot.y> enemyTank.getY()&&shot.y< enemyTank.getY()+40)
                {
                    shot.isLive=false;
                    enemyTank.isLive=false;
                    if(enemyTank instanceof EnemyTank)
                    {
                        Recorder.addAllEnemyTank();
                    }
                    Bomb bomb = new Bomb(enemyTank.getX(),enemyTank.getY());
                    bombs.add(bomb);
                }
                break;

        }
    }
    //判断敌人坦克是否击中我方坦克
    public void hitHero()
    {
        for(int i=0;i<et.size();i++)
        {
            EnemyTank enemyTank=et.get(i);
            //遍历坦克对象的所有子弹
            for(int j=0;j<enemyTank.shots.size();j++)
            {
                Shot shot=enemyTank.shots.get(j);
                if(hero.isLive&&shot.isLive)
                {
                    hitTank(shot,hero);
                }
            }
        }
    }
    //监听某个键被按下
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_W)
        {
            //改变坦克方向
            hero.setDirect(0);
            //修改坦克坐标
            if(hero.getY()>0)
            {
                hero.moveUp();
            }

        }
        else if (e.getKeyCode()==KeyEvent.VK_D)
        {
            hero.setDirect(1);
            if(hero.getX()+60<1000)
            {
                hero.moveRight();
            }

        }
        else if (e.getKeyCode()==KeyEvent.VK_S)
        {
            hero.setDirect(2);
            if(hero.getY()+60<750)
            {
                hero.moveDown();
            }

        }
        else if (e.getKeyCode()==KeyEvent.VK_A)
        {
            hero.setDirect(3);
            if(hero.getX()>0)
            {
                hero.moveLeft();
            }

        }

        //当用户按下J时，子弹发射
        if(e.getKeyCode()==KeyEvent.VK_J)
        {
//            if(hero.shot==null||hero.shot.isLive==false)
//            {
//                hero.shotEnemyTank();
//            }
            hero.shotEnemyTank();

        }
        this.repaint();
    }
    //监听某个键被释放
    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void run()
    {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }
}
