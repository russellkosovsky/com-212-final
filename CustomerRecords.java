// rn this is essentially just a mini test class for CustomerHashTable 

public class CustomerRecords {
    public static void main(String[] args) {
        
        // HashTable to store customer records
        HashTable customerTable = new HashTable(13);

        // Create some Customers
        Customer customer1 = new Customer("Alice", "1234567890123456", "alice@example.com");
        Customer customer2 = new Customer("Bob", "2345678901234567", "bob@example.com");
        Customer customer3 = new Customer("Charlie", "3456789012345678", "charlie@example.com");

        // to the HashTable
        customerTable.add(customer1);
        customerTable.add(customer2);
        customerTable.add(customer3);

        // Look up customers using their credit card numbers
        Customer result = customerTable.lookUp("1234567890123456");
        if (result != null) {
            System.out.println("Customer found: " + result.getName());
        } else {
            System.out.println("Customer not found");
        }

        // Remove customer from HashTable
        customerTable.remove("2345678901234567");

        // Check if the customer was removed
        result = customerTable.lookUp("2345678901234567");
        if (result != null) {
            System.out.println("Customer found: " + result.getName());
        } else {
            System.out.println("Customer not found");
        }

        // Print the current HashTable
        customerTable.printTable();
    }
}
