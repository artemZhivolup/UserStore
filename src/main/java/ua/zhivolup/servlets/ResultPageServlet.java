package ua.zhivolup.servlets;

import ua.zhivolup.dao.PersonsRepository;
import ua.zhivolup.entity.Person;
import ua.zhivolup.service.PersonService;
import ua.zhivolup.servlets.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultPageServlet extends HttpServlet {
    private PersonService personService;

    public void setPersonService(PersonService personService){
        this.personService = personService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> persons = personService.getAll();
        Map<String, Object> map = new HashMap<>();
        map.put("users", persons);

        resp.getWriter().println(PageGenerator.instance().getPage("resultPage.html", map));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        personService.save();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("resultPage.html");
    }
}
