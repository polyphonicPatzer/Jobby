package com.capstone.jobby.service;

import com.capstone.jobby.model.Reference;

import java.util.List;

public interface ReferenceService {
    List<Reference> findAll();
    Reference findById(Long id);
    void save(Reference reference);
    void delete(Reference reference);
}
