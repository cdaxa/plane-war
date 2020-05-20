package com.cdax.planewar.po;

import com.cdax.planewar.po.iface.Enemy;
import com.cdax.planewar.resource.Resources;

import java.util.Random;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 15:33
 */


public class AirPlan extends Role implements Enemy {
    private int speed = 3;

    /**
     * 初始化数据
     */
    public AirPlan() {
        Random rand = new Random();
        this.bfImg = Resources.airplane;
        this.width = bfImg.getWidth();
        this.height = bfImg.getHeight();
        this.y = -height;
        this.x = rand.nextInt(Resources.WIDTH - width);
    }

    /**
     * 获取分数
     */
    public int getScore() {
        return 5;
    }

    /**
     * //越界处理
     */
    @Override
    public boolean outOfBounds() {
        return y > Resources.HEIGHT;
    }

    /**
     * 移动
     */
    @Override
    public void step() {
        y += speed;
    }

}

