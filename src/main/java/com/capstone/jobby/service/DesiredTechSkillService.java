package com.capstone.jobby.service;

import com.capstone.jobby.model.DesiredTechSkill;

import java.util.List;

public interface DesiredTechSkillService {
    List<DesiredTechSkill> findAll();
    List<DesiredTechSkill> findAllByID(Long id);
    DesiredTechSkill findById(Long id);
    void save(DesiredTechSkill desiredTechSkill);
    void delete(DesiredTechSkill desiredTechSkill);
}
