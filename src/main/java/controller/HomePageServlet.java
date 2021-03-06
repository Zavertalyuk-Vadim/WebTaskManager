package controller;

import dao.HibernateUtil;
import dao.ListDao;
import dao.TaskDao;
import model.Task;
import model.ListName;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/home"})
public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Task> taskList = new ArrayList<>();
//        List<ListName> listList = new ArrayList<>();
//        try {
//            TaskDao taskDao = new TaskDao();
//            taskList.addAll(taskDao.getAllTasks());
//            ListDao listDao = new ListDao();
//            listList.addAll(listDao.getLAllListsOfTasks());
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        SessionFactory factory ;
        factory=new AnnotationConfiguration().
                configure().
                //addPackage("com.xyz") //add package if used.
                        addAnnotatedClass(Task.class).
                        buildSessionFactory();
        factory.openSession();
        factory.
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        req.setAttribute("taskList", taskList);
//        req.setAttribute("listLists",listList);
        dispatcher.forward(req, resp);
    }
}
