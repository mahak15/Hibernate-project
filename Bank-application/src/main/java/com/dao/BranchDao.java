package com.dao;
import com.model.Branch;
import com.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BranchDao {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Branch> listAllBranches() {
        Criteria criteria = getSession().createCriteria(Branch.class);
        return criteria.list();
    }

    public void saveOrUpdate(String name) {
        Branch branch = new Branch();
        branch.setName(name);
        getSession().saveOrUpdate(branch);
    }

    public Branch findBranchById(int id) {
        return (Branch) getSession().get(Branch.class, id);
    }

    public void deleteBranch(int id) {
        Branch branch = (Branch) getSession().get(Branch.class, id);
        getSession().delete(branch);
    }

    public void update(Branch branch, String name){
        branch.setName(name);
        getSession().update(branch);
    }
}
