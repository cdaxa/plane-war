package com.cdax.planewar.po;

import com.cdax.planewar.resource.Resources;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 15:38
 */


public class Hero extends Role {
    private BufferedImage[] images = {};
    private int index = 0;

    private int doubleFire;
    private int life;

    /**
     * 初始化数据
     */
    public Hero() {
        life = 3;
        doubleFire = 0;
        images = new BufferedImage[]{Resources.hero0, Resources.hero1};
        bfImg = Resources.hero0;
        width = bfImg.getWidth();
        height = bfImg.getHeight();
        x = 150;
        y = 400;

    }


    /**
     * 设置双倍火力
     */
    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }

    /**
     * 增加火力
     */
    public void addDoubleFire() {
        doubleFire = 40;
    }

    /**
     * 增命
     */
    public void addLife() {  //增命
        life++;
    }

    /**
     * 减命
     */
    public void subtractLife() {   //减命
        life--;
    }

    /**
     * 获取命
     */
    public int getLife() {
        return life;
    }

    /**
     * 当前物体移动了一下，相对距离，x,y鼠标位置
     */
    public void moveTo(int x, int y) {
        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    /**
     * 越界处理
     */
    @Override
    public boolean outOfBounds() {
        return false;
    }

    /**
     * 发射子弹
     */
    public ArrayList<Bullet> shoot() {
        ArrayList<Bullet> bullets = new ArrayList<>();

        int xStep = width / 4;
        int yStep = 20;
        if (doubleFire > 0) {
            bullets.add(new Bullet(x + xStep, y - yStep));
            bullets.add(new Bullet(x + 3 * xStep, y - yStep));
            return bullets;
        } else {
            bullets.add(new Bullet(x + 2 * xStep, y - yStep));
            return bullets;
        }
    }

    /**
     * 移动
     */
    @Override
    public void step() {
        if (images.length > 0) {
            bfImg = images[index++ / 10 % images.length];
        }
    }

    /**
     * 英雄机碰撞
     */
    public boolean hit(Role other) {
        int x1 = other.x - this.width / 2;
        int x2 = other.x + this.width / 2 + other.width;
        int y1 = other.y - this.height / 2;
        int y2 = other.y + this.height / 2 + other.height;

        int herox = this.x + this.width / 2;
        int heroy = this.y + this.height / 2;

        return herox > x1 && herox < x2 && heroy > y1 && heroy < y2;
    }
}
