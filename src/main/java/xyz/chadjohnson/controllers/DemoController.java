package xyz.chadjohnson.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

/**
 * Created by Chad on 3/24/2016.
 */
@RestController()
@RequestMapping(path = "/demo")
public class DemoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    // Unsecured
    @RequestMapping(method = RequestMethod.GET, path = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getInfo() {
        return Response.ok("Unsecured...").build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/secure/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getSecureInfo() {
        return Response.ok("Secured!").build();
    }
}
