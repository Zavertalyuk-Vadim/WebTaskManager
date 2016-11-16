package controller;

import dao.TaskDao;
import model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by employee on 11/15/16.
 */
@WebServlet({"/home"})
public class HomePageServlet extends HttpServlet {

    private String counterKey = "counter";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        List<Task> taskList = new ArrayList<>();
        try {
            TaskDao taskDao =  new TaskDao();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (session.isNew())
            System.out.println("Home servlet session not exist");
        else {
            int listLength = (Integer) session.getAttribute(counterKey);
            System.out.println("Home servlet session exist, listLength = " + listLength);
            for (int i = 1; i <= listLength; i++) {
                try {
                    Task task = (Task) session.getAttribute(i + "");
                    if(!task.getTitle().isEmpty()){
                      taskList.add(task);
                    }
                }catch (NullPointerException e){
                }
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        req.setAttribute("taskList", taskList);
        dispatcher.forward(req, resp);
    }
}
