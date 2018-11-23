package baisc.locktest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author huzuoliang
 * @Title: TwinsLock
 * @ProjectName decoder
 * @Description: TODO
 * @date 2018/11/2217:05
 */
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 10-10
 */
import java.util.concurrent.locks.Lock;

import baisc.SleepUtils;

/**
 * 10-11
 */
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(1);

    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -7889272986162341211L;

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }

        @Override
        public int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                System.out.println("apply " + Thread.currentThread().getName());
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    System.out.println("approve " + Thread.currentThread().getName() + " and current: " + current + " new count: " + newCount);
                    return newCount;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                System.out.println("apply release " + Thread.currentThread().getName());
                if (compareAndSetState(current, newCount)) {
                    System.out.println("approve release " + Thread.currentThread().getName());
                    return true;
                }
            }
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

    public void lock() {
        sync.acquireShared(1);
    }

    public void unlock() {
        sync.releaseShared(1);
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
