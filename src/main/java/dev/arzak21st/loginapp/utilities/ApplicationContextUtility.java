package dev.arzak21st.loginapp.utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtility {

    /* ========== VARIABLES ========== */
    private static final String configFile = "spring-context.xml";
    private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configFile);

    /* ========== METHODS ========== */
    protected static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
