package com.enroll.enrollment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Enrollees implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean activation_status;
    @Temporal(TemporalType.DATE)
    private Date birth_date;
    private String phoneNumber;
    @OneToMany
    @JoinColumn(name = "enrollee_id", referencedColumnName = "id")
    private List<Dependents> dependentsList;
}
