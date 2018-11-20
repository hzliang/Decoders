package baisc;

import java.util.concurrent.TimeUnit;

/**
 * @author huzuoliang
 * @Title: SleepUtils
 * @ProjectName java_basic
 * @Description: TODO
 * @date 2018/11/620:06
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {

        }
    }
}
