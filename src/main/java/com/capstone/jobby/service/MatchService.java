package com.capstone.jobby.service;

import com.capstone.jobby.model.Match;

import java.util.List;

public interface MatchService {
    List<Match> findAll();
    Match findById(Long id);
    void save(Match match);
    void delete(Match match);
}
