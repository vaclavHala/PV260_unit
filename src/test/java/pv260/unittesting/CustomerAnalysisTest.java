package pv260.unittesting;

import static com.googlecode.catchexception.CatchException.catchException;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import pv260.unittesting.CustomerAnalysis.AnaliticalEngine;
import pv260.unittesting.CustomerAnalysis.CantUnderstandException;
import pv260.unittesting.CustomerAnalysis.Customer;
import pv260.unittesting.CustomerAnalysis.ErrorHandler;
import pv260.unittesting.CustomerAnalysis.GeneralException;
import pv260.unittesting.CustomerAnalysis.NewsList;
import pv260.unittesting.CustomerAnalysis.Offer;
import pv260.unittesting.CustomerAnalysis.Product;
import pv260.unittesting.CustomerAnalysis.Storage;

public class CustomerAnalysisTest {

    private static final String FAKE_QUERY = "GIMME_STUFF";

    private static final long FAKE_PROD_ID = 12345;

    /**
     * Verify the ErrorHandler is invoked when one of the AnaliticalEngine
     * throws exception and the exception is not re-thrown from the CustomerAnalysis.
     * The exception is passed to the ErrorHandler directly, not wrapped.
     */
    @Test
    public void testErrorHandlerInvokedWhenEngineThrows() throws GeneralException {

    }

    /**
     * Verify that if first AnaliticalEngine fails by throwing an exception,
     * subsequent engines are tried with the same input.
     * Ordering of engines is given by their order in the List passed to
     * constructor of AnaliticalEngine
     */
    @Test
    public void testSubsequentEnginesTriedIfOneFails() throws GeneralException {

    }

    /**
     * Verify that as soon as the first AnaliticalEngine succeeds,
     * this result is returned as result and no subsequent
     * AnaliticalEngine is invoked for this input
     */
    @Test
    public void testNoMoreEnginesTriedAfterOneSucceeds() throws GeneralException {

    }

    /**
     * Verify that once Offer is created for the Customer,
     * this order is persisted in the Storage before being
     * added to the NewsList
     */
    @Test
    public void testOfferIsPersistedBefreAddedToNewsList() throws GeneralException {


    }

    /**
     * Verify that Offer is created for every selected Customer for the given Product
     * test with at least two Customers selected by the AnaliticalEngine
     */
    @Test
    public void testOfferContainsProductAndCustomer() throws GeneralException {
      
    }

}
