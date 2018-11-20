package baisc;

/**
 * @author huzuoliang
 * @Title: ThreadDaemon
 * @ProjectName java_basic
 * @Description: TODO
 * @date 2018/11/622:28
 */

public class ThreadDaemon {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Thread2());
        thread1.setDaemon(true);
        thread1.start();
        thread1.interrupt();
        Thread.sleep(10000);
        System.out.println("用户线程退出");
    }
}

class Thread2 implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("1+1=" + (1 + 1));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}