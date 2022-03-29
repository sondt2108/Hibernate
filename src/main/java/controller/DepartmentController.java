package controller;

import models.Department;

import org.apache.logging.log4j.Logger;
import service.DepartmentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/list-department")
public class DepartmentController extends HttpServlet {
    private DepartmentService departmentService;

    public DepartmentController(){
        departmentService = new DepartmentService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Department> dept = departmentService.getAllDepartment();
        req.setAttribute("dept", dept);

        String id = req.getParameter("id");
        System.out.println("id:" + id);

        if (id != null) {
             req.setAttribute("detailDept", departmentService.getDepartmentById(Integer.parseInt(id)));
        }

        RequestDispatcher rd = req.getRequestDispatcher("./views/department.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        String deptName = req.getParameter("deptName");
        String deptId = req.getParameter("id");

        if (action != null) {
            switch (action) {
                case "create":

                    Department departments = new Department(deptName);
                    departmentService.create(departments);
                    resp.sendRedirect(req.getContextPath() + "/list-department");
                    break;
                case "update":

                    if (deptId != null) {
                        Department dept = new Department(Integer.parseInt(deptId), deptName);
                         departmentService.update(dept);
                        resp.sendRedirect(req.getContextPath() + "/list-department");
                    }
                    break;
                case "delete":
                    if (deptId != null) {
                         departmentService.deleteDepartment(Integer.parseInt(deptId));
                    }
                    resp.sendRedirect(req.getContextPath() + "/list-department");
                    break;
            }
        }
    }
}
