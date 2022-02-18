package lock;


import com.night.RedisApplication;
import com.night.lock.service.LockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisLockTest {
    @Autowired
    private LockService lockService;

    @Test
    public void rLockTest(){
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            pool.execute(() -> {
                lockService.rLock(1);
            });
        }

        while (!pool.isTerminated()){

        }
    }


    @Test
    public void nxLockTest(){
//        lockService.setNx(1);
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            pool.execute(() -> {
                lockService.setNx(1);
            });
        }

        while (!pool.isTerminated()){

        }
    }
}
