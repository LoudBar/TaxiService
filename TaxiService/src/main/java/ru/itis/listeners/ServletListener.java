package ru.itis.listeners;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.ApplicationConfig;
import ru.itis.dao.CustomerRepository;
import ru.itis.dao.FilesRepository;
import ru.itis.dao.implementation.CustomerRepositoryImpl;
import ru.itis.dao.implementation.FilesRepositoryImpl;
import ru.itis.services.FileService;
import ru.itis.services.PasswordEncoder;
import ru.itis.services.implementation.FileServiceImpl;
import ru.itis.services.implementation.PasswordEncoderImpl;
import ru.itis.services.implementation.ValidatorImpl;
import ru.itis.services.validation.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {

    private ApplicationContext springContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        springContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("springContext", springContext);

        HikariDataSource hikariDataSource = springContext.getBean(HikariDataSource.class);

        FilesRepository filesRepository = new FilesRepositoryImpl(hikariDataSource);

        CustomerRepository customerRepository = new CustomerRepositoryImpl(hikariDataSource);
        FileService fileService = new FileServiceImpl(filesRepository, customerRepository);
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        Validator validator = new ValidatorImpl(customerRepository);

        servletContext.setAttribute("userRepository", customerRepository);
        servletContext.setAttribute("passwordEncoder", passwordEncoder);
        servletContext.setAttribute("validator", validator);
        servletContext.setAttribute("filesRepository", filesRepository);
        servletContext.setAttribute("filesService", fileService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HikariDataSource hikariDataSource = springContext.getBean(HikariDataSource.class);
        hikariDataSource.close();
    }
}