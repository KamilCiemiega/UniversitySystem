package com.kamilc.universitysystem.domain.service.helper.scoringservicehelpers;

import com.kamilc.universitysystem.domain.model.applicationdata.StudentResult;
import com.kamilc.universitysystem.domain.model.scoringconfig.RequiredSubject;
import com.kamilc.universitysystem.domain.model.scoringconfig.ScoringRules;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Component
public class ScoringHelper {

 public BigDecimal findBestScoreForSubject(
         RequiredSubject requiredSubject,
         List<StudentResult> matchingStudentResults,
         ScoringRules scoringRules){



      matchingStudentResults.stream()
              .map(sResult -> {
               String subjectLevel = sResult.getLevel();
               BigDecimal subjectScore = sResult.getScore();
               BigDecimal subjectWeight = requiredSubject.getWeight();


//              })

 }

 public BigDecimal  applyWeightAndLevelMultiplier(
         BigDecimal score,
         BigDecimal weight,
         String level,
         Map<String, BigDecimal> levelMultipliers){

 }

 public void  sumFinalScores(Double score){

 }

// public void verifierMinRequiredPoints(Double ObtainedPoints, Double minRequiredPoints){
//
// }

}

