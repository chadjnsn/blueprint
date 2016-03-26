package xyz.chadjohnson.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.chadjohnson.services.DemoService;

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
    DemoService demoService;

    // Unsecured
    @RequestMapping(method = RequestMethod.GET, path = "/info")
    public ResponseEntity getInfo() {
        return ResponseEntity.ok(demoService.getUnsecuredInfo());
    }

    // Secured for anyone with the USER role
    @RequestMapping(method = RequestMethod.GET, path = "/secure/userinfo")
    public ResponseEntity getSecureInfo() {
        return ResponseEntity.ok(demoService.getUserInfo());
    }

    // Secured for anyone with the ADMIN role
    @RequestMapping(method = RequestMethod.GET, path = "/secure/admininfo")
    public ResponseEntity getSecureAdminInfo() {
        return ResponseEntity.ok(demoService.getAdminInfo());
    }

    @RequestMapping(path = "/unstableinfo")
    public ResponseEntity getHystrixProtectedInfo() {
        return ResponseEntity.ok(demoService.getInfoThatMightFail());
    }
}
