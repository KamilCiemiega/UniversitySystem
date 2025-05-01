package com.kamilc.universitysystem.domain.service.serviceimpl;

import com.kamilc.universitysystem.domain.model.applicationdata.ApplicationConfigurator;
import com.kamilc.universitysystem.domain.model.applicationdata.StudentResult;
import com.kamilc.universitysystem.domain.model.scoringconfig.FieldConfig;
import com.kamilc.universitysystem.domain.model.scoringconfig.RequiredSubject;
import com.kamilc.universitysystem.domain.model.scoringconfig.ScoringConfiguration;
import com.kamilc.universitysystem.domain.model.scoringconfig.ScoringRules;
import com.kamilc.universitysystem.entity.FieldOfStudy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class TestDataFactory {

    public  ScoringConfiguration createITScoringConfig() {
        ScoringConfiguration config = new ScoringConfiguration();

        ScoringRules rules = new ScoringRules();
        rules.setLevelMultipliers(Map.of(
                "basic", 0.7,
                "extended", 1.0
        ));

        FieldConfig fieldConfig = new FieldConfig();
        fieldConfig.setCode("IT");
        fieldConfig.setRequiredSubjects(List.of(
                createRequiredSubject("mathematics", "basic", 1.0, true),
                createRequiredSubject("mathematics", "extended", 1.0, true),
                createRequiredSubject("information technology", "basic", 0.5, false),
                createRequiredSubject("information technology", "extended", 0.5, false),
                createRequiredSubject("english", "basic", 0.3, true),
                createRequiredSubject("english", "extended", 0.3, true)
        ));

        config.setScoringRules(rules);
        config.setFieldConfig(fieldConfig);
        return config;
    }

    public ScoringConfiguration createRoboticsScoringConfig() {
        ScoringConfiguration config = new ScoringConfiguration();

        ScoringRules rules = new ScoringRules();
        rules.setLevelMultipliers(Map.of(
                "basic", 0.7,
                "extended", 1.0
        ));

        FieldConfig fieldConfig = new FieldConfig();
        fieldConfig.setCode("ROB");
        fieldConfig.setRequiredSubjects(List.of(
                createRequiredSubject("mathematics", "basic", 1.0, true),
                createRequiredSubject("mathematics", "extended", 1.0, true),
                createRequiredSubject("physics", "extended", 0.8, true),
                createRequiredSubject("english", "basic", 0.3, true),
                createRequiredSubject("english", "extended", 0.3, false)
        ));

        config.setScoringRules(rules);
        config.setFieldConfig(fieldConfig);
        return config;
    }

    public RequiredSubject createRequiredSubject(String name, String level, double weight, boolean required) {
        RequiredSubject subject = new RequiredSubject();
        subject.setName(name);
        subject.setWeight(weight);
        subject.setRequired(required);
        return subject;
    }

    public ApplicationConfigurator sampleAppConfig() {
        ApplicationConfigurator appConfig = new ApplicationConfigurator();
        appConfig.setStudentResults(List.of(
                createStudentResult("mathematics", "basic", new BigDecimal("84")),
                createStudentResult("mathematics", "extended", new BigDecimal("64")),
                createStudentResult("english", "basic", new BigDecimal("98")),
                createStudentResult("english", "extended", new BigDecimal("77"))
        ));
        return appConfig;
    }

    public ApplicationConfigurator sampleAppConfigMissingPhysics() {
        ApplicationConfigurator appConfig = new ApplicationConfigurator();
        appConfig.setStudentResults(List.of(
                createStudentResult("mathematics", "basic", new BigDecimal("84")),
                createStudentResult("english", "basic", new BigDecimal("98"))
        ));
        return appConfig;
    }

    public ApplicationConfigurator emptyAppConfig() {
        ApplicationConfigurator appConfig = new ApplicationConfigurator();
        appConfig.setStudentResults(List.of());
        return appConfig;
    }

    public List<FieldOfStudy> sampleFieldOfStudies() {
        FieldOfStudy informationTechnology = new FieldOfStudy();
        informationTechnology.setId(1);
        informationTechnology.setName("Information Technology");
        informationTechnology.setStudyType(FieldOfStudy.StudyType.FULL_TIME);
        informationTechnology.setScoringConfig(createITScoringConfig());

        FieldOfStudy robotics = new FieldOfStudy();
        robotics.setId(2);
        robotics.setName("Robotics");
        robotics.setStudyType(FieldOfStudy.StudyType.FULL_TIME);
        robotics.setScoringConfig(createRoboticsScoringConfig());

        return List.of(informationTechnology, robotics);
    }

    private StudentResult createStudentResult(String subject, String level, BigDecimal score) {
        StudentResult result = new StudentResult();
        result.setSubject(subject);
        result.setLevel(level);
        result.setScore(score);
        return result;
    }

}
