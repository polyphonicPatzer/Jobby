package com.capstone.jobby.service;

import com.capstone.jobby.dao.SkillDao;
import com.capstone.jobby.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements  SkillService {
    @Autowired
    private SkillDao skillDao;

    @Override
    public List<Skill> findAll() {
        return skillDao.findAll();
    }

    @Override
    public Skill findById(Long id) {
        return skillDao.findById(id);
    }

    @Override
    public void save(Skill skill) {
        skillDao.save(skill);
    }

    @Override
    public void delete(Skill skill) {
        skillDao.delete(skill);
    }
}
