package model;

import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> customerQueue;

    // Constructor to initialize the customerQueue
    public QueueOfCustomers() {
        this.customerQueue = new LinkedList<>();
    }

    // Method to add a customer to the queue
    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
    }

    // Method to remove a customer from the queue
    public Customer removeCustomer() {
        return customerQueue.poll(); // Returns null if the queue is empty
    }

    // Method to peek at the next customer in the queue without removing them
    public Customer peekCustomer() {
        return customerQueue.peek(); // Returns null if the queue is empty
    }

    // Method to get the size of the customer queue
    public int size() {
        return customerQueue.size();
    }

    // Optional: Override the toString() method for display purposes
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : customerQueue) {
            sb.append(customer.getName()).append("\n");
        }
        return sb.toString();
    }
}
