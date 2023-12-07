package com.ss.restaapispring.service.impl;

import com.ss.restaapispring.entity.QuoteEntity;
import com.ss.restaapispring.entity.VotesEntity;
import com.ss.restaapispring.repository.VotesRepository;
import com.ss.restaapispring.service.VotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VotesServiceImpl implements VotesService {

    private final VotesRepository votesRepository;

    @Autowired
    public VotesServiceImpl(VotesRepository votesRepository) {this.votesRepository = votesRepository;}

    @Override
    public List<VotesEntity> getAllVotes(){
        return votesRepository.findAll();
    }
    @Override
    public Optional<VotesEntity> findById(Long id){
        return votesRepository.findById(id);
    }

    @Override
    public VotesEntity upvoteQuote(VotesEntity votesEntity){
        votesEntity.setVotes(votesEntity.getVotes() + 1l);
        return votesRepository.save(votesEntity);
    }

    @Override
    public VotesEntity downvoteQuote(VotesEntity votesEntity){
        votesEntity.setVotes(votesEntity.getVotes() - 1);
        return votesRepository.save(votesEntity);
    }

    @Override
    public VotesEntity saveVotes(VotesEntity votesEntity){
        return votesRepository.save(votesEntity);
    }
    @Override
    //We sort top10 quote id's by getting 10 most upvoted quotes
    public List<VotesEntity> getTopTen(){
        List<VotesEntity> entities = votesRepository.findAll(Sort.by(Sort.Direction.ASC, "votes"));
        List<VotesEntity> topTen;
        if(entities.size() < 10){
            topTen = entities.subList(0, entities.size());
        }
        else {
            topTen = entities.subList(0, 10);
        }
        return topTen;
    }
    @Override
    //We sort top10 quote id's by getting 10 most downvoted quotes
    public List<VotesEntity> getFlopTen(){
        List<VotesEntity> entities = votesRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
        List<VotesEntity> flopTen;
        if(entities.size() < 10){
            flopTen = entities.subList(0, entities.size());
        }
        else {
            flopTen = entities.subList(0, 10);
        }
        return flopTen;
    }
    @Override
    public void deleteVotes(Long id){
        votesRepository.deleteById(id);
    }
}
