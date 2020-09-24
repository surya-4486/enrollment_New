package com.enroll.enrollment.dto;

import com.enroll.enrollment.model.Dependents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrolleeDTO {

    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotNull(message = "activation_status is mandatory")
    private Boolean activation_status;
    @NotNull(message = "birth_date is mandatory")
    private Date birth_date;
    private String phoneNumber;
    private List<DependentDTO> dependentsList;
}
