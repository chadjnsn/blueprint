package xyz.chadjohnson.services;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Chad on 3/25/2016.
 */
public interface DemoSecurityService {

    String getUnsecuredInfo();

    @PreAuthorize("isAuthenticated()")
    String getUserInfo();

    @PreAuthorize("hasRole('ADMIN')")
    String getAdminInfo();
}
