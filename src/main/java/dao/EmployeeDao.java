package dao;

import models.Employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.stat.Statistics;
import util.HibernateUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EmployeeDao {

    private static final Logger logger = LogManager.getLogger(EmployeeDao.class.getName());

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
        } catch (HibernateException e) {
            if (tx != null) {
                //Roll back if any exception occurs.
                tx.rollback();
            }
            e.printStackTrace();
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
        }
    }

    private static final int pageSize = 3;

    public List<Employee> getAllEmployee(int pageNumber) {

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
        //cacheLv1Ex1();
//        cacheLv2();
//        mechanismCacheL2();
        cacheQuery();
    }

    public static void cacheLv1Ex1() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (int i = 0; i < 10; i++) {
                Employee employee = session.get(Employee.class, 4);
                logger.info(employee.getFullName());
            }
            transaction.commit();
        }
    }


    //    lấy 1 Entity trên nhiều Session khác nhau
    public static void cacheLv2() {
        try (Session session = HibernateUtil.getSessionFactory().openSession();
             Session session1 = HibernateUtil.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, 4);
            logger.info("Session 1 at 1st time" + employee.getFullName());

            Employee employee1 = session.get(Employee.class, 4);
            logger.info("Session 1 at 2st time" + employee1.getFullName());

            Employee employee2 = session1.get(Employee.class, 4);
            logger.info("Session 2" + employee2.getFullName());
        }
    }

    //    cơ chế hoạt động của l2cache
    public static void mechanismCacheL2() {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            Session session1 = sessionFactory.openSession();

            Statistics statistics = sessionFactory.getStatistics();
            logger.info("statistics enabled : " + statistics.isStatisticsEnabled());
            statistics.setStatisticsEnabled(true);
            logger.info("statistics enabled : " + statistics.isStatisticsEnabled());

            printStatistics(statistics);

            logger.info("--------------------1-------------------------");
            Employee employee = session.get(Employee.class, 4);
            logger.info("employee-------------------1 : " + employee.getFullName()); //get from database
            printStatistics(statistics);

            logger.info("--------------------2-------------------------");
            Employee employee1 = session.get(Employee.class, 4);
            logger.info("employee-------------------2 : " + employee1.getFullName()); //get from L1 cache
            printStatistics(statistics);

            logger.info("--------------------3-------------------------");
            Employee employee2 = session1.get(Employee.class, 4);
            logger.info("employee-------------------3 : " + employee2.getFullName()); //get from L2 cache
            printStatistics(statistics);

            logger.info("--------------------4-------------------------");
            Employee employee3 = session1.get(Employee.class, 4);
            logger.info("employee-------------------4 : " + employee3.getFullName()); //get from L1 cache
            printStatistics(statistics);


            logger.info("--------------------5-------------------------");
            Employee employee4 = session.get(Employee.class, 6);
            logger.info("employee-------------------5 : " + employee4.getFullName()); //get from database
            printStatistics(statistics);

            logger.info("--------------------6-------------------------");
            Employee employee5 = session1.get(Employee.class, 6);
            logger.info("employee-------------------6 : " + employee5.getFullName()); //get from L2 cache
            printStatistics(statistics);


        }
    }

    public static void printStatistics(Statistics statistics) {
        logger.info("Second Level Hit Count = " + statistics.getSecondLevelCacheHitCount());
        logger.info("Second Level Hit Count = " + statistics.getSecondLevelCacheHitCount());
    }

//(1) : cả L1 và L2 cache không có gì, nên SQL được thực thì để lấy dữ liệu từ database.
// Sau đó nó được lưu tại L1 Cache của session1 và L2 Cache.

//(2) : Dữ liệu được lấy trực tiếp của L1 Cache, nên Hit=0, Put=1.

//(3) : Dữ liệu lấy từ L2 Cache. Do session2 không chứa Entity đã lấy từ session1, nó tìm ở L2 Cache và lấy từ Cache, không truy xuất Database.
// Sau đó, dữ liệu được cache lại ở L1 Cache của session2. Bây giờ: Hit=1, Put=1.

//(4) : Lần tiếp theo dữ liệu đã được cache lại ở L1 Cache của session2, nó không cần tìm ở L2 Cache, nên Hit=1, Put=1.

//(5) : Cả L1 và L2 cache không chứa Category=2, nên lấy dữ liệu từ database. Sau đó nó được lưu tại L1 Cache của session1 và L2 Cache. Bây giờ: Hit=1, Put=2.

//(6) : Tương tự, L1 Cache của session2 không chứa dữ liệu Category=2, nên nó tìm và truy xuất từ L2 Cache. Bây giờ: Hit=2, Put=2.




    public static void cacheQuery(){
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); Session session = sessionFactory.openSession()) {
            String hql = "from Employee e Where e.id = :id";
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            query.setCacheRegion(Employee.class.getCanonicalName());
            query.setParameter("id", 4);
            List result = query.list();
            logger.info("--------------------------Query: " +result);
            query.setParameter("id", 4);
            result = query.list();
            logger.info("--------------------------------Query 2:" + result);


        }
    }


}
