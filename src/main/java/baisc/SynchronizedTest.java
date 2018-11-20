package baisc;

/**
 * @author huzuoliang
 * @Title: SynchronizedTest
 * @ProjectName java_basic
 * @Description: TODO
 * @date 2018/11/721:06
 */
public class SynchronizedTest {
    public static synchronized void m() {

    }

    public static void main(String[] args) {
        synchronized(SynchronizedTest.class) {

        }

        m();
    }
}
