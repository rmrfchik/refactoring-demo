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

        final Movie regularLong = new Movie("regular", PriceCodes.Regular);
        final Rental rentalRegularLong = new Rental(regularLong, 3);
        mickey.addRental(rentalRegularLong);

        final Movie regularShort = new Movie("regularshort", PriceCodes.Regular);
        final Rental rentalRegularShort = new Rental(regularLong, 1);
        mickey.addRental(rentalRegularShort);

        final Movie newRelease = new Movie("newrelease", PriceCodes.NewRelease);
        final Rental rentalNewRelease = new Rental(newRelease, 2);
        mickey.addRental(rentalNewRelease);

        final Movie newReleaseShort = new Movie("newreleaseshort", PriceCodes.NewRelease);
        final Rental rentalNewReleaseShort = new Rental(newReleaseShort, 1);
        mickey.addRental(rentalNewReleaseShort);

        final Movie child = new Movie("child", PriceCodes.Childrens);
        final Rental rentalChild = new Rental(child, 4);
        mickey.addRental(rentalChild);

        final Movie childShort = new Movie("childshort", PriceCodes.Childrens);
        final Rental rentalChildShort = new Rental(childShort, 1);
        mickey.addRental(rentalChildShort);

        final String invoice = mickey.Statement();
        final String expectedInvoice
                = "Rental record for mickey\n"
                + "	regular	3.5\n"
                + "	regular	2.0\n"
                + "	newrelease	6.0\n"
                + "	newreleaseshort	3.0\n"
                + "	child	1.5\n"
                + "	childshort	1.5\n"
                + "Amount owed is 17.5\n"
                + "You earned 7 frequent renter points.";
        System.out.println(invoice);
        assertEquals(expectedInvoice, invoice);
    }
}
