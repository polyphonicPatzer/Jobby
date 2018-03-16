package com.capstone.jobby.service;

import com.capstone.jobby.dao.DesiredCBSkillDao;
import com.capstone.jobby.model.DesiredCBSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesiredCBSkillServiceImpl implements DesiredCBSkillService {

    @Autowired
    private DesiredCBSkillDao desiredCBSkillDao;

    @Override
    public List<DesiredCBSkill> findAll() {
        return desiredCBSkillDao.findAll();
    }

    @Override
    public List<DesiredCBSkill> findAllByID(Long id) {return desiredCBSkillDao.findAllById(id);}

    @Override
    public DesiredCBSkill findById(Long id) {
        return desiredCBSkillDao.findById(id);
    }

    @Override
    public void save(DesiredCBSkill desiredCBSkill) {
        desiredCBSkillDao.save(desiredCBSkill);
    }

    @Override
    public void delete(DesiredCBSkill desiredCBSkill) {
        desiredCBSkillDao.delete(desiredCBSkill);
    }
}
