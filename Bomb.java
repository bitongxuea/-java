package TankGame;
//炸弹
public class Bomb {
    int x,y;
    int life=9;//炸弹生命周期
    boolean isLive=true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少生命值
    public void lifeDown()//配合出现爆炸的效果
    {
        if(life>0)
        {
            life--;
        }
        else
        {
            isLive=false;
        }
    }
}
