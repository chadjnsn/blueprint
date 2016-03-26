package xyz.chadjohnson.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * A service to demonstrate how a hystrix command can be setup and used.
 *
 * @author Chad Johnson
 */
@Service("demoHystrixService")
public class DemoHystrixServiceImpl implements DemoHystrixService {

    /***
     * This is a method that has a 50% chance of failing and throwing a {@link RuntimeException}. When it does,
     * hystrix will automatically fallback to the specified fallback method.
     */
    @HystrixCommand(fallbackMethod = "getBackupInfo")
    @Override
    public String getInfoThatMightFail() {
        if (new Random().nextBoolean())
            return "Neat, it didn't fail!";
        else
            throw new RuntimeException("Oh no!");
    }

    @Override
    public String getBackupInfo() {
        return "Oh no! The main method failed!";
    }
}
