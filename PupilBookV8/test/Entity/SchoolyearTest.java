/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Collection;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KastanNotas
 */
public class SchoolyearTest {
    
    public SchoolyearTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIdSchoolYear method, of class Schoolyear.
     */
    @Test
    public void testGetIdSchoolYear() {
        System.out.println("getIdSchoolYear");
        Schoolyear instance = new Schoolyear();
        Integer expResult = null;
        Integer result = instance.getIdSchoolYear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdSchoolYear method, of class Schoolyear.
     */
    @Test
    public void testSetIdSchoolYear() {
        System.out.println("setIdSchoolYear");
        Integer idSchoolYear = null;
        Schoolyear instance = new Schoolyear();
        instance.setIdSchoolYear(idSchoolYear);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Schoolyear.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Schoolyear instance = new Schoolyear();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Schoolyear.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Schoolyear instance = new Schoolyear();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsactualyear method, of class Schoolyear.
     */
    @Test
    public void testGetIsactualyear() {
        System.out.println("getIsactualyear");
        Schoolyear instance = new Schoolyear();
        boolean expResult = false;
        boolean result = instance.getIsactualyear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsactualyear method, of class Schoolyear.
     */
    @Test
    public void testSetIsactualyear() {
        System.out.println("setIsactualyear");
        boolean isactualyear = false;
        Schoolyear instance = new Schoolyear();
        instance.setIsactualyear(isactualyear);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartDate method, of class Schoolyear.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        Schoolyear instance = new Schoolyear();
        Date expResult = null;
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartDate method, of class Schoolyear.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        Date startDate = null;
        Schoolyear instance = new Schoolyear();
        instance.setStartDate(startDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndDate method, of class Schoolyear.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        Schoolyear instance = new Schoolyear();
        Date expResult = null;
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndDate method, of class Schoolyear.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        Date endDate = null;
        Schoolyear instance = new Schoolyear();
        instance.setEndDate(endDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudygroupCollection method, of class Schoolyear.
     */
    @Test
    public void testGetStudygroupCollection() {
        System.out.println("getStudygroupCollection");
        Schoolyear instance = new Schoolyear();
        Collection<Studygroup> expResult = null;
        Collection<Studygroup> result = instance.getStudygroupCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStudygroupCollection method, of class Schoolyear.
     */
    @Test
    public void testSetStudygroupCollection() {
        System.out.println("setStudygroupCollection");
        Collection<Studygroup> studygroupCollection = null;
        Schoolyear instance = new Schoolyear();
        instance.setStudygroupCollection(studygroupCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Schoolyear.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Schoolyear instance = new Schoolyear();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Schoolyear.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Schoolyear instance = new Schoolyear();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Schoolyear.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Schoolyear instance = new Schoolyear();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
