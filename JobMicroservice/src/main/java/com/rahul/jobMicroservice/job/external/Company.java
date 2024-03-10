package com.rahul.jobMicroservice.job.external;




import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor

public class Company {


    private Long id;
    private String name ;
    private String description ;




}