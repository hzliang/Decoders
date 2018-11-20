package baisc;

import java.util.concurrent.TimeUnit;

/**
 * @author huzuoliang
 * @Title: ShutDown
 * @ProjectName java_basic
 * @Description: TODO
 * @date 2018/11/623:53
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ThrowExceptionInterrupt one = new ThrowExceptionInterrupt();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();

//        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();

//        TimeUnit.SECONDS.sleep(1);

        two.cancel();
    }

    private static class Runner implements Runnable {
        private long i;
        private boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }

            System.out.println("Count i= " + i);
        }

        public void cancel() {
            on = false;
        }
    }

    private static class ThrowExceptionInterrupt implements Runnable {
        private long i;
        private boolean on = true;

        @Override
        public void run() {

            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void cancel() {
            on = false;
        }
    }
}
