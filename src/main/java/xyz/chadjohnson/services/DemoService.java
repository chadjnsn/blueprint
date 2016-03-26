package xyz.chadjohnson.services;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Chad on 3/25/2016.
 */
public interface DemoService {

    String getUnsecuredInfo();

    @PreAuthorize("hasRole('USER')")
    String getUserInfo();

    @PreAuthorize("hasRole('ADMIN')")
    String getAdminInfo();

    String getInfoThatMightFail();

    String getBackupInfo();
}
