package com.cdax.planewar.po;


import com.cdax.planewar.resource.Resources;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 15:37
 */


public class Bullet extends Role {
    private int speed = 3;

    /**
     * 初始化数据
     */
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.bfImg = Resources.bullet;
    }


    /**
     * 移动
     */
    @Override
    public void step() {
        y -= speed;
    }

    /**
     * 越界处理
     */
    @Override
    public boolean outOfBounds() {
        return y < -height;
    }
}
