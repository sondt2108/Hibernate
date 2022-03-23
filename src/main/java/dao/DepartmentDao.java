package dao;

import models.Department;
import models.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class DepartmentDao {

    public List<Department> getAllDepartment() {
        Transaction transaction = null;
        List<Department> listOfDepartment = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object

            listOfDepartment = session.createQuery("from Department ").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfDepartment;
    }


    public void create(Department department) {
        Transaction tx = null;
        //Get the session object.
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            //Start hibernate session.
            tx = session.beginTransaction();

            //Insert a new department record in the database.
            session.save(department);

            //Commit hibernate transaction if no exception occurs.
            tx.commit();
            System.out.println("Saved Successfully.");
        }catch (HibernateException e) {
            if(tx!=null){
                //Roll back if any exception occurs.
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            //Close hibernate session.
            session.close();
        }
    }

    public Department getDepartmentById(int id) {

        Transaction transaction = null;
        Department department = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object
            department = session.get(Department.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return department;
    }

    public void update(Department department) {
        Transaction tx = null;
        //Get the session object.
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            //Start hibernate session.
            tx = session.beginTransaction();

            //Insert a new department record in the database.
            session.update(department);

            //Commit hibernate transaction if no exception occurs.
            tx.commit();
            System.out.println("Update Successfully.");
        }catch (HibernateException e) {
            if(tx!=null){
                //Roll back if any exception occurs.
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            //Close hibernate session.
            session.close();
        }
    }

    public void deleteDepartment(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a department object
            Department department = session.get(Department.class, id);
            if (department != null) {
                session.delete(department);
                System.out.println("Department is deleted");
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
}
