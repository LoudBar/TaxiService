package ru.itis.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.dao.CustomerRepository;
import ru.itis.dao.DriverRepository;
import ru.itis.dao.FilesRepository;
import ru.itis.dao.ShiftRepository;
import ru.itis.dao.implementation.CustomerRepositoryImpl;
import ru.itis.dao.implementation.DriverRepositoryImpl;
import ru.itis.dao.implementation.FilesRepositoryImpl;
import ru.itis.dao.implementation.ShiftRepositoryImpl;
import ru.itis.services.*;
import ru.itis.services.implementation.*;
import ru.itis.services.validation.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class ServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String DB_USERNAME;
        String DB_PASSWORD;
        String DB_URL;
        String DB_DRIVER;
        String IMAGES_STORAGE_PATH;
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Требуется файл properties");
        }
        DB_USERNAME = (String) properties.get("spring.datasource.username");
        DB_PASSWORD = (String) properties.get("spring.datasource.password");
        DB_URL = (String) properties.get("spring.datasource.url");
        DB_DRIVER = (String) properties.get("spring.datasource.driver-class-name");
        IMAGES_STORAGE_PATH = (String) properties.get("storage.images");

        ServletContext servletContext = servletContextEvent.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        FilesRepository filesRepository = new FilesRepositoryImpl(dataSource);
        CustomerRepository customerRepository = new CustomerRepositoryImpl(dataSource);
        DriverRepository driverRepository = new DriverRepositoryImpl(dataSource);
        ShiftRepository shiftRepository = new ShiftRepositoryImpl(dataSource);
        FileService fileService = new FileServiceImpl(filesRepository, customerRepository, IMAGES_STORAGE_PATH);
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        Validator validator = new ValidatorImpl(customerRepository);
        ShiftService shiftService = new ShiftServiceImpl(shiftRepository, driverRepository);
        SignInService signInService = new SignInServiceImpl(customerRepository, passwordEncoder);
        SignUpService signUpService = new SignUpImpl(customerRepository, passwordEncoder, validator);
        ObjectMapper objectMapper = new ObjectMapper();

        servletContext.setAttribute("filesService", fileService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("shiftService", shiftService);
        servletContext.setAttribute("passwordEncoder", passwordEncoder);
        servletContext.setAttribute("objectMapper", objectMapper);
        servletContext.setAttribute("shiftRepository", shiftRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}