package com.scrumtrek.simplestore.test;

import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paul
 */
public class TestMovie {
    
    public TestMovie() {
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

    @Test
    public void testMovie()
    {
        Movie regularMovie=new Movie("regular", PriceCodes.Regular);
        assertEquals(PriceCodes.Regular, regularMovie.getPriceCode());
    }
}
