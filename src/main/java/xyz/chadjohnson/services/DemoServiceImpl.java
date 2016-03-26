package xyz.chadjohnson.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * A service to demonstrate some of the included/enabled features and how they work.
 *
 * @author Chad Johnson
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    // These 3 methods demonstrate how to secure a service with spring security annotations

    @Override
    public String getUnsecuredInfo() {
        return "Some unsecured info";
    }

    @Override
    public String getUserInfo() {
        return "Some info for an authenticated user";
    }

    @Override
    public String getAdminInfo() {
        return "Some super secret admin stuff!";
    }

    // These 2 methods demonstrate hystrix functionality

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
