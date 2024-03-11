package com.yudiol.SpringProjection.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestEmployee {

    private String firstName;

    private String lastName;

    private String position;

    private int salary;

    private String department;
}
