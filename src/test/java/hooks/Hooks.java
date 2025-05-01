package hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import support.GuiceScoped;

public class Hooks {
    @Inject
    private GuiceScoped guiceScoped;

    @After()
    public void afterScenario() {
        if (guiceScoped.driver != null) {
            guiceScoped.driver.close();
            guiceScoped.driver.quit();
        }
    }
}
