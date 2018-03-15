package com.capstone.jobby.service;

import com.capstone.jobby.dao.DesiredCBSkillDao;
import com.capstone.jobby.dao.DesiredTechSkillDao;
import com.capstone.jobby.model.DesiredCBSkill;
import com.capstone.jobby.model.DesiredTechSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesiredTechSkillServiceImpl implements DesiredTechSkillService {

    @Autowired
    private DesiredTechSkillDao desiredTechSkillDao;

    @Override
    public List<DesiredTechSkill> findAll() {
        return desiredTechSkillDao.findAll();
    }

    @Override
    public DesiredTechSkill findById(Long id) {
        return desiredTechSkillDao.findById(id);
    }

    @Override
    public void save(DesiredTechSkill desiredTechSkill) {
        desiredTechSkillDao.save(desiredTechSkill);
    }

    @Override
    public void delete(DesiredTechSkill desiredTechSkill) {
        desiredTechSkillDao.delete(desiredTechSkill);
    }
}
