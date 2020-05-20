package com.cdax.planewar.po;

import com.cdax.planewar.po.iface.Award;
import com.cdax.planewar.resource.Resources;

import java.util.Random;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 15:30
 */


public class Bee extends Role implements Award {
    private int xSpeed = 1;
    private int ySpeed = 2;
    private int awardType;

    public Bee() {
        this.bfImg = Resources.bee;
        width = bfImg.getWidth();
        height = bfImg.getHeight();
        y = -height;
        Random rand = new Random();
        x = rand.nextInt(Resources.WIDTH - width);
        awardType = rand.nextInt(2);
    }

    /**
     * 获得奖励类型
     */
    public int getType() {
        return awardType;
    }

    /**
     * 越界处理
     */
    @Override
    public boolean outOfBounds() {
        return y > Resources.HEIGHT;
    }

    /**
     * 移动，可斜着飞
     */
    @Override
    public void step() {
        x += xSpeed;
        y += ySpeed;
        if (x > Resources.WIDTH - width) {
            xSpeed = -1;
        }
        if (x < 0) {
            xSpeed = 1;
        }
    }


}
