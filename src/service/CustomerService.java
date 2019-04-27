package service;

import model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    void delete(int id);
    void update(int id, Customer customer);
    void create(Customer customer);
}
