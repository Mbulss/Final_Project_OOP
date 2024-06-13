public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        // Create and add vehicles to the rental system
        Vehicle car1 = new Car("C001", "Toyota", "Camry", 60.0);
        Vehicle car2 = new Car("C002", "Honda", "Accord", 70.0);
        Vehicle car3 = new Car("C003", "Toyota", "Cayla", 50.0);

        Vehicle luxuryCar1 = new LuxuryCar("L001", "Rolls-Royce", "Phantom", 500.0, 0.15);
        Vehicle luxuryCar2 = new LuxuryCar("L002", "McLaren", "720S", 280.0, 0.1);
        Vehicle luxuryCar3 = new LuxuryCar("L003", "Ferrari", "488 GTB", 250.0, 0.1);
        Vehicle luxuryCar4 = new LuxuryCar("L004", "Audi", "R8", 210.0, 0.2);
        Vehicle luxuryCar5 = new LuxuryCar("L005", "Nissan", "GT-R", 190.0, 0.12);
        Vehicle luxuryCar6 = new LuxuryCar("L006", "Porsche", "911", 170.0, 0.11);

        Vehicle electricCar1 = new ElectricCar("E001", "Tesla", "Model S", 150.0);
        Vehicle electricCar2 = new ElectricCar("E002", "Nissan", "Leaf", 130.0);
        Vehicle electricCar3 = new ElectricCar("E003", "BMW", "i3", 140.0);

        Vehicle suv1 = new SUV("S001", "Ford", "Explorer", 90.0);
        Vehicle suv2 = new SUV("S002", "Chevrolet", "Tahoe", 100.0);
        Vehicle suv3 = new SUV("S003", "Toyota", "Highlander", 95.0);

        // Add vehicles to the rental system
        // Regular cars
        rentalSystem.addVehicle(car1);
        rentalSystem.addVehicle(car2);
        rentalSystem.addVehicle(car3);

        // Luxury cars
        rentalSystem.addVehicle(luxuryCar1);
        rentalSystem.addVehicle(luxuryCar2);
        rentalSystem.addVehicle(luxuryCar3);
        rentalSystem.addVehicle(luxuryCar4);
        rentalSystem.addVehicle(luxuryCar5);
        rentalSystem.addVehicle(luxuryCar6);

        // Electric cars
        rentalSystem.addVehicle(electricCar1);
        rentalSystem.addVehicle(electricCar2);
        rentalSystem.addVehicle(electricCar3);

        // SUVs
        rentalSystem.addVehicle(suv1);
        rentalSystem.addVehicle(suv2);
        rentalSystem.addVehicle(suv3);

        // Start the rental system menu
        rentalSystem.menu();
    }
}

