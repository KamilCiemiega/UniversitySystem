package com.kamilc.universitysystem.domain.service.helper.scoringServiceHelpers;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.entity.FieldOfStudy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ScoreCalculator {

    private final ScoringHelper scoringHelper;

    public ScoreCalculator(ScoringHelper scoringHelper) {
        this.scoringHelper = scoringHelper;
    }

    public BigDecimal calculateScoreForFieldOfStudy(ApplicationConfigurator applicationData, FieldOfStudy study) {

//          Double applicationResult = applicationData.getStudentResults().stream()
//                    .map(scoringHelper::findBestScoreForSubject)
//                    .reduce(0.0, Double::sum);

        return BigDecimal.valueOf(2);
    }

}
