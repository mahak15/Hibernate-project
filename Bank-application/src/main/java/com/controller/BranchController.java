package com.controller;

import com.model.Account;
import com.model.Branch;
import com.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/branch")
@Controller
public class BranchController {

    @Autowired
    BranchService branchService;

    @RequestMapping(value="/addbranch", method= RequestMethod.POST)
    public ModelAndView addBranch(@RequestParam(value="name") String name){
        ModelAndView model = new ModelAndView("branch/list");
        branchService.saveOrUpdate(name);
        return model;
    }

    @RequestMapping(value = "/branchlist", method = RequestMethod.GET)
    public ModelAndView listBranches(){
        ModelAndView model = new ModelAndView("branch/list");
        List<Branch> branchList = branchService.listAllBranches();
        model.addObject("branchlist", branchList);
        return model;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") int id, @RequestParam(value = "name") String name){
        ModelAndView model = new ModelAndView("branch/list");
        Branch branch = branchService.findBranchById(id);
        branchService.update(branch, name);
        return new ModelAndView("redirect:/branch/list");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable int id){
        ModelAndView model = new ModelAndView("branch/list");
        branchService.deleteBranch(id);
        return new ModelAndView("redirect:/branch/list");
    }



}
