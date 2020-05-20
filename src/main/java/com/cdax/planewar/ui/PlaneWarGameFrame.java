package com.cdax.planewar.ui;

import com.cdax.planewar.gamer.RulesGame;
import com.cdax.planewar.event.MouseHandler;
import com.cdax.planewar.po.*;
import com.cdax.planewar.resource.Resources;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.util.*;

import static com.cdax.planewar.gamer.RulesGame.STATE;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-20 00:44
 */

public class PlaneWarGameFrame extends JPanel {
    // 分数值与生命值坐标
    private static final int SCORE_X = 10;
    private static final int SCORE_Y = 25;
    private static final int LIFE_X = 10;
    private static final int LIFE_Y = 45;

    // 游戏资源
    private RulesGame rulesGame;
    private ArrayList<Role> shootObjects;
    private ArrayList<Bullet> bullets;
    private Hero hero;

    public PlaneWarGameFrame(RulesGame rulesGame) {
        this.rulesGame = rulesGame;
        this.shootObjects = rulesGame.getShootObjects();
        this.hero = rulesGame.getHero();
        this.bullets = rulesGame.getBullets();

        JFrame jFrame = new JFrame("飞机大战");
        jFrame.add(this);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(Resources.WIDTH, Resources.HEIGHT);
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        MouseHandler mouseHandler = new MouseHandler(rulesGame);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }


    /**
     * repaint方法会调用paint方法，
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintBackground(g);
        paintHero(g);
        paintShootObjects(g);
        paintBullets(g);
        paintScore(g);
        paintState(g);
    }

    /**
     * 画游戏状态
     */
    public void paintState(Graphics g) {
        switch (STATE) {
            case START:
                g.drawImage(Resources.start, 0, 0, null);
                break;
            case PAUSE:
                g.drawImage(Resources.pause, 0, 0, null);
                break;
            case OVER:
                g.drawImage(Resources.gameover, 0, 0, null);
                break;
            case RUNNING:
                break;
        }
    }

    /**
     * 画分数
     */
    public void paintScore(Graphics g) {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g.setColor(new Color(0x3A3B3B));
        g.setFont(font);
        g.drawString("SCORE:" + rulesGame.getScore(), SCORE_X, SCORE_Y);
        g.drawString("LIFE:" + hero.getLife(), LIFE_X, LIFE_Y);
    }

    /**
     * 画子弹
     */
    public void paintBullets(Graphics g) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet b = iterator.next();
            g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2,
                    b.getY(), null);
        }
    }


    private void paintHero(Graphics g) {
        g.drawImage(hero.getImage(), hero.getX(),
                hero.getY(), null);
    }

    private void paintBackground(Graphics g) {
        g.drawImage(Resources.background,
                0, 0, null);
    }

    /**
     * 画飞行物
     */
    private void paintShootObjects(Graphics g) {
        Iterator<Role> iterator = shootObjects.iterator();
        while (iterator.hasNext()) {
            Role shootObj = iterator.next();
            g.drawImage(shootObj.getImage(), shootObj.getX(),
                    shootObj.getY(), null);
        }
    }
}
