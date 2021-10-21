package ru.itis.servlets;

import ru.itis.dto.CustomerDto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            CustomerDto customerDto = (CustomerDto) req.getSession().getAttribute("user");
            req.setAttribute("user", customerDto);
            req.getRequestDispatcher("/profile.ftl").forward(req, resp);
    }
}
