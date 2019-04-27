package service;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private static List<Customer> customerList=new ArrayList<>();
    static {
        customerList.add(new Customer(1,"Hung","hung@gmail.com","123 Trần Phú"));
        customerList.add(new Customer(2,"Nam","Nam@gmail.com","32 Lê Lợi"));
        customerList.add(new Customer(3,"Phi","Phi@gmail.com","14 Bạch Đằng"));
        customerList.add(new Customer(4,"Nhat","Nhat@gmail.com","87 Lê Duẩn"));
    }

    @Override
    public List<Customer> findAll() {
        return customerList;
    }

    @Override
    public Customer findById(int id) {
        for (Customer customer: customerList){
            if (customer.getId()==id){
                return customer;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (Customer customer: customerList){
            if (customer.getId()==id){
                customerList.remove(customer);
                break;
            }
        }
    }

    @Override
    public void update(int id,Customer customer) {
        for (Customer customerOfList: customerList){
            if (customerOfList.getId()==id){
                customerOfList.setName(customer.getName());
                customerOfList.setEmail(customer.getEmail());
                customerOfList.setAddress(customer.getAddress());
                break;
            }
        }
    }

    @Override
    public void create(Customer customer) {
        customerList.add(customer);
    }
}
