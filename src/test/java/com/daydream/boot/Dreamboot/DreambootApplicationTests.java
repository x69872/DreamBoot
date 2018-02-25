package com.daydream.boot.Dreamboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import jersey.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DreambootApplicationTests
{
    private static int count=0;
    protected final Logger logger = LogManager.getLogger(DreambootApplicationTests.class.getName());
    protected final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void contextLoads()
    {
        MyTask myTask = new MyTask();
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%02d").build();
        ExecutorService executorService = new ThreadPoolExecutor(18, 20,
                3L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            executorService.execute(myTask);

        }

        executorService.shutdown();
        System.out.println(count);
    }
    class MyTask implements Runnable {

        @Override
        public void run() {

            System.out.println(System.currentTimeMillis() + ":Thread name:"
                    + Thread.currentThread().getName()) ;
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void threadTools()
    {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();


    }
    public class TimerTaskThread extends Thread {
        public TimerTaskThread(){
            super.setName("TimerTaskThread");
        }

    }
}