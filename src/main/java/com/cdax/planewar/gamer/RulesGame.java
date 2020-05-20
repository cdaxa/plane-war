package com.cdax.planewar.gamer;

import com.cdax.planewar.po.*;
import com.cdax.planewar.po.iface.Award;
import com.cdax.planewar.po.iface.Enemy;
import com.cdax.planewar.po.iface.Game;
import com.cdax.planewar.resource.Resources;
import com.cdax.planewar.ui.PlaneWarGameFrame;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.cdax.planewar.po.StateEnum.*;

/**
 * @author cdax
 * @Version 1.0
 * @create 2020-05-20 23:37
 */


public class RulesGame implements Game {
    // 游戏窗口
    private PlaneWarGameFrame planeWarGameFrame;

    // 游戏状态值
    public static StateEnum STATE = START;

    private int createBullTimer = 0;
    private int createEnemyOrBeeTimer = 0;

    // 游戏资源
    private ArrayList<Role> shootObjects = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private Hero hero;
    private int score = 0;

    public ArrayList<Role> getShootObjects() {
        return shootObjects;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public Hero getHero() {
        return hero;
    }

    public int getScore() {
        return score;
    }




    public RulesGame() {
        try {
            //  加载资源
            loadConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        hero = new Hero();
        planeWarGameFrame = new PlaneWarGameFrame(this);
    }


    /**
     * 游戏引擎：每10毫秒执行一次，重绘。因为10毫秒重绘，肉眼看起来就像动画一样
     * 1. 生成对象
     * 2. 对象飞一步（动态就是在走动了）
     * 3. 删除超过边界的元素
     * 4. 重绘
     */
    public void start() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (STATE == RUNNING) {
                    createAction();
                    stepAction();
                    shootAction();
                    bangAction();
                    outOfBoundsAction();
                    checkGameOverAction();
                }

                // repaint会调用paint方法重绘图像
                planeWarGameFrame.repaint();
            }
        }, 1000, 10);

    }

    /**
     * 每隔40毫秒生成
     */
    private void createAction() {
        if (createEnemyOrBeeTimer++ % 40 == 0) {
            Role role = nextOne();
            shootObjects.add(role);
        }
    }

    /**
     * 移动：英雄机、子弹、敌机、小蜜蜂
     */
    public void stepAction() {
        for (Role shootObj : shootObjects) {
            shootObj.step();
        }

        for (Bullet bullet : bullets) {
            bullet.step();
        }
        hero.step();
    }

    /**
     * 射击
     */
    public void shootAction() {
        createBullTimer++;
        if (createBullTimer % 30 == 0) { // 30毫秒发一颗子弹
            ArrayList<Bullet> shootBullets = hero.shoot();
            bullets.addAll(shootBullets);
        }
    }

    /**
     * 检查每颗子弹是否击中目标
     */
    public void bangAction() {
        for (Bullet bullet : bullets) {
            bang(bullet);
        }
    }

    /**
     * 删除越界飞行物及子弹
     */
    public void outOfBoundsAction() {
        outOfBoundsRole();
        outOfBoundsBullet();
    }


    /**
     * 检查游戏结束
     */
    public void checkGameOverAction() {
        if (isGameOver()) {
            STATE = OVER; // 改变状态
        }
    }

    /**
     * 初始化游戏状态
     */
    public void initEnvironment() {
        shootObjects.clear();
        shootObjects.clear();
        bullets.clear();
        hero = new Hero();
        score = 0;
        STATE = START;
    }

    /**
     * 检查游戏是否结束
     */
    public boolean isGameOver() {
        ArrayList<Role> hadHitRoles = new ArrayList<>();

        for (Role role : shootObjects) {
            if (hero.hit(role)) {
                hero.subtractLife();
                hero.setDoubleFire(0);
                hadHitRoles.add(role);
            }
        }
        shootObjects.removeAll(hadHitRoles);
        return hero.getLife() <= 0;
    }

    private void outOfBoundsBullet() {
        Iterator<Bullet> iteratorBullet = bullets.iterator();
        while (iteratorBullet.hasNext()) {
            Bullet bullet = iteratorBullet.next();
            if (bullet.outOfBounds())
                iteratorBullet.remove();   //注意这个地方
        }
    }

    private void outOfBoundsRole() {
        Iterator<Role> iteratorRole = shootObjects.iterator();
        while (iteratorRole.hasNext()) {
            Role shootObj = iteratorRole.next();
            if (shootObj.outOfBounds())
                iteratorRole.remove();   //注意这个地方
        }
    }


    /**
     * 子弹和飞行物之间的碰撞检查
     */
    public void bang(Bullet bullet) {
        ArrayList<Role> hadShootRoles = new ArrayList<>();
        for (Role role : shootObjects) {
            if (role.shootBy(bullet)) { // 判断是否击中
                checkHadShootRoleType(role); // 记录分数、记录生命
                hadShootRoles.add(role);
                break;
            }
        }
        shootObjects.removeAll(hadShootRoles);
    }

    /**
     * 根据设置的类型进行分数计算、加命、加大火力操作
     *
     * @param role
     */
    private void checkHadShootRoleType(Role role) {
        if (role instanceof Enemy) {
            Enemy e = (Enemy) role;
            score += e.getScore();
        } else if (role instanceof Award) {
            Award a = (Award) role;
            int type = a.getType();
            switch (type) {
                case Award.DOUBLE_FIRE:
                    hero.addDoubleFire();
                    break;
                case Award.LIFE:
                    hero.addLife();
                    break;
            }
        }
    }

    /**
     * 随机生成飞行物
     *
     * @return 飞行物对象
     */
    private Role nextOne() {
        int type = (int) (21 * Math.random());
        return type == 0 ? new Bee() : new AirPlan();
    }


    /**
     * 加载资源
     */
    private void loadConfiguration() throws IOException {
        Properties properties = new Properties();
        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        properties.load(resourceStream);

        String imgsDir = properties.getProperty("imgsDir");
        Resources resources = new Resources();
        resources.load(imgsDir);

    }
}
