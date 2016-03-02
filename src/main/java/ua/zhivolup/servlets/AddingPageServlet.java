package ua.zhivolup.servlets;

import ua.zhivolup.entity.Person;
import ua.zhivolup.dao.PersonsRepository;
import ua.zhivolup.service.PersonService;
import ua.zhivolup.servlets.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddingPageServlet extends HttpServlet {
    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.instance().getPage("addUser.html", null));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String birthDate = req.getParameter("birthDate");
            if (name == null || name.isEmpty()
                    && birthDate == null || birthDate.isEmpty()){
                resp.setStatus(403);
            } else {
                resp.setStatus(200);
            }

            Person person = new Person();
            person.setName(name);
            person.setBirthDate(LocalDate.parse(birthDate, DATE_FORMATTER));
            personService.addToRepository(person);
            resp.sendRedirect("resultPage.html");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
