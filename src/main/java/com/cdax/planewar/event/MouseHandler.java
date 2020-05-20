package com.cdax.planewar.event;

import com.cdax.planewar.gamer.RulesGame;
import com.cdax.planewar.po.Hero;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static com.cdax.planewar.gamer.RulesGame.STATE;
import static com.cdax.planewar.po.StateEnum.*;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-20 22:10
 */


public class MouseHandler extends MouseAdapter implements MouseMotionListener {
    private RulesGame rulesGame;
    private Hero hero;

    public MouseHandler(RulesGame rulesGame) {
        this.rulesGame = rulesGame;
        this.hero = rulesGame.getHero();

    }

    /**
     * 鼠标移动
     *
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (STATE == RUNNING) {
            int x = e.getX();
            int y = e.getY();
            hero.moveTo(x, y);
        }
    }

    /**
     * 鼠标进入窗口
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (STATE == PAUSE) {
            STATE = RUNNING;
        }
    }

    /**
     * 鼠标移出窗口
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (STATE != OVER && STATE != START) {
            STATE = PAUSE;
        }
    }

    /**
     * 鼠标点击
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (STATE) {
            case START:
                STATE = RUNNING;
                break;
            case OVER:
                rulesGame.initEnvironment();
                break;
        }
    }
}
