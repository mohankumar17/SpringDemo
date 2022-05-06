package com.springboot.thymeleafdemo.controller;

import com.springboot.thymeleafdemo.entity.Department;
import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.service.DepartmentService;
import com.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentControllerWithDB {

    private DepartmentService departmentService;
    private EmployeeService employeeService;

    @Autowired
    public DepartmentControllerWithDB(DepartmentService theDepartmentService, EmployeeService theEmployeeService){
        departmentService = theDepartmentService;
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel){

        List<Department> theDepartments = departmentService.findAll();
        theModel.addAttribute("departments",theDepartments);
        return "departments/list-departments";
    }

    @GetMapping("/showEmployees")
    public String showEmployees(@RequestParam("departmentId") int theId,
                                    Model theModel) {

        Department theDepartment = departmentService.findById(theId);
        List<Employee> theEmployees = employeeService.findAll();

        List<Employee> deptEmployees = new ArrayList<>();
        for(Employee eachEmployee : theEmployees){
            if(eachEmployee.getTheDepartment().getDeptId()==theDepartment.getDeptId()){
                deptEmployees.add(eachEmployee);
            }
        }

        theModel.addAttribute("employees",deptEmployees);

        return "employees/list-employees";
    }


}
