package com.cdax.planewar;

import com.cdax.planewar.factory.PlanWarGameFactory;
import com.cdax.planewar.po.iface.Game;

/**
 *  游戏程序入口
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 22:52
 */

public class MainApp {
    public static void main(String[] args) {
        PlanWarGameFactory planWarGameFactory = new PlanWarGameFactory();
        Game game = planWarGameFactory.creatGame();
        game.start();
    }
}
