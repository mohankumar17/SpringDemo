package com.springboot.thymeleafdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="department")
@Getter
@Setter
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int deptId;

    @Column(name="name")
    private String deptName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="dept_id")
    private List<Employee> employees;

    public Department(int deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }


    public void add(Employee tempEmp){
        if(tempEmp==null){
            employees=new ArrayList<>();
        }
        employees.add(tempEmp);
    }

}
