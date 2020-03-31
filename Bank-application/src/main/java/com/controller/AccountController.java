package com.controller;

import com.exceptions.LowBalanceException;
import com.model.Account;
import com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value="/addaccount", method= RequestMethod.POST)
    public ModelAndView addAccount(@RequestParam(value="balance") Double balance, @RequestParam(value="customer_id") int customer_id, @RequestParam(value="branch_id") int branch_id ){
        ModelAndView model = new ModelAndView("account/list");
        accountService.saveOrUpdate(balance, customer_id, branch_id);
        return new ModelAndView("redirect:account/list");
    }

    @RequestMapping(value="/withdraw/{id}", method=RequestMethod.POST)
    public ModelAndView withdraw(@PathVariable("id") int id, @RequestParam(value = "amount") Double amount) {
        ModelAndView model = new ModelAndView("account/list");
        Account account = accountService.findAccountById(id);
        try {
            accountService.withdraw(account, amount);
        }
        catch (LowBalanceException e){
            System.out.println(e.getMessage());
        }
        return new ModelAndView("/account/list");
    }

    @RequestMapping(value="/deposit/{id}", method=RequestMethod.POST)
    public ModelAndView deposit(@PathVariable("id") int id, @RequestParam(value="amount") Double amount){
        ModelAndView model = new ModelAndView("account/list");
        Account account = accountService.findAccountById(id);
        accountService.deposit(account, amount);
        return new ModelAndView("/account/list");
    }

    @RequestMapping(value = "/accountslist", method = RequestMethod.GET)
    public ModelAndView listAccounts(){
        ModelAndView model = new ModelAndView("account/list");
        List<Account> accountsList = accountService.listAllAccounts();
        model.addObject("accountslist", accountsList);
        return model;
    }

    @RequestMapping(value = "/deleteaccount/{id}", method = RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable int id){
        ModelAndView model = new ModelAndView("account/list");
        accountService.deleteAccount(id);
        return new ModelAndView("redirect:/account/list");
    }


}
