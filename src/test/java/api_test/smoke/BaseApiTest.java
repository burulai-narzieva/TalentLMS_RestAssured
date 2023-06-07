package api_test.smoke;

import com.digital.api.controller.UserController;
import com.digital.config.ConfigReader;

public abstract class BaseApiTest {

    protected UserController userController = new UserController(ConfigReader.getProperty("apiUrl"), ConfigReader.getProperty("apiKey"));
}
