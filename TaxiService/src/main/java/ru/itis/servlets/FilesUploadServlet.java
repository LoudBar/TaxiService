package ru.itis.servlets;

import ru.itis.dto.CustomerDto;
import ru.itis.exceptions.FileSizeException;
import ru.itis.models.FileInfo;
import ru.itis.services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/file-upload")
@MultipartConfig
public class FilesUploadServlet extends HttpServlet {

    private FileService filesService;

    @Override
    public void init(ServletConfig config) {
        this.filesService = (FileService) config.getServletContext().getAttribute("filesService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("html/fileUpload.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("user");
        FileInfo fileInfo;
        try {
            fileInfo = filesService.saveFileToStorage(customerDto,
                    part.getInputStream(),
                    part.getSubmittedFileName(),
                    part.getContentType(),
                    part.getSize());
        } catch (FileSizeException e) {
            response.setStatus(400);
            response.getWriter().println(e.getMessage());
            return;
        }
        customerDto.setAvatarId(fileInfo.getId());
        request.getSession().setAttribute("user", customerDto);
//        response.sendRedirect("/files/" + fileInfo.getId());
        response.sendRedirect("/profile");
    }
}
