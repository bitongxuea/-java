package TankGame;

public class Shot implements Runnable {
     public int x;
    int y;
    int direct=0;
    int speed=10;
    boolean isLive=true;//子弹是否还存活
    //构造器


    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击行为
        while(true)
        {
            //子弹休眠
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct)
            {
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }
            if(!(x>=0&&x<=1000&&y>=0&&y<=750))
            {
                isLive=false;
            }
        }
    }
}
