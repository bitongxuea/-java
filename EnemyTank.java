package TankGame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    Vector<Shot> shots=new Vector<>();
    //增加成员
    Vector<EnemyTank> enemyTanks=new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
    //编写方法，判断当前这个坦克是否和其他坦克发生碰撞
    public boolean isTouchEnemyTank()
    {
        //判断当前坦克方向
        switch(this.getDirect())
        {
            case 0:
                //让当前敌人坦克与其他所有敌人坦克比较
                for(int i=0;i<enemyTanks.size();i++)
                {
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(enemyTank!=this)
                    {
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2)
                        {
                            if(this.getX()>=enemyTank.getX()&&this.getX()<=enemyTank.getX()+40&&
                            this.getY()>=enemyTank.getY()&&this.getY()<=enemyTank.getY()+60)
                            {
                                return true;
                            }
                            if(this.getX()+40>=enemyTank.getX()&&this.getX()+40<=enemyTank.getX()+40&&
                                    this.getY()>=enemyTank.getY()&&this.getY()<=enemyTank.getY()+60)
                            {
                                return true;
                            }
                        }
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3)
                        {
                            if(this.getX()>=enemyTank.getX()&&this.getX()<=enemyTank.getX()+60&&
                                    this.getY()>=enemyTank.getY()&&this.getY()<=enemyTank.getY()+40)
                            {
                                return true;
                            }
                            if(this.getX()+40>=enemyTank.getX()&&this.getX()+40<=enemyTank.getX()+60&&
                                    this.getY()>=enemyTank.getY()&&this.getY()<=enemyTank.getY()+40)
                            {
                                return true;
                            }
                        }

                    }
                }
                break;
            case 1:
                //让当前敌人坦克与其他所有敌人坦克比较
                for(int i=0;i<enemyTanks.size();i++)
                {
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(enemyTank!=this)
                    {
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2)
                        {
                            if(this.getX()+60>=enemyTank.getX()&&this.getX()+60<=enemyTank.getX()+40&&
                                    this.getY()>=enemyTank.getY()&&this.getY()<=enemyTank.getY()+60)
                            {
                                return true;
                            }
                            if(this.getX()+60>=enemyTank.getX()&&this.getX()+60<=enemyTank.getX()+40&&
                                    this.getY()+40>=enemyTank.getY()&&this.getY()+40<=enemyTank.getY()+60)
                            {
                                return true;
                            }
                        }
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3)
                        {
                            if(this.getX()+60>=enemyTank.getX()&&this.getX()+60<=enemyTank.getX()+60&&
                                    this.getY()>=enemyTank.getY()&&this.getY()<=enemyTank.getY()+40)
                            {
                                return true;
                            }
                            if(this.getX()+60>=enemyTank.getX()&&this.getX()+60<=enemyTank.getX()+60&&
                                    this.getY()+40>=enemyTank.getY()&&this.getY()+40<=enemyTank.getY()+40)
                            {
                                return true;
                            }
                        }

                    }
                }
                break;
            case 2://下
                //让当前敌人坦克与其他所有敌人坦克比较
                for(int i=0;i<enemyTanks.size();i++)
                {
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(enemyTank!=this)
                    {
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2)
                        {
                            if(this.getX()>=enemyTank.getX()&&this.getX()<=enemyTank.getX()+40&&
                                    this.getY()+60>=enemyTank.getY()&&this.getY()+60<=enemyTank.getY()+60)
                            {
                                return true;
                            }
                            if(this.getX()+40>=enemyTank.getX()&&this.getX()+40<=enemyTank.getX()+40&&
                                    this.getY()+60>=enemyTank.getY()&&this.getY()+60<=enemyTank.getY()+60)
                            {
                                return true;
                            }
                        }
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3)
                        {
                            if(this.getX()>=enemyTank.getX()&&this.getX()<=enemyTank.getX()+60&&
                                    this.getY()+60>=enemyTank.getY()&&this.getY()+60<=enemyTank.getY()+40)
                            {
                                return true;
                            }
                            if(this.getX()+40>=enemyTank.getX()&&this.getX()+40<=enemyTank.getX()+60&&
                                    this.getY()+60>=enemyTank.getY()&&this.getY()+60<=enemyTank.getY()+40)
                            {
                                return true;
                            }
                        }

                    }
                }
                break;
            case 3:
                //让当前敌人坦克与其他所有敌人坦克比较
                for(int i=0;i<enemyTanks.size();i++)
                {
                    EnemyTank enemyTank=enemyTanks.get(i);
                    if(enemyTank!=this)
                    {
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2)
                        {
                            if(this.getX()>=enemyTank.getX()&&this.getX()<=enemyTank.getX()+40&&
                                    this.getY()>=enemyTank.getY()&&this.getY()<=enemyTank.getY()+60)
                            {
                                return true;
                            }
                            if(this.getX()>=enemyTank.getX()&&this.getX()<=enemyTank.getX()+40&&
                                    this.getY()+40>=enemyTank.getY()&&this.getY()+40<=enemyTank.getY()+60)
                            {
                                return true;
                            }
                        }
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3)
                        {
                            if(this.getX()>=enemyTank.getX()&&this.getX()<=enemyTank.getX()+60&&
                                    this.getY()>=enemyTank.getY()&&this.getY()<=enemyTank.getY()+40)
                            {
                                return true;
                            }
                            if(this.getX()>=enemyTank.getX()&&this.getX()<=enemyTank.getX()+60&&
                                    this.getY()+40>=enemyTank.getY()&&this.getY()+40<=enemyTank.getY()+40)
                            {
                                return true;
                            }
                        }

                    }
                }
                break;

        }
        return false;
    }
    boolean isLive=true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while(true)
        {
            if(isLive&&shots.size()==0)
            {
                Shot s=null;
                switch(getDirect())
                {
                    case 0:
                        s=new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s=new Shot(getX()+60,getY()+20,0);
                        break;
                    case 2:
                        s=new Shot(getX()+20,getY()+60,0);
                        break;
                    case 3:
                        s=new Shot(getX(),getY()+20,0);
                        break;
                }
                shots.add(s);
                new Thread(s).start();

            }
            //根据坦克方向来继续移动
            switch(getDirect())
            {
                case 0:
                    //让坦克保持一个方向多走几步
                    for(int i=0;i<30;i++)
                    {
                        if(getY()>0&&!isTouchEnemyTank())
                        {
                            moveUp();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 1:

                    for(int i=0;i<30;i++)
                    {
                        if(getX()+60<1000&&!isTouchEnemyTank())
                        {
                            moveRight();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 2:

                    for(int i=0;i<30;i++)
                    {
                        if(getY()+60<750&&!isTouchEnemyTank())
                        {
                            moveDown();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 3:
                    for(int i=0;i<30;i++)
                    {
                        if(getX()>0&&!isTouchEnemyTank())
                        {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
            }

            setDirect((int)(Math.random()*4));
            //当写多线程并发程序时，一定要自己设定好什么时候结束线程
            if(!isLive)
            {
                break;//退出线程
            }
        }
    }
}
