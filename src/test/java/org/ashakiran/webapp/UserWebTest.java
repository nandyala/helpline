package org.ashakiran.webapp;

import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.html.Cell;
import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTablePresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextFieldEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextInTable;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickButton;
import static net.sourceforge.jwebunit.junit.JWebUnit.getTable;
import static net.sourceforge.jwebunit.junit.JWebUnit.getTestContext;
import static net.sourceforge.jwebunit.junit.JWebUnit.gotoPage;
import static net.sourceforge.jwebunit.junit.JWebUnit.setScriptingEnabled;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class UserWebTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private ResourceBundle messages;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void editUser() {
    addUser();
    gotoPage("/userform?id=" + getInsertedUserId());
    assertTitleKeyMatches("userForm.title");
    assertTextFieldEquals("firstName", "Spring");
    clickButton("save");
    assertTitleKeyMatches("userList.title");
    deleteUser();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void listUsers() {
    addUser();
    gotoPage("/users");
    assertTitleKeyMatches("userList.title");

    // check that table is present
    assertTablePresent("userList");

    // check that a set of strings are present somewhere in table
    assertTextInTable("userList", new String[] { "Spring", "User" });
    deleteUser();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void login() {
    assertTitleKeyMatches("userList.title");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void logout() {
    gotoPage("/logout");
    assertTitleKeyMatches("index.title");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Before public void setUp() {
    setScriptingEnabled(false);
    getTestContext().setBaseUrl(
      "http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
    getTestContext().setResourceBundleName("messages");
    messages = ResourceBundle.getBundle("messages");

    beginAt("/users");
    assertTitleEquals("Login | AppFuse Light");
    setTextField("j_username", "admin");
    setTextField("j_password", "admin");
    submit("login");
    assertTitleEquals("User List | AppFuse Light");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void welcomePage() {
    beginAt("/");
    assertTitleKeyMatches("index.title");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * Convenience method to get the id of the inserted user Assumes last inserted user is "Spring User"
   *
   * @return  last id in the table
   */
  protected String getInsertedUserId() {
    gotoPage("/users");
    assertTablePresent("userList");
    assertTextInTable("userList", "Spring");

    Table table = getTable("userList");

    // Find row with Spring in it
    for (Object r : table.getRows()) {
      Row row = (Row) r;

      for (Object c : row.getCells()) {
        Cell cell = (Cell) c;

        if (cell.getValue().contains("Spring")) {
          return (row.getCells().get(0)).getValue();
        }
      }
    }

    return "";
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  private void addUser() {
    gotoPage("/userform");
    assertTitleKeyMatches("userForm.title");
    setTextField("username", "springuser");
    setTextField("password", "springuser");
    setTextField("firstName", "Spring");
    setTextField("lastName", "User");
    setTextField("email", "springuser@appfuse.org");
    clickButton("save");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  private void assertTitleKeyMatches(String title) {
    assertTitleEquals(messages.getString(title) + " | " + messages.getString("webapp.name"));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  private void deleteUser() {
    gotoPage("/userform?id=" + getInsertedUserId());
    assertTitleKeyMatches("userForm.title");
    clickButton("delete");
    assertTitleKeyMatches("userList.title");
  }
} // end class UserWebTest
