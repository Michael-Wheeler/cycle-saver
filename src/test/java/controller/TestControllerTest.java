package controller;

import com.cycle_saver.CycleSaver;
import com.cycle_saver.controller.TestController;
import com.cycle_saver.model.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CycleSaver.class })
public class TestControllerTest {
    private User testUser;

    @Autowired
    private TestController testController;

    @Test
    public void shouldAddUser() {
        this.testUser = testController.addUser();
        Assert.assertNotNull(this.testUser);
    }

    @Test
    public void shouldGetUser() {
        this.testUser = testController.addUser();
        Assert.assertNotNull(testController.getUserById(this.testUser.getObjectId()));
    }

    @Test
    public void shouldClearUsers() {
        testController.deleteUsers();
        Assert.assertEquals(testController.getUsers(), new ArrayList<>());
    }

    @Test
    public void shouldClearAlreadyEmptyUsers() {
        testController.deleteUsers();
        Assert.assertEquals(testController.getUsers(), new ArrayList<>());
    }

    @Test
    public void shouldGenerateUsers() {
        testController.deleteUsers();
        testController.genUsers();
        Assert.assertNotNull(testController.getUsers());
    }
}
