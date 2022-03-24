package controller;

import models.Department;
import models.Employee;
import service.DepartmentService;
import service.EmployeeService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/list-employee")
public class EmployeeController extends HttpServlet {

    private EmployeeService employeeService;

    private DepartmentService departmentService;

    public EmployeeController() {
        employeeService = new EmployeeService();
        departmentService = new DepartmentService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNumber = request.getParameter("page");

        int currentPage = 1;
        if (pageNumber != null) {
            currentPage = Integer.parseInt(pageNumber);
            List<Employee> empl = employeeService.getAllEmployee(currentPage);
            request.setAttribute("epl", empl);
            request.setAttribute("currentPage", currentPage);

        } else {
            List<Employee> empl = employeeService.getAllEmployee(1);
            request.setAttribute("epl", empl);
            request.setAttribute("currentPage", currentPage);
        }


        List<Department> departments = departmentService.getAllDepartment();
        request.setAttribute("dept", departments);

        String id = request.getParameter("id");
        if (id != null) {
            request.setAttribute("detailEmployee", employeeService.getEmployeeById(Integer.parseInt(id)));
        }
        RequestDispatcher rd = request.getRequestDispatcher("./views/employee.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        String employeeId = request.getParameter("id");
        String fullName = request.getParameter("fullname");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String departmentId = request.getParameter("dept");

        Department department = new Department();
        department.setDept_id(Integer.parseInt(departmentId));

        if (action != null) {
            switch (action) {
                case "create":
                    Employee epl = new Employee(fullName, address, phoneNumber, email, department);
                    employeeService.create(epl);
                    response.sendRedirect(request.getContextPath() + "/list-employee");
                    break;
                case "update":
                    Employee employee = new Employee(Integer.parseInt(employeeId), fullName, address, phoneNumber, email, department);
                    employeeService.update(employee);
                    response.sendRedirect(request.getContextPath() + "/list-employee");
                    break;
                case "delete":
                    String id = request.getParameter("id");
                    System.out.println("id:" + id);

                    if (id != null) {
                        employeeService.deleteEmployee(Integer.parseInt(id));
                    }
                    response.sendRedirect(request.getContextPath() + "/list-employee");
                    break;
                case "detail":
                    System.out.println("Detail");
                    break;
            }
        }
    }
}
