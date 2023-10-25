package TankGame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame01 extends JFrame {
    //定义一个MyPanel
    MyPanel mp=null;
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {

        TankGame01 tankGame01 = new TankGame01();

    }
    public TankGame01()
    {
        System.out.println("请输入选择：1：新游戏 2：继续上局");
        String key=scanner.next();
        mp=new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1300,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //监听结束按钮的方法
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }


}
