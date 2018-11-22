package baisc;

import java.util.concurrent.locks.LockSupport;

/**
 * @author huzuoliang
 * @Title: LockSupportTest
 * @ProjectName decoder
 * @Description: TODO
 * @date 2018/11/2210:10
 */
public class LockSupportTest {


    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        LockSupport.park();// 获取许可
        LockSupport.park();// 获取许可
        LockSupport.unpark(thread);//释放许可
        LockSupport.unpark(thread);//释放许可
        System.out.println("b");
    }
}
