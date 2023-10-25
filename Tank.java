package TankGame;

public class Tank {
    private int x;//坦克横坐标
    private int y;
    private int direct=2;
    private int speed=5;
    boolean isLive=true;
    //四个方向的移动
    public void moveUp()
    {
        y-=speed;
    }
    public void moveDown()
    {
        y+=speed;
    }
    public void moveLeft()
    {
        x-=speed;
    }
    public void moveRight()
    {
        x+=speed;
    }
    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getDirect() {
        return direct;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
