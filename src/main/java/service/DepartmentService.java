package service;

import dao.DepartmentDao;
import models.Department;
import models.Employee;

import java.util.List;

public class DepartmentService {
    private DepartmentDao departmentDao;

    public DepartmentService(){
        departmentDao = new DepartmentDao();
    }
    public List<Department> getAllDepartment(){

        return departmentDao.getAllDepartment();
    }

    public void create(Department department){

        departmentDao.create(department);
    }

    public Department getDepartmentById(int id){

        return departmentDao.getDepartmentById(id);
    }

    public void update(Department department){

        departmentDao.update(department);
    }

    public void deleteDepartment(int id){

        departmentDao.deleteDepartment(id);
    }
}
