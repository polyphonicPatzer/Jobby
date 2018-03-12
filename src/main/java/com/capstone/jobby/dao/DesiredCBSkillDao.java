package com.capstone.jobby.dao;

import com.capstone.jobby.model.DesiredCBSkill;
import java.util.List;

public interface DesiredCBSkillDao {
    List<DesiredCBSkill> findAll();
    DesiredCBSkill findById(Long id);
    void save(DesiredCBSkill candidateSkill);
    void delete(DesiredCBSkill candidateSkill);
}
