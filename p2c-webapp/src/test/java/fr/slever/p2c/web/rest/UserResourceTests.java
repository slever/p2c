/*
 * Copyright 2012-2014 the original author or authors. Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package fr.slever.p2c.web.rest;

import static fr.slever.p2c.web.rest.URI.USERS_API;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.slever.p2c.service.UserService;
import fr.slever.p2c.util.TestUtil;
import fr.slever.p2c.web.rest.dto.UserDTO;

/**
 * User Integration test
 * 
 * @author sebastienlever
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserResourceTests {

  @Inject
  private UserService userService;

  private MockMvc restUserMockMvc;

  @Before
  public void setup() {
    UserResource userResource = new UserResource();

    ReflectionTestUtils.setField(userResource, "userService", userService);
    this.restUserMockMvc = MockMvcBuilders.standaloneSetup(userResource).build();
  }

  @Test
  public void testGetAllUsers() throws Exception {
    restUserMockMvc.perform(get(USERS_API).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$[0].lastName").value("ADMIN"))
        .andExpect(jsonPath("$[0].firstName").value("User"))
        .andExpect(jsonPath("$[1].lastName").value("PRODUCER"))
        .andExpect(jsonPath("$[1].firstName").value("User"))
        .andExpect(jsonPath("$[2].lastName").value("CONSUMER"))
        .andExpect(jsonPath("$[2].firstName").value("User"))
        .andExpect(jsonPath("$[3].lastName").value("TRANSPORTER"))
        .andExpect(jsonPath("$[3].firstName").value("User"));
  }

  @Test
  public void testGetFirstUser() throws Exception {
    restUserMockMvc.perform(get(USERS_API + "/user.admin").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$.lastName").value("ADMIN"))
        .andExpect(jsonPath("$.firstName").value("User"));
  }

  @Test
  public void testGetEmptyUser() throws Exception {
    restUserMockMvc.perform(get(USERS_API + "/.").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void testGetInvalidUser() throws Exception {
    restUserMockMvc.perform(get(USERS_API + "/invalid.login").accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
    restUserMockMvc.perform(get(USERS_API + "/invalid").accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
    restUserMockMvc.perform(get(USERS_API + "/useradmin").accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
    restUserMockMvc.perform(get(USERS_API + "/admin.").accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
  }

  @Test
  public void testGetUserByLogin() throws Exception {
    MvcResult result = restUserMockMvc.perform(get(USERS_API + "/user.admin").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.login").value("user.admin"))
        .andReturn();
    assertTrue(result.getResponse().getContentAsString().contains("user.admin"));
  }

  @Test
  public void testAddUser() throws Exception {
    byte[] userJson = getJsonUser("foo", "bar", "foo@bar.com", "PRODUCER");
    System.out.println(new String(userJson));

    restUserMockMvc.perform(post(USERS_API).contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(userJson))
        .andExpect(status().isOk());

    restUserMockMvc.perform(delete(USERS_API + "/foo.bar"))
        .andExpect(status().isOk());
  }

  @Test
  public void testPostUserLoginInvalid() throws Exception {
    byte[] invalidUserJson = getJsonUser("", "", "", "CONSUMER");
    restUserMockMvc.perform(post(USERS_API).contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(invalidUserJson))
        .andExpect(status().is4xxClientError());

    byte[] invalidUserJson2 = getJsonUser(null, null, null, "CONSUMER");
    restUserMockMvc.perform(post(USERS_API).contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(invalidUserJson2))
        .andExpect(status().is4xxClientError());
  }

  private byte[] getJsonUser(String firstName, String lastName, String email, String role) throws IOException {
    UserDTO user = new UserDTO();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setMobile("0666554433");
    Set<String> roles = new HashSet<>();
    roles.add(role);
    user.setRoles(roles);

    return TestUtil.convertObjectToJsonBytes(user);
  }
}
