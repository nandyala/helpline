package com.ashakiran.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;

import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ashakiran.model.User;

import com.ashakiran.service.UserManager;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @InjectMocks private UserController controller;

  private MockMvc mockMvc;

  @Mock private UserManager userManager;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Before
  @SuppressWarnings("unchecked")
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    // set expected behavior on manager
    User user1 = new User();
    user1.setFirstName("ControllerTest");

    final List<User> users = new ArrayList<User>();
    users.add(user1);
    when(userManager.getUsers()).thenReturn(users);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testGetUsers() throws Exception {
    mockMvc.perform(post("/users")).andExpect(status().isOk()).andExpect(model().attributeExists("userList")).andExpect(
      view().name("userList"));
  }
} // end class UserControllerTest
