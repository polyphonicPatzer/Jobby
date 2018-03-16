package com.capstone.jobby.service;


import com.capstone.jobby.model.DesiredCBSkill;
import java.util.List;

public interface DesiredCBSkillService {
    List<DesiredCBSkill> findAll();
    DesiredCBSkill findById(Long id);
    List<DesiredCBSkill> findAllByID(Long id);
    void save(DesiredCBSkill candidateSkill);
    void delete(DesiredCBSkill candidateSkill);
}
