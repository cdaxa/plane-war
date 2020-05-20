package com.cdax.planewar.po;

import java.awt.image.BufferedImage;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 15:27
 */


public abstract class Role {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected BufferedImage bfImg;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getImage() {
        return bfImg;
    }

    public void setImage(BufferedImage image) {
        this.bfImg = image;
    }


    /**
     * 检查是否出界
     *
     * @return true 出界与否
     */
    public abstract boolean outOfBounds();

    /**
     * 飞行物移动一步
     */
    public abstract void step();

    /**
     * 是否被击中
     *
     * @param bullet
     * @return
     */
    public boolean shootBy(Bullet bullet) {
        boolean flag = false;
        int bulletX = bullet.getX();
        int bulletY = bullet.getY();


        if (this.x < bulletX && bulletX < (this.x + this.width)
                && this.y < bulletY && bulletY < (this.y + this.height)) {
            flag = true;
        }
        return flag;
    }
}
