package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.dao.FieldOfStudyRepository;
import com.kamilc.universitysystem.domain.service.FieldOfStudyService;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import com.kamilc.universitysystem.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldOfStudyServiceImpl implements FieldOfStudyService {
    private final FieldOfStudyRepository fieldOfStudyRepository;


    @Autowired
    public FieldOfStudyServiceImpl(FieldOfStudyRepository fieldOfStudyRepository) {
        this.fieldOfStudyRepository = fieldOfStudyRepository;
    }

    @Override
    public List<FieldOfStudy> findFieldOfStudy(List<Integer> fieldOfStudyIDs, User user){
        return fieldOfStudyIDs
                .stream()
                .map(studyId -> {
                    FieldOfStudy exFoS = fieldOfStudyRepository.findById(studyId)
                            .orElseThrow(() -> new EntityNotFoundException("Can't find field of study with ID: " + studyId));

                    user.getFieldsOfStudy().add(exFoS);

                    return exFoS;
                })
                .toList();
    }
}
