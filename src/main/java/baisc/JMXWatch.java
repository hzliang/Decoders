package baisc;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author huzuoliang
 * @Title: JMXWatch
 * @ProjectName java_basic
 * @Description: TODO
 * @date 2018/11/619:32
 */
public class JMXWatch {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        for (ThreadInfo t : threadInfos) {
            System.out.println(t.getThreadId() + ":" + t.getThreadName());
        }

        Thread.yield();
    }
}
