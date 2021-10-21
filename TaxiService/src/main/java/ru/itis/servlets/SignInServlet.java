package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.CustomerDto;
import ru.itis.dto.CustomerForm;
import ru.itis.exceptions.ValidationException;
import ru.itis.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.signInService = springContext.getBean(SignInService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signIn.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CustomerForm form = CustomerForm.builder()
                .phoneNumber(request.getParameter("phoneNumber"))
                .password(request.getParameter("password"))
                .build();
        CustomerDto customerDto;

        try {
            customerDto = signInService.signIn(form);
        } catch (ValidationException e) {
            response.sendRedirect("/signIn");
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("user", customerDto);
        response.sendRedirect("/profile");
    }
}