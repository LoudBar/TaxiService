package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.CustomerDto;
import ru.itis.dto.ShiftDto;
import ru.itis.dto.ShiftForm;
import ru.itis.services.ShiftService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/takeTrip")
public class ShiftServlet extends HttpServlet {

    private ShiftService shiftService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.shiftService = springContext.getBean(ShiftService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/takeTrip.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("user");

        ShiftForm shiftForm = ShiftForm.builder()
                .departurePlace(request.getParameter("departurePlace"))
                .arrivalPlace(request.getParameter("arrivalPlace"))
                .build();

        shiftService.takeTrip(customerDto.getId(), shiftForm);

        response.sendRedirect("/shifts");
    }
}
