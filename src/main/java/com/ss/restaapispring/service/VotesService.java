package com.ss.restaapispring.service;

import com.ss.restaapispring.entity.VotesEntity;

import java.util.List;
import java.util.Optional;

public interface VotesService {
    Optional<VotesEntity> findById(Long id);
    List<VotesEntity> getAllVotes();
    VotesEntity saveVotes(VotesEntity votesEntity);
    VotesEntity upvoteQuote(VotesEntity votesEntity);
    VotesEntity downvoteQuote(VotesEntity votesEntity);
    void deleteVotes(Long id);

    List<VotesEntity> getTopTen();
    List<VotesEntity> getFlopTen();
}
