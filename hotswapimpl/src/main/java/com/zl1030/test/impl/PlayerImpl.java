package com.zl1030.test.impl;

import com.zl1030.test.api.IPlayer;

import java.util.logging.Logger;

/**
 * @Author: zl1030
 * @Date: 2018/4/11
 */
public class PlayerImpl implements IPlayer {

    private String a = "#######";

    private static Logger logger = Logger.getLogger(PlayerImpl.class.getSimpleName());

    public void hello() {
        logger.info("hello~~!!!");
    }

    public void init() {
        logger.info("init44444" + a);
    }

    public void destroy() {
        logger.info("destroy444444" + a);
    }

    public static int take() {
        return (int) (System.currentTimeMillis() / 100);
    }
}
