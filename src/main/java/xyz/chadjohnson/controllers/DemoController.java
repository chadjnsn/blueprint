package xyz.chadjohnson.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.chadjohnson.services.DemoHystrixService;
import xyz.chadjohnson.services.DemoSecurityService;

/**
 * An example controller class with a couple REST endpoints
 *
 * @author Chad Johnson
 */
@RestController
@RequestMapping(path = "/demo")
public class DemoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    DemoSecurityService demoSecurityService;

    @Autowired
    DemoHystrixService demoHystrixService;

    /**
     * An endpoint that requires no permissions to access
     */
    @RequestMapping(method = RequestMethod.GET, path = "/info")
    public ResponseEntity getInfo() {
        return ResponseEntity.ok(demoSecurityService.getUnsecuredInfo());
    }

    /**
     * An endpoint that requires a user with the USER role to access
     */
    @RequestMapping(method = RequestMethod.GET, path = "/secure/userinfo")
    public ResponseEntity getSecureInfo() {
        return ResponseEntity.ok(demoSecurityService.getUserInfo());
    }

    /**
     * An endpoint that requires a user with the ADMIN role to access
     */
    @RequestMapping(method = RequestMethod.GET, path = "/secure/admininfo")
    public ResponseEntity getSecureAdminInfo() {
        return ResponseEntity.ok(demoSecurityService.getAdminInfo());
    }

    /**
     * An endpoint that calls a hystrix command which might fail. If you have hystrix dashboard enabled, you can point
     * it towards localhost:9000/hystrix.stream to monitor the status of the hystrix command circuit breaker.
     */
    @RequestMapping(path = "/unstableinfo")
    public ResponseEntity getHystrixProtectedInfo() {
        return ResponseEntity.ok(demoHystrixService.getInfoThatMightFail());
    }
}
