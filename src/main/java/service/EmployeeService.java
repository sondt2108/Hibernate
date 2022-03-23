package service;

import dao.EmployeeDao;
import models.Employee;

import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDao();
    }

    public void create(Employee employee) {

        employeeDao.create(employee);
    }

    public void update(Employee employee) {

        employeeDao.update(employee);
    }

    public List<Employee> getAllEmployee(int pageNumber) {

        return employeeDao.getAllEmployee(pageNumber);
    }

    public Employee getEmployeeById(int id){

        return employeeDao.getEmployeeById(id);
    }

    public void deleteEmployee(int id){

        employeeDao.deleteEmployee(id);
    }
}
