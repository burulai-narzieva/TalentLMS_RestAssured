package api_test.smoke;

import com.digital.entities.User;
import com.digital.utils.EntitiesManager;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest extends BaseApiTest{

    User user;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        user = EntitiesManager.genereteUser();
    }
    
    @Test
    public void testUsers() {
        User createUser = userController.createUser(user);
        User receivedUser = userController.receiveSingleUsers("id", createUser.getId());
        user.setId(createUser.getId());
        userController.getResponse().then().statusCode(200);
        AssertJUnit.assertNotNull(receivedUser);
    }

    @AfterClass
    public void afterClass() {
        userController.userDelete(user.getId());

    }
}
