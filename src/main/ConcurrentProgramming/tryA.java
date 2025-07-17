package ConcurrentProgramming;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class tryA {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static int counter = 0;

    public static void main(String[] args) {
        var a = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            a.add(i);
        }
        System.out.println(a);
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
//        Thread writerThread = new Thread(() -> {
//            while (true) {
//                lock.writeLock().lock();
//                try {
//                    counter++;
//                    System.out.println("Writer Thread: Counter incremented to " + counter);
//                } finally {
//                    lock.writeLock().unlock();
//                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        Thread readerThread = new Thread(() -> {
//            while (true) {
//                lock.readLock().lock();
//                try {
//                    System.out.println("Reader Thread: Counter is " + counter);
//                } finally {
//                    lock.readLock().unlock();
//                }
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        writerThread.start();
//        readerThread.start();
    }
}
