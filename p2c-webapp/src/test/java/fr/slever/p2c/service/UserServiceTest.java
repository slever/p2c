package fr.slever.p2c.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.slever.p2c.data.entity.Role;
import fr.slever.p2c.data.entity.User;
import fr.slever.p2c.exception.FunctionalException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@WebAppConfiguration
@Transactional
public class UserServiceTest {

  @Inject
  private UserService userService;

  @Test
  public void testContainsUsers() {
    List<User> users = userService.findAllUsers();
    assertTrue("should contain any user", !users.isEmpty());
  }

  @Test
  public void testgetUser() {
    User userInDb = userService.findUserByLogin("user.admin");
    assertNotNull(userInDb);
    assertEquals("user.admin", userInDb.getLogin());
    assertEquals("User", userInDb.getFirstName());
    assertEquals("ADMIN", userInDb.getLastName());

  }

  @Test
  public void testgetNotExistingUser() {
    User userInDb = userService.findUserByLogin("invalid.login");
    assertNull(userInDb);

  }

  @Test(expected = FunctionalException.class)
  public void testUserLoginInvalid() {
    User user1 = new User();
    user1.setFirstName("");
    user1.setLastName("");
    userService.addUser(user1);

    User user2 = new User();
    user1.setFirstName(null);
    user1.setLastName(null);
    userService.addUser(user2);
  }

  @Test
  public void testaddUser() {
    User user = new User();
    user.setFirstName("User1");
    user.setLastName("userLastName");
    user.setPassword("pwd");
    user.setEmail("foo@bar.com");
    user.setMobile("0666554433");
    Role role = new Role();
    role.setName("PRODUCER");
    List<Role> roles = new ArrayList<Role>();
    roles.add(role);
    user.setRoles(roles);

    userService.addUser(user);

    String login = "user1.userlastname";
    User userInDb = userService.findUserByLogin(login);
    assertNotNull(userInDb);
    assertEquals(user.getLogin(), userInDb.getLogin());
    assertTrue("User1".equals(userInDb.getFirstName()));
    assertTrue("userLastName".equals(userInDb.getLastName()));
    assertTrue(!userInDb.getRoles().isEmpty());
    assertTrue("PRODUCER".equals(userInDb.getRoles().get(0).getName()));

    userService.deleteUser(login);
    User deletedUser = userService.findUserByLogin(login);
    assertNull(deletedUser);
  }

}
