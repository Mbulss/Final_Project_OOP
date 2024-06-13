/*CustomerManager: Manages customer-related operations.
Adds, updates, deletes, and searches for customers.*/

import java.util.HashMap;
import java.util.Map;

// Class to manage customer-related operations
public class CustomerManager {
    private Map<String, Customer> customers; // Map to store customers with their IDs as keys

    // Constructor initializes the customer map
    public CustomerManager() {
        customers = new HashMap<>();
    }

    // Method to add a new customer
    public void addCustomer(String customerId, String name) {
        // Validate customer ID and name
        if (customerId == null || customerId.isEmpty() || name == null || name.isEmpty()) {
            System.out.println("Customer ID and name cannot be null or empty.");
            return;
        }
        // Check if customer ID already exists
        if (customers.containsKey(customerId)) {
            System.out.println("Customer ID already exists. Please choose a different ID.");
            return;
        }
        // Add new customer to the map
        customers.put(customerId, new Customer(customerId, name));
    }

    // Method to get a customer by their ID
    public Customer getCustomerById(String customerId) {
        // Validate customer ID
        if (customerId == null || customerId.isEmpty()) {
            System.out.println("Customer ID cannot be null or empty.");
            return null;
        }
        // Retrieve customer from the map
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
        }
        return customer;
    }

    // Method to get a customer by their name
    public Customer getCustomerByName(String name) {
        // Validate customer name
        if (name == null || name.isEmpty()) {
            System.out.println("Customer name cannot be null or empty.");
            return null;
        }
        // Find and return the customer with the given name
        return customers.values().stream()
                .filter(customer -> customer.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    // Method to check if a customer exists by their name
    public boolean customerExists(String name) {
        // Validate customer name
        if (name == null || name.isEmpty()) {
            System.out.println("Customer name cannot be null or empty.");
            return false;
        }
        // Check if any customer matches the given name
        return customers.values().stream()
                .anyMatch(customer -> customer.getName().equals(name));
    }

    // Method to update a customer's name
    public void updateCustomer(String customerId, String name) {
        // Validate customer ID and name
        if (customerId == null || customerId.isEmpty() || name == null || name.isEmpty()) {
            System.out.println("Customer ID and name cannot be null or empty.");
            return;
        }
        // Find and update the customer's name
        Customer customer = customers.get(customerId);
        if (customer != null) {
            customer.setName(name);
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Method to delete a customer
    public void deleteCustomer(String customerId) {
        // Validate customer ID
        if (customerId == null || customerId.isEmpty()) {
            System.out.println("Customer ID cannot be null or empty.");
            return;
        }
        // Remove customer from the map
        if (customers.remove(customerId) != null) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Method to get all customers
    public Map<String, Customer> getAllCustomers() {
        return customers;
    }

    // Method to generate a new customer ID
    public String generateNewCustomerId() {
        return "CUS" + (customers.size() + 1); // Generate ID based on the number of customers
    }
}
