package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);

        System.out.println("\nDEADLOCK");
        deadlock(LOCK1, LOCK2);
        deadlock(LOCK2, LOCK1);
    }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
        counter++;
//                wait();
//                readFile
//                ...
//        }
    }

    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    private static void deadlock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Попытка захватить монитор объекта " + lock1);
            synchronized (lock1) {
                System.out.println("Монитор объекта " + lock1 + " захвачен");
                System.out.println("Попытка захватить монитор объекта " + lock2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Мониторы объектов " + lock1 + " и " + lock2 + " захвачены");
                }
                System.out.println("Монитор объектов " + lock2 + " отпущен, отпускаем " + lock1 + "\n" +
                        "------------------------------------------------------------");
            }
        }).start();
    }
}