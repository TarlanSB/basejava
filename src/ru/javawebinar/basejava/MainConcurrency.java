package ru.javawebinar.basejava;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;


public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();

    //    private static final Object LOCK = new Object();
//    private static final Lock lock = new ReentrantLock();
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();
    private static final ThreadLocal<SimpleDateFormat> threadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"));

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                // throw new IllegalStateException();
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
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);
//
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {

            Future<Integer> future = executorService.submit(() ->
//            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
//            thread.start();
//            threads.add(thread);
        }

/*
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
*/
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        System.out.println(mainConcurrency.counter);
        System.out.println(mainConcurrency.atomicCounter.get());

        System.out.println("\nDEADLOCK");
//       deadlock(LOCK1, LOCK2);
//       deadlock(LOCK2, LOCK1);
    }

    private void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
//        WRITE_LOCK.lock();
//        try {
        atomicCounter.incrementAndGet();
//            counter++;
//        } finally {
//            WRITE_LOCK.unlock();
//        }
//                wait();
//                readFile
//                ...
//        }

        int[] values = new int[]{5, 7, 3, 1, 2, 3, 3, 2, 3};
        System.out.println("Результат метода int minValue(int[] values) через стрим " + minValue(values));

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);

        System.out.println("Метод oddOrEven " + oddOrEven(integers));
        System.out.println("Метод oddOrEven в одну строку " + oddOrEven2(integers));
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

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        if (sum % 2 == 0) {
            integers = integers.stream().filter(num -> num % 2 != 0).collect(Collectors.toList());
        } else {
            integers = integers.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        }
        return integers;
    }

    public static List<Integer> oddOrEven2(List<Integer> values) {
        return values.stream()
                .collect(collectingAndThen(
                        Collectors.groupingBy(i -> i % 2),
                        map -> map.getOrDefault(
                                (map.getOrDefault(1, List.of()).size() + 1)
                                        % 2,
                                List.of())));
    }
}