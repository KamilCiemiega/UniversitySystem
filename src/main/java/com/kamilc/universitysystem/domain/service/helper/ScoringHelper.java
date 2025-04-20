package com.kamilc.universitysystem.domain.service.helper;

import com.kamilc.universitysystem.domain.model.applicationdata.StudentResult;
import org.springframework.stereotype.Component;




@Component
public class ScoringHelper {

 public Double findBestScoreForSubject(StudentResult studentResults){

     return  1.0;
 }

 public void  applyWeightAndLevelMultiplier(Double score, Double weight, String level){

 }

 public void  sumFinalScores(Double score){

 }

 public void verifierMinRequiredPoints(Double ObtainedPoints, Double minRequiredPoints){

 }

}

