package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dao.ShiftRepository;
import ru.itis.dto.CustomerDto;
import ru.itis.models.Customer;
import ru.itis.models.Shift;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shifts")
public class ShiftsServlet extends HttpServlet {

    private ShiftRepository shiftRepository;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        this.shiftRepository = (ShiftRepository) servletContext.getAttribute("shiftRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("user");
        List<Shift> shifts = shiftRepository.findAll(customerDto.getId());
        request.setAttribute("shifts", shifts);
        request.getRequestDispatcher("/shifts.ftl").forward(request, response);
    }
}
