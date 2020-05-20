package com.cdax.planewar.resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 15:35
 */


public class Resources {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 654;

    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage airplane;
    public static BufferedImage bee;
    public static BufferedImage bullet;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage pause;
    public static BufferedImage gameover;

    /**
     * 加载资源
     * @param resourceDir
     */
    public void load(String resourceDir) {
        try {
            background = ImageIO.read(this.getClass().getClassLoader().
                    getResourceAsStream(resourceDir + "background.png"));
            start = ImageIO.read(this.getClass().getClassLoader().
                    getResourceAsStream(resourceDir + "start.png"));
            airplane = ImageIO.read(this.getClass().getClassLoader().
                    getResourceAsStream(resourceDir + "airplane.png"));
            bee = ImageIO.read(this.getClass().getClassLoader().
                    getResourceAsStream(resourceDir + "bee.png"));
            bullet = ImageIO.read(this.getClass().getClassLoader().
                    getResourceAsStream(resourceDir + "bullet.png"));
            hero0 = ImageIO.read(this.getClass().getClassLoader().
                    getResourceAsStream(resourceDir + "hero0.png"));
            hero1 = ImageIO.read(this.getClass().getClassLoader().
                    getResourceAsStream(resourceDir + "hero1.png"));
            pause = ImageIO.read(this.getClass().getClassLoader().
                    getResourceAsStream(resourceDir + "pause.png"));
            gameover = ImageIO.read(this.getClass().getClassLoader().
                    getResourceAsStream(resourceDir + "gameover.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
