package com.capstone.jobby.service;

import com.capstone.jobby.model.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> findAll();
    Skill findById(Long id);
    void save(Skill skill);
    void delete(Skill skill);
}
