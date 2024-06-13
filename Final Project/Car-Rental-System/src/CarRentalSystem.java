//This class manages the entire car rental system's operations, including the list of vehicles, the list of rentals, and customer management.
//Key methods in this class:
//addVehicle(Vehicle vehicle): Adds a new vehicle to the system.
//rentVehicle(Vehicle vehicle, Customer customer, int days): Processes vehicle rentals.
//returnVehicle(Vehicle vehicle): Processes vehicle returns.
//menu(): Displays the main menu for user interaction.
//Additional methods to handle submenus like renting a vehicle, returning a vehicle,
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Main class for the car rental system
public class CarRentalSystem {
    private List<Vehicle> vehicles;   // List to store available vehicles
    private List<Rental> rentals;     // List to store ongoing rentals
    private CustomerManager customerManager;  // Manager for customer-related operations

    // Constructor initializes the lists and customer manager
    public CarRentalSystem() {
        vehicles = new ArrayList<>();
        rentals = new ArrayList<>();
        customerManager = new CustomerManager();
    }

    // Method to add a new vehicle to the system
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // Method to rent a vehicle to a customer
    public void rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (vehicle.isAvailable()) {
            vehicle.rent();   // Mark vehicle as rented
            rentals.add(new Rental(vehicle, customer, days));   // Add rental record
            customer.incrementRentalCount();   // Increment customer's rental count
        } else {
            System.out.println("Vehicle is not available for rent.");
        }
    }

    // Method to return a rented vehicle
    public void returnVehicle(Vehicle vehicle) {
        vehicle.returnVehicle();   // Mark vehicle as available
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getVehicle().equals(vehicle)) {
                rentalToRemove = rental;   // Find the corresponding rental record
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);   // Remove the rental record
        } else {
            System.out.println("Vehicle was not rented.");
        }
    }

    // Method to display the main menu and handle user input
    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Return a Vehicle");
            System.out.println("3. Add Car for Rental");
            System.out.println("4. Manage Customers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        rentVehicleMenu(scanner);   // Display rent vehicle menu
                        break;
                    case 2:
                        returnVehicleMenu(scanner);   // Display return vehicle menu
                        break;
                    case 3:
                        addCarForRental(scanner);   // Display add car menu
                        break;
                    case 4:
                        manageCustomers(scanner);   // Display customer management menu
                        break;
                    case 5:
                        System.out.println("Exiting the system. Goodbye!");
                        return; // Exit the method to stop the program
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
            System.out.println("\nThank you for using the Car Rental System!");
        }
    }

    // Method to display the rent vehicle menu and handle renting process
    private void rentVehicleMenu(Scanner scanner) {
        System.out.println("\n== Rent a Vehicle ==\n");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.println("\nSelect Vehicle Type:");
        System.out.println("1. Regular Car");
        System.out.println("2. Luxury Car");
        System.out.println("3. Electric Car");
        System.out.println("4. SUV");
        System.out.print("Enter your choice: ");
        int vehicleTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("\nAvailable Vehicles:");
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable() && matchesVehicleTypeChoice(vehicle, vehicleTypeChoice)) {
                availableVehicles.add(vehicle);
                System.out.println(vehicle.getId() + " - " + vehicle.getBrand() + " " + vehicle.getModel());
            }
        }

        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles available for the selected type.");
            return;
        }

        System.out.print("\nEnter the vehicle ID you want to rent: ");
        String vehicleId = scanner.nextLine();

        System.out.print("Enter the number of days for rental: ");
        int rentalDays = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Customer customer;
        String customerId;
        if (customerManager.customerExists(customerName)) {
            customer = customerManager.getCustomerByName(customerName);   // Get existing customer
            customerId = customer.getCustomerId();
        } else {
            customerId = customerManager.generateNewCustomerId();   // Generate new customer ID
            customerManager.addCustomer(customerId, customerName);   // Add new customer
            customer = customerManager.getCustomerById(customerId);
        }

        Vehicle selectedVehicle = null;
        for (Vehicle vehicle : availableVehicles) {
            if (vehicle.getId().equals(vehicleId)) {
                selectedVehicle = vehicle;
                break;
            }
        }

        if (selectedVehicle != null) {
            double totalPrice = selectedVehicle.calculatePrice(rentalDays);
            double discount = 0.0;
            if (customer.isEligibleForLoyaltyDiscount()) {
                discount = totalPrice * customer.getLoyaltyDiscountPercentage();
            }
            double finalPrice = totalPrice - discount;

            System.out.println("\n== Rental Information ==\n");
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Vehicle: " + selectedVehicle.getBrand() + " " + selectedVehicle.getModel());
            System.out.println("Rental Days: " + rentalDays);

            // Total Price at the top
            System.out.printf("Total Price: $%.2f%n", finalPrice);

            // Detailed breakdown
            double basePrice = totalPrice;
            if (selectedVehicle instanceof LuxuryCar) {
                basePrice = totalPrice / (1 + ((LuxuryCar) selectedVehicle).getLuxuryTax());
                double luxuryTaxAmount = totalPrice - basePrice;
                System.out.printf("Base Price: $%.2f%n", basePrice);
                System.out.printf("Tax: $%.2f%n", luxuryTaxAmount);
            }

            if (discount > 0) {
                System.out.printf("Loyalty Discount: $%.2f%n", discount);
            }

            System.out.print("\nConfirm rental (Y/N): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                rentVehicle(selectedVehicle, customer, rentalDays);
                System.out.println("\nVehicle rented successfully.");
            } else {
                System.out.println("\nRental canceled.");
            }
        } else {
            System.out.println("\nInvalid vehicle selection or vehicle not available for rent.");
        }
    }

    // Helper method to check if a vehicle matches the selected type
    private boolean matchesVehicleTypeChoice(Vehicle vehicle, int vehicleTypeChoice) {
        switch (vehicleTypeChoice) {
            case 1:
                return vehicle instanceof Car && !(vehicle instanceof LuxuryCar) && !(vehicle instanceof ElectricCar) && !(vehicle instanceof SUV);
            case 2:
                return vehicle instanceof LuxuryCar;
            case 3:
                return vehicle instanceof ElectricCar;
            case 4:
                return vehicle instanceof SUV;
            default:
                return false;
        }
    }

    // Method to display the return vehicle menu and handle returning process
    private void returnVehicleMenu(Scanner scanner) {
        System.out.println("\n== Return a Vehicle ==\n");

        if (rentals.isEmpty()) {
            System.out.println("No vehicles are currently rented.");
            return;
        }

        System.out.println("Rented Vehicles:");
        for (Rental rental : rentals) {
            System.out.println(rental);
        }

        System.out.print("Enter the vehicle ID you want to return: ");
        String vehicleId = scanner.nextLine();

        Vehicle vehicleToReturn = null;
        for (Rental rental : rentals) {
            if (rental.getVehicle().getId().equals(vehicleId)) {
                vehicleToReturn = rental.getVehicle();
                break;
            }
        }

        if (vehicleToReturn != null) {
            returnVehicle(vehicleToReturn);
            System.out.println("Vehicle returned successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    // Method to check if a car ID is unique
    private boolean isCarIdUnique(String carId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(carId)) {
                return false; // Return false if ID already exists
            }
        }
        return true; // Return true if ID is unique
    }

    // Method to display the add car menu and handle adding new cars
    private void addCarForRental(Scanner scanner) {
        System.out.println("\n== Add Your Car for Rental ==\n");

        System.out.println("Select Vehicle Type:");
        System.out.println("1. Regular Car");
        System.out.println("2. Luxury");
        System.out.println("3. Electric Car");
        System.out.println("4. SUV");
        System.out.print("Enter your choice: ");
        int vehicleTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter car ID: ");
        String carId = scanner.nextLine();

        // Check if car ID is unique
        if (!isCarIdUnique(carId)) {
            System.out.println("Car ID already exists. \n" +
                    "Please choose a different ID.");
            return;
        }

        System.out.print("Enter car brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter car model: ");
        String model = scanner.nextLine();
        System.out.print("Enter base price per day: ");
        double basePricePerDay = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Vehicle newCar = null;
        switch (vehicleTypeChoice) {
            case 1:
                newCar = new Car(carId, brand, model, basePricePerDay);
                break;
            case 2:
                System.out.print("Enter luxury tax for Luxury: ");
                double luxuryTax = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                newCar = new LuxuryCar(carId, brand, model, basePricePerDay, luxuryTax);
                break;
            case 3:
                newCar = new ElectricCar(carId, brand, model, basePricePerDay);
                break;
            case 4:
                newCar = new SUV(carId, brand, model, basePricePerDay);
                break;
            default:
                System.out.println("Invalid vehicle type choice.");
                return;
        }

        addVehicle(newCar);
        System.out.println("Your car has been added for rental.");
    }

    // Method to display the customer management menu and handle customer operations
    private void manageCustomers(Scanner scanner) {
        System.out.println("\n== Manage Customers ==\n");
        System.out.println("1. Display All Customers");
        System.out.println("2. Add Customer");
        System.out.println("3. Update Customer");
        System.out.println("4. Delete Customer");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            displayAllCustomers();   // Display all customers
        } else if (choice == 2) {
            System.out.print("Enter customer ID: ");
            String customerId = scanner.nextLine();
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();
            customerManager.addCustomer(customerId, name);   // Add a new customer
        } else if (choice == 3) {
            System.out.print("Enter customer ID: ");
            String customerId = scanner.nextLine();
            System.out.print("Enter new customer name: ");
            String name = scanner.nextLine();
            customerManager.updateCustomer(customerId, name);   // Update customer details
        } else if (choice == 4) {
            System.out.print("Enter customer ID: ");
            String customerId = scanner.nextLine();
            customerManager.deleteCustomer(customerId);   // Delete a customer
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Method to display all customers
    private void displayAllCustomers() {
        System.out.println("\n== All Customers ==\n");
        Map<String, Customer> customers = customerManager.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers.values()) {
                System.out.println("Customer ID: " + customer.getCustomerId() +
                        ", Name: " + customer.getName() +
                        ", Rental Count: " + customer.getRentalCount());
            }
        }
    }
}
