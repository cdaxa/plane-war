package com.cdax.planewar.factory;

import com.cdax.planewar.gamer.RulesGame;
import com.cdax.planewar.po.iface.Game;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-19 23:01
 */


public class PlanWarGameFactory extends AbstractFactory {
    public Game creatGame() {
        return new RulesGame();
    }
}
