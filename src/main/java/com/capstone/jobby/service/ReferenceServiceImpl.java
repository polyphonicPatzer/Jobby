package com.capstone.jobby.service;

import com.capstone.jobby.dao.ReferenceDao;
import com.capstone.jobby.model.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceServiceImpl implements  ReferenceService {
    @Autowired
    private ReferenceDao referenceDao;

    @Override
    public List<Reference> findAll() {
        return referenceDao.findAll();
    }

    @Override
    public Reference findById(Long id) {
        return referenceDao.findById(id);
    }

    @Override
    public void save(Reference reference) {
        referenceDao.save(reference);
    }

    @Override
    public void delete(Reference reference) {
        referenceDao.delete(reference);
    }
}
