package com.controller;


import com.model.Branch;
import com.model.Customer;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/customer")
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/addcustomer", method= RequestMethod.POST)
    public ModelAndView add(@RequestParam(value = "name") String name){
        ModelAndView model = new ModelAndView("customer/list");
        customerService.saveOrUpdate(name);
        return model;
    }


    @RequestMapping(value = "/customerlist", method = RequestMethod.GET)
    public ModelAndView listCustomers(){
        ModelAndView model = new ModelAndView("customer/list");
        List<Customer> customerList = customerService.listAllCustomers();
        model.addObject("customerlist", customerList);
        return model;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") int id, @RequestParam(value = "name") String name){
        ModelAndView model = new ModelAndView("customer/list");
        Customer customer = customerService.findCustomerById(id);
        customerService.update(customer, name);
        return new ModelAndView("redirect:/customer/list");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCustomer(@PathVariable int id){
        ModelAndView model = new ModelAndView("customer/list");
        customerService.deleteCustomer(id);
        return new ModelAndView("redirect:/customer/list");
    }


}
