package pv260.unittesting;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class CustomerAnalysis {

    private static final Pattern VALID_PARAM = Pattern.compile("\\w{3,}");

    private final List<AnaliticalEngine> engines;

    private final Storage storage;

    private final NewsList newslist;

    private final ErrorHandler errorHandler;

    public CustomerAnalysis(
            List<AnaliticalEngine> supplier,
            Storage storage,
            NewsList newslist,
            ErrorHandler errorHandler) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(storage);
        Objects.requireNonNull(newslist);
        Objects.requireNonNull(errorHandler);
        this.engines = supplier;
        this.storage = storage;
        this.newslist= newslist;
        this.errorHandler = errorHandler;
    }

    public int calculateTotalCredit(String query, long productId) throws GeneralException {
        if (query == null || !VALID_PARAM.matcher(query).matches()) {
            throw new InvalidQueryException();
        }
        int totalCredit = 0;
        Product prod = this.storage.find(Product.class, productId);
        List<Customer> customers = this.findInterestingCustomers(prod);
        for (Customer customer : customers) {
            try {
                Offer offer = new Offer(customer, prod);
                this.storage.persist(offer);
                this.newslist.sendPeriodically(offer);
            } catch (ServiceUnavailableException e) {
                throw e;
            } catch (GeneralException e) {
                this.errorHandler.handle(e);
            }
        }
        return totalCredit;
    }

    List<Customer> findInterestingCustomers(Product product) throws ServiceUnavailableException {
        for (AnaliticalEngine engine : this.engines) {
            try {
                return engine.interesetingCustomers(product);
            } catch (GeneralException e) {
                this.errorHandler.handle(e);
            }
        }
        throw new ServiceUnavailableException();
    }

    public interface Storage {

        <T> T find(Class<T> type, long key);

        int update(Object record) throws GeneralException;

        int persist(Object record) throws GeneralException;

        int persist(List<Object> records) throws GeneralException;

    }

    public interface NewsList{

        public void sendPeriodically(Offer offer);

    }

    public interface AnaliticalEngine {

        List<Customer> interesetingCustomers(Product product) throws GeneralException;

        Map<String, Integer> uninterestingData() throws GeneralException;
    }

    public interface ErrorHandler {

        void handle(Exception error);
    }

    public static class GeneralException extends Exception {}

    public static class ServiceUnavailableException extends GeneralException {}

    public static class CantUnderstandException extends GeneralException {}

    public static class InvalidQueryException extends GeneralException {}

    public static class Customer {

        private long id;

        private String name;

        private int credit;

        public Customer(long id, String name, int credit) {
            this.id = id;
            this.name = name;
            this.credit = credit;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCredit() {
            return credit;
        }
    }

    public static class Product {

        private long id;

        private String name;

        private String category;

        private double price;

        public Product(long id, String name, String category, double price) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public double getPrice() {
            return price;
        }
    }

    public static class Offer{

        private Customer customer;
        private Product product;

        public Offer(Customer customer, Product product) {
            this.customer = customer;
            this.product = product;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Product getProduct() {
            return product;
        }
    }
}
