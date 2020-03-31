package com.dao;


import com.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Customer> listAllCustomers() {
        Criteria criteria = getSession().createCriteria(Customer.class);
        return criteria.list();
    }

    public void saveOrUpdate(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        getSession().saveOrUpdate(customer);
    }

    public Customer findCustomerById(int id) {
        return (Customer) getSession().get(Customer.class, id);
    }

    public void deleteCustomer(int id) {
        Customer customer = (Customer) getSession().get(Customer.class, id);
        getSession().delete(customer);
    }

    public void update(Customer customer, String name){
        customer.setName(name);
        getSession().update(customer);
    }
}
