package com.enroll.enrollment.sevice;

import com.enroll.enrollment.dto.DependentDTO;
import com.enroll.enrollment.dto.EnrolleeDTO;
import com.enroll.enrollment.model.Dependents;
import com.enroll.enrollment.model.Enrollees;
import com.enroll.enrollment.repo.DependentRepo;
import com.enroll.enrollment.repo.EnrolleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrolleeService {

    @Autowired
    EnrolleRepo enrolleRepo;

    @Autowired
    DependentRepo dependentRepo;

    public Enrollees getEnrolleById(long id) {
        Enrollees enrollees = enrolleRepo.getOne(id);
        if(null == enrollees) return new Enrollees();
        return enrollees;
    }

    public List<Enrollees> getEnrolles() {
        return enrolleRepo.findAll();
    }

    public Enrollees addEnrolle(EnrolleeDTO enrolleeDTO) {
        Enrollees enrollee = new Enrollees();
        enrollee.setActivation_status(true);
        enrollee.setBirth_date(enrolleeDTO.getBirth_date());
        enrollee.setName(enrolleeDTO.getName());
        enrollee.setPhoneNumber(enrolleeDTO.getPhoneNumber());
        return enrolleRepo.save(enrollee);
    }

    public Enrollees updateEnrolle(EnrolleeDTO enrolleeDTO) {
        Enrollees enrollee = enrolleRepo.getOne(enrolleeDTO.getId());
        if(null == enrollee) return new Enrollees();
        enrollee.setId(enrolleeDTO.getId());
        enrollee.setActivation_status(true);
        enrollee.setBirth_date(enrolleeDTO.getBirth_date());
        enrollee.setName(enrolleeDTO.getName());
        enrollee.setPhoneNumber(enrolleeDTO.getPhoneNumber());
        enrollee.setDependentsList(mapDependentList(enrolleeDTO.getDependentsList()));
        return enrolleRepo.save(enrollee);
    }


    public String deleteEnrolle(long id) {
        Enrollees enrollees = enrolleRepo.getOne(id);
        if(null == enrollees) return  "Record not found";
        List<Dependents> dependentsList = enrollees.getDependentsList();
        dependentsList.stream().forEach(e -> dependentRepo.delete(e));
        enrolleRepo.delete(enrollees);
        return "Success";
    }

    public Enrollees addDependent(DependentDTO dependentDto) {
        Enrollees enrollee = enrolleRepo.getOne(dependentDto.getEnroleeId().longValue());
        if(null == enrollee) return new Enrollees();
        List<Dependents> dependentsList = enrollee.getDependentsList();
        Dependents dependent = mapDependent(dependentDto);
        dependentsList.add(dependent);
        dependentRepo.save(dependent);
        Enrollees resp = enrolleRepo.save(enrollee);
        return resp;
    }

    public Enrollees updateDependent(DependentDTO dependentDto) {
        Dependents dependent = mapDependent(dependentDto);
        dependentRepo.save(dependent);
        Enrollees resp = enrolleRepo.getOne(dependentDto.getEnroleeId());
        return resp;
    }

    public Enrollees removeDependents(long id, long dependentId) {
        Enrollees enrollee = enrolleRepo.getOne(id);
        Dependents dependent = dependentRepo.getOne(dependentId);
        List<Dependents> dependentsList = enrollee.getDependentsList();
        dependentsList.remove(dependent);
        dependentRepo.delete(dependent);
        Enrollees resp = enrolleRepo.save(enrollee);
        return resp;
    }


    private List<Dependents> mapDependentList(List<DependentDTO> dependentDtoList) {
        List<Dependents> dependentsList = new ArrayList<>();
        for (int i = 0; i < dependentDtoList.size(); i++) {
            dependentsList.add(mapDependent(dependentDtoList.get(i)));
        }
        return dependentsList;
    }

    private Dependents mapDependent(DependentDTO dependentDTO) {
        Dependents dependents = new Dependents();
        dependents.setId(dependentDTO.getId());
        dependents.setName(dependentDTO.getName());
        dependents.setBirth_date(dependentDTO.getBirth_date());
        return dependents;
    }
}
