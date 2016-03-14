package com.scrumtrek.simplestore.test;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import java.lang.reflect.Field;
import java.util.List;
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
public class TestCustomer {

    public TestCustomer() {
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
    public void testCtor() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        final Customer mickey = new Customer("mickey");
        assertEquals("mickey", mickey.getName());

        Field rentalsField = mickey.getClass().getDeclaredField("m_Rentals");
        rentalsField.setAccessible(true);
        List<Rental> rentals = (List<Rental>) rentalsField.get(mickey);
        assertEquals(0, rentals.size());
    }

    @Test
    public void testAddRental() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        final Customer mickey = new Customer("mickey");
        assertEquals("mickey", mickey.getName());

        final Movie regular = new Movie("regular", PriceCodes.Regular);
        final Rental rental = new Rental(regular, 1);
        mickey.addRental(rental);

        Field rentalsField = mickey.getClass().getDeclaredField("m_Rentals");
        rentalsField.setAccessible(true);
        List<Rental> rentals = (List<Rental>) rentalsField.get(mickey);
        assertEquals(1, rentals.size());
    }

    @Test
    public void testStatement() {
        final Customer mickey = new Customer("mickey");
        assertEquals("mickey", mickey.getName());

        final Movie regular = new Movie("regular", PriceCodes.Regular);
        final Rental rentalRegular = new Rental(regular, 3);
        mickey.addRental(rentalRegular);

        final Movie newRelease = new Movie("newrelease", PriceCodes.NewRelease);
        final Rental rentalNewRelease = new Rental(newRelease, 2);
        mickey.addRental(rentalNewRelease);

        final Movie child = new Movie("child", PriceCodes.Childrens);
        final Rental rentalChild = new Rental(child, 4);
        mickey.addRental(rentalChild);

        final String invoice = mickey.Statement();
        final String expectedInvoice
                = "Rental record for mickey\n"
                + "	regular	3.5\n"
                + "	newrelease	6.0\n"
                + "	child	1.5\n"
                + "Amount owed is 11.0\n"
                + "You earned 4 frequent renter points.";
        assertEquals(expectedInvoice, invoice);
    }
}
