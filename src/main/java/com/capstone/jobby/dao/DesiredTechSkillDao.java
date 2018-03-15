package com.capstone.jobby.dao;

import com.capstone.jobby.model.DesiredTechSkill;

import java.util.List;

public interface DesiredTechSkillDao {
    List<DesiredTechSkill> findAll();
    DesiredTechSkill findById(Long id);
    void save(DesiredTechSkill candidateSkill);
    void delete(DesiredTechSkill candidateSkill);
}
