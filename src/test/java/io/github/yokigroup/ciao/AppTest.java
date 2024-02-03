package io.github.yokigroup.ciao;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test public void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}