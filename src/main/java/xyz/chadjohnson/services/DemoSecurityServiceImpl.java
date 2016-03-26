package xyz.chadjohnson.services;

import org.springframework.stereotype.Service;

/**
 * A service to demonstrate some of the included/enabled features and how they work.
 *
 * @author Chad Johnson
 */
@Service("demoService")
public class DemoSecurityServiceImpl implements DemoSecurityService {

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
}
