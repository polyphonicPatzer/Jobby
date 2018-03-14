package com.capstone.jobby.dao;

import com.capstone.jobby.model.Reference;

import java.util.List;

public interface ReferenceDao {
    List<Reference> findAll();
    Reference findById(Long id);
    void save(Reference reference);
    void delete(Reference reference);
}
