package com.enroll.enrollment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DependentDTO {
    private Long id;
    private Long enroleeId;
    private String name;
    private Date birth_date;
}
