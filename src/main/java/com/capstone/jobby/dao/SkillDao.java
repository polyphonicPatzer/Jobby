package com.capstone.jobby.dao;

import com.capstone.jobby.model.Skill;

import java.util.List;

public interface SkillDao {
    List<Skill> findAll();
    Skill findById(Long id);
    void save(Skill skill);
    void delete(Skill skill);
}
