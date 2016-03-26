package xyz.chadjohnson.services;

/**
 * Created by Chad on 3/26/2016.
 */
public interface DemoHystrixService {
    String getInfoThatMightFail();

    String getBackupInfo();
}
