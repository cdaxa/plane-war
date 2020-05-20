package com.cdax.planewar.po.iface;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 15:36
 */


public interface Award {
    int DOUBLE_FIRE = 0;
    int LIFE = 1;

    /**
     * 获得奖励类型(上面的0或1)
     */
    int getType();
}
