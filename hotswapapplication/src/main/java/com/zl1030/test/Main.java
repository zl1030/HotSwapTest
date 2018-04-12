package com.zl1030.test;

import com.zl1030.test.api.IPlayer;
import com.zl1030.test.classloader.HotSwapURLClassLoader;
import com.zl1030.test.impl.PlayerImpl;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zl1030
 * @Date: 2018/4/12
 */
public class Main {
    public static void main(String[] args) {

        IPlayer player1 = null;
//        IPlayer player2 = null;

        try {
            HotSwapURLClassLoader classLoader = new HotSwapURLClassLoader(new URL[0]);
            classLoader.addJarFile(System.getProperty("user.dir") + File.separator + "hotswap-impl-1.0-SNAPSHOT.jar");
            Class<IPlayer> clazz = (Class<IPlayer>) classLoader.loadClass("com.zl1030.test.impl.PlayerImpl");

            player1 = clazz.newInstance();
//            player2 = new PlayerImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                System.out.println("Player1:");
                player1.hello();
//                System.out.println("player2:" + player2.getClass().getClassLoader().toString());

                HotSwapURLClassLoader classLoader2 = new HotSwapURLClassLoader(new URL[0]);
                classLoader2.addJarFile(System.getProperty("user.dir") + File.separator + "hotswap-impl-1.0-SNAPSHOT.jar");

                Class<IPlayer> clazz = (Class<IPlayer>) classLoader2.loadClass("com.zl1030.test.impl.PlayerImpl");

                IPlayer player = clazz.newInstance();
                player.init();

                player.hello();

                Thread.sleep(TimeUnit.SECONDS.toMillis(2));

                player.destroy();

                ClassLoader c = player.getClass().getClassLoader();
                if (c instanceof URLClassLoader) ((URLClassLoader) c).close();

                Thread.sleep(TimeUnit.SECONDS.toMillis(5));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
