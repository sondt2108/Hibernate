package dao;

import models.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class EmployeeDao {
    public void create(Employee employee) {
        Transaction tx = null;
        //Get the session object.
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            //Start hibernate session.
            tx = session.beginTransaction();

            //Insert a new employee record in the database.
            session.save(employee);

            //Commit hibernate transaction if no exception occurs.
            tx.commit();
            System.out.println("Saved Successfully.");
        } catch (HibernateException e) {
            if (tx != null) {
                //Roll back if any exception occurs.
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //Close hibernate session.
            session.close();
        }
    }

    public void update(Employee employee) {
        Transaction tx = null;
        //Get the session object.
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            //Start hibernate session.
            tx = session.beginTransaction();

            //Update employee record in the database.
            session.update(employee);

            //Commit hibernate transaction if no exception occurs.
            tx.commit();
            System.out.println("Update Successfully.");
        } catch (HibernateException e) {
            if (tx != null) {
                //Roll back if any exception occurs.
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //Close hibernate session.
            session.close();
        }
    }

    private static final int pageSize = 3;

    public List<Employee> getAllEmployee( int pageNumber) {

        Transaction transaction = null;
        List<Employee> listOfEmployee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object

            Query query = session.createQuery("from Employee");
            query = query.setFirstResult(pageSize * (pageNumber - 1));
            query.setMaxResults(pageSize);


            listOfEmployee = query.list();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfEmployee;
    }

    public Employee getEmployeeById(int id) {

        Transaction transaction = null;
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object
            employee = session.get(Employee.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return employee;
    }

    public void deleteEmployee(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a employee object
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
                System.out.println("Employee is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Transaction transaction = null;
        List<Employee> listOfEmployee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object

            Query query = session.createQuery("from Employee");
            query = query.setFirstResult(pageSize * (1 - 1));
            query.setMaxResults(pageSize);


            listOfEmployee = query.list();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println(listOfEmployee);
    }
}
