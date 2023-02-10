package medical_analytical_prescription.service.impl;

import medical_analytical_prescription.BaseTest;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.exception.UserNotFoundException;
import medical_analytical_prescription.utils.UserUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
public class UserServiceImplTest extends BaseTest {

  private UserUtil userUtil;

  @Before
  public void before() {
    userUtil = new UserUtil();
  }

  @Test
  public void showAllUsersTest() {
    // given
    int repositorySize = userRepository.findAll().size();

    // when
    int serviceSize = userService.getAllUsers().size();

    // then
    assertEquals(repositorySize, serviceSize);
  }

  @Test(expected = UserNotFoundException.class)
  public void getUserById() {
    // given
    User savedUser = addUser();

    // when
    User foundedUser = userService.getUserById(savedUser.getId());

    // then
    assertEquals(savedUser.getId(), foundedUser.getId());
    assertEquals(savedUser.getFirstName(), foundedUser.getFirstName());
    assertEquals(savedUser.getLastName(), foundedUser.getLastName());
    assertEquals(savedUser.getSex(), foundedUser.getSex());
    assertEquals(savedUser.getAge(), foundedUser.getAge());
    assertEquals(savedUser.getEmail(), foundedUser.getEmail());
    assertNull(savedUser.getApplicationHistoryIds());
    userService.getUserById(999L);
  }

  @Test(expected = UserNotFoundException.class)
  public void getUserByEmail() {
    // given
    User savedUser = addUser();

    // when
   User foundedUser = userService.getUserByEmail(savedUser.getEmail());

    // then
    assertEquals(savedUser.getId(), foundedUser.getId());
    assertEquals(savedUser.getFirstName(), foundedUser.getFirstName());
    assertEquals(savedUser.getLastName(), foundedUser.getLastName());
    assertEquals(savedUser.getSex(), foundedUser.getSex());
    assertEquals(savedUser.getAge(), foundedUser.getAge());
    assertEquals(savedUser.getEmail(), foundedUser.getEmail());
    assertNull(savedUser.getApplicationHistoryIds());
    assertNotNull(userService.getUserByEmail("EMAIL"));
  }

  @Test
  public void addUserTest() {
    // given
    User createdUser = userUtil.createUser();

    // when
    User savedUser = userService.addUser(createdUser);

    // then
    assertNotNull(savedUser.getId());
    assertNotNull(userService.getUserById(savedUser.getId()));
    assertEquals(savedUser.getFirstName(), createdUser.getFirstName());
    assertEquals(savedUser.getLastName(), createdUser.getLastName());
    assertEquals(savedUser.getEmail(), createdUser.getEmail());
    assertEquals(savedUser.getAge(), createdUser.getAge());
    assertNull(savedUser.getApplicationHistoryIds());
  }

  private User addUser() {
    User createdUser = userUtil.createUser();
    return userService.addUser(createdUser);
  }
}
