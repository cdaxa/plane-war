package com.cdax.planewar.factory;

import com.cdax.planewar.po.iface.Game;

/**
 *
 * 抽象工厂类：创建不同的游戏的厂
 *
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 22:58
 */


public abstract class AbstractFactory {
    public abstract Game creatGame();
}
