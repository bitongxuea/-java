package TankGame;

import java.io.*;
import java.util.Vector;

//该类用于记录相关信息，和文件交互
public class Recorder {
    private static int allEnemyTankNmu=0;
    //定义io对象
    private static FileWriter fw=null;
    private static BufferedReader br=null;
    private static BufferedWriter bw=null;
    private static String recordFile="src\\TankGame\\myRecord.txt";
     static Vector<EnemyTank> enemyTanks=null;

    public static void  setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //定义一个Vector的Vector，用来保存信息
    private static Vector<Node> nodes=new Vector<>();

    public static Vector<Node> getNodesAndEnemyTanks()
    {
        try {
            br=new BufferedReader(new FileReader(recordFile));

            allEnemyTankNmu=Integer.parseInt(br.readLine());
            //循环读取文件生成node集合
            String line="";
            while((line=br.readLine())!=null)
            {
                String[] xyd=line.split(" ");
                Node node=new Node(Integer.parseInt(xyd[0]),Integer.parseInt(xyd[1]),Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(br!=null)
            {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }
    public static int getAllEnemyTankNmu() {
        return allEnemyTankNmu;
    }

    public static void setAllEnemyTankNmu(int allEnemyTankNmu) {
        Recorder.allEnemyTankNmu = allEnemyTankNmu;
    }
    //当我方坦克击毁一个坦克时，就应当加
    public static void addAllEnemyTank()
    {
        Recorder.allEnemyTankNmu++;
    }
    //增加一个方法，当游戏退出时，将数据保存到文件中
    public static void keepRecord()
    {
        try {
            bw= new BufferedWriter(new FileWriter(recordFile));
            //保存数量
            bw.write(allEnemyTankNmu+"\r\n");
            //遍历敌人坦克的Vector，根据情况保存
            //定义一个属性，通过setXXXX方法得到Vector
            for(int i=0;i<enemyTanks.size();i++)
            {
                EnemyTank enemyTank=enemyTanks.get(i);
                if(enemyTank.isLive==true)
                {
                    //保存该信息
                    String record=enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect();
                    bw.write(record+"\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(bw!=null)
            {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
