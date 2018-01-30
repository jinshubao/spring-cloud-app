package test;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ConcurrentTest {

    @Test
    public void CountDownLatchTest() {
        int threadNum = 4;
        long sleep = 3000L;
        final CountDownLatch latch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(() -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("子线程" + threadName + "正在执行");
                    Thread.sleep(sleep);
                    System.out.println("子线程" + threadName + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.setDaemon(true);
            t.start();
        }

        try {
            System.out.println("等待子线程执行...");
            latch.await();
            System.out.println("子线程已经执行完毕，主线程继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CyclicBarrierTest() throws InterruptedException {

        int threadNum = 10;
        long sleep = 3000L;
        CyclicBarrier barrier = new CyclicBarrier(threadNum);

        Runnable runnable =() -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println("线程" + threadName + "正在执行耗时操作");
                Thread.sleep(sleep);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + threadName + "执行完毕");
                barrier.await();
                System.out.println("所有线程执行完毕，继续处理其他任务...");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            t.start();
        }
        //主线程不能退出
        Thread.sleep(2 * sleep);

        System.out.println("CyclicBarrier重用");

        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            t.start();
        }
        //主线程不能退出
        Thread.sleep(2 * sleep);
    }

    @Test
    public void SemaphoreTest() throws InterruptedException {
        int threadNum = 8;
        long sleep = 3000L;
        int workerNum = 5;
        Semaphore semaphore = new Semaphore(workerNum);
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("线程" + Thread.currentThread().getName() + "正在执行耗时操作");
                    Thread.sleep(sleep);
                    System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.setDaemon(true);
            t.start();
        }
        //主线程不能退出

        int times = threadNum / workerNum + ((threadNum % workerNum) > 0 ? 1 : 0);

        Thread.sleep((times + 1) * sleep);
    }
}
