package com.kamilc.universitysystem.domain.service.helper.scoringservicehelpers;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.model.applicationdata.StudentResult;
import com.kamilc.universitysystem.domain.model.scoringconfig.RequiredSubject;
import com.kamilc.universitysystem.domain.model.scoringconfig.ScoringRules;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class ScoreCalculator {

    private final ScoringHelper scoringHelper;

    public ScoreCalculator(ScoringHelper scoringHelper) {
        this.scoringHelper = scoringHelper;
    }

    public BigDecimal calculateScoreForFieldOfStudy(ApplicationConfigurator applicationData, FieldOfStudy study) {
           BigDecimal achievedPoints = study.getScoringConfig().getFieldConfig().getRequiredSubjects()
                     .stream()
                     .map(subject -> {
                         List<StudentResult> matchingResult =   applicationData.getStudentResults()
                                 .stream()
                                 .filter(sResult -> sResult.getSubject().equals(subject.getName()))
                                 .toList();

                         ScoringRules scoringRules = study.getScoringConfig().getScoringRules();

                             return scoringHelper.findBestScoreForSubject(subject, matchingResult , scoringRules);
                         })
                         .reduce(BigDecimal.ZERO, BigDecimal::add);


        return BigDecimal.valueOf(2);
    }

}
