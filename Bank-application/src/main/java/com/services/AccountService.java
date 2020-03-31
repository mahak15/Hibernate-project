package com.services;


import com.dao.AccountDao;
import com.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BranchService {
    BranchDao branchDao;

    @Autowired
    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public List listAllBranches() {
        return branchDao.listAllBranches();
    }

    public void saveOrUpdate(String branchName) {
        branchDao.saveOrUpdate(branchName);
    }

    public Branch findBranchById(int id) {
        return branchDao.findBranchById(id);
    }

    public void deleteBranch(int id) {
        branchDao.deleteBranch(id);
    }

    public void update(Branch branch, String name){
        branchDao.update(branch, name);
    }
}
