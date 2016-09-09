/**
 * Copyright 2016 sebastien lever
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package fr.slever.p2c;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.slever.p2c.web.rest.RoleController;
import fr.slever.p2c.web.rest.UserController;
import fr.slever.p2c.web.rest.resource.SiteResource;

/**
 * Main class for P2C REST services application
 * 
 * 
 * 
 * @author sebastienlever
 *
 */
@SpringBootApplication
@Controller
public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  /**
   * @param args
   * @throws UnknownHostException
   */
  public static void main(String[] args) throws UnknownHostException {
    SpringApplication app = new SpringApplication(Application.class);
    Environment env = app.run(args).getEnvironment();
    LOGGER.info(
        "\n----------------------------------------------------------\n\t"
            + "Application '{}' is running! Access URLs:\n\t"
            + "External: \thttp://{}:{}\n----------------------------------------------------------",
        env.getProperty("spring.application.name"),
        InetAddress.getLocalHost().getHostAddress(),
        env.getProperty("server.port"));
  }

  /**
   * @return the REST API
   */
  @RequestMapping("/api")
  @ResponseBody
  public ResponseEntity<SiteResource> site() {
    SiteResource site = new SiteResource();
    site.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(Application.class).site()).withSelfRel());
    site.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(UserController.class).getUsers()).withRel("users"));
    site.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(RoleController.class).getRoles()).withRel("roles"));
    return new ResponseEntity<>(site, HttpStatus.OK);
  }

  @RequestMapping("/user")
  @ResponseBody
  public Principal user(Principal user) {
    return user;
  }
}
