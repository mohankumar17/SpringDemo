package com.springboot.thymeleafdemo.dao;

import com.springboot.thymeleafdemo.entity.Department;
import com.springboot.thymeleafdemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    private EntityManager entityManager;
    @Autowired
    public DepartmentDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    public List<Department> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Department> theQuery =
                currentSession.createQuery("from Department", Department.class);

        // execute query and get result list
        List<Department> departments = theQuery.getResultList();

        // return the results
        return departments;
    }

    @Override
    public Department findById(int theId) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        Department theDepartment =
                currentSession.get(Department.class, theId);

        // return the employee
        return theDepartment;
    }
}
