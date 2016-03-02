package ua.zhivolup;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.zhivolup.dao.DataSource;
import ua.zhivolup.dao.PersonDao;
import ua.zhivolup.service.PersonService;
import ua.zhivolup.servlets.AddingPageServlet;
import ua.zhivolup.servlets.ResultPageServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        DataSource dataSource = new DataSource();
        PersonDao personDao = new PersonDao();
        personDao.setConection(dataSource);

        PersonService personService = new PersonService();
        personService.setPersonDao(personDao);
        personService.initialize();

        ResultPageServlet resultPageServlet = new ResultPageServlet();
        AddingPageServlet addingPageServlet = new AddingPageServlet();

        resultPageServlet.setPersonService(personService);
        addingPageServlet.setPersonService(personService);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(resultPageServlet), "/resultPage.html");
        servletContextHandler.addServlet(new ServletHolder(addingPageServlet), "/addUser.html");

        Server server = new Server(8080);
        server.setHandler(servletContextHandler);
        server.start();
    }
}
