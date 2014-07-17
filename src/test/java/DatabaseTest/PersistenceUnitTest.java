package DatabaseTest;

import internship.issuetracker.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import junit.framework.TestCase;

public class PersistenceUnitTest extends TestCase {

    private static Logger logger = Logger.getLogger(PersistenceUnitTest.class.getName());

    private EntityManagerFactory emFactory;

    private EntityManager em;

    private Connection connection;

    public PersistenceUnitTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            logger.info("Starting in-memory HSQL database for unit tests");
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa", "sa", "");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during HSQL database startup.");
        }
        try {
            logger.info("Building JPA EntityManagerFactory for unit tests");
            emFactory = Persistence.createEntityManagerFactory("testPU");
            logger.info("Building JPA EntityManager for unit tests");
            em = emFactory.createEntityManager();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during JPA EntityManager instanciation.");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        logger.info("Shuting down Hibernate JPA layer.");
        if (em != null) {
            em.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
        logger.info("Stopping in-memory HSQL database.");
        try {
            connection.createStatement().execute("SHUTDOWN");
        } catch (Exception ex) {}
    }

    public void testPersistence() {
        try {
           em.getTransaction().begin();

            User u = new User();
            u.setEmail("random@gmail.com");
            u.setPassword("random_password");
            u.setUserName("randomusername");
           em.persist(u);
           assertTrue(em.contains(u));
           em.getTransaction().commit();

        } catch (Exception ex) {
           em.getTransaction().rollback();
            ex.printStackTrace();
            fail("Exception during testPersistence");
        }
    }
    public static void main(String[] args)
    {
    	PersistenceUnitTest t=new PersistenceUnitTest("test1");
    	t.testPersistence();
    }
}