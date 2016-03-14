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
    public void testCtor()
    {
        final Movie regularMovie=new Movie("regular", PriceCodes.Regular);
        assertEquals(PriceCodes.Regular, regularMovie.getPriceCode());
        assertEquals("regular", regularMovie.getTitle());
        
    }
    
    @Test
    public void testChangePriceCode()
    {
        final Movie changePriceCodeMoview=new Movie("pricechanged", PriceCodes.Childrens);
        assertEquals(PriceCodes.Childrens, changePriceCodeMoview.getPriceCode());
        changePriceCodeMoview.setPriceCode(PriceCodes.Regular);
        assertEquals(PriceCodes.Regular, changePriceCodeMoview.getPriceCode());
    }
}
