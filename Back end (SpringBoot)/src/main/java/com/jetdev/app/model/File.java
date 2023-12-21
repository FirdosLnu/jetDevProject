package com.jetdev.app.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy="file" ,fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Employee> employeeList;
    private Date dateModified;


}
