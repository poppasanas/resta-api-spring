package com.ss.restaapispring.service.impl;

import com.ss.restaapispring.entity.QuoteEntity;
import com.ss.restaapispring.entity.VotesEntity;
import com.ss.restaapispring.repository.QuoteRepository;
import com.ss.restaapispring.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuoteServiceImpl implements QuoteService{

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository){this.quoteRepository = quoteRepository; }

    @Override
    public List<QuoteEntity> findAllQuotes(){
        return quoteRepository.findAll();
    }

    @Override
    public Optional<QuoteEntity> findById(Long id){
        return quoteRepository.findById(id);
    }

    @Override
    public QuoteEntity saveQuote(QuoteEntity quoteEntity){
        quoteEntity.setDate();
        quoteRepository.save(quoteEntity);
        quoteEntity.setVotes(quoteEntity.getId());
        quoteRepository.save(quoteEntity);
        return quoteEntity;
    }

    @Override
    public QuoteEntity updateQuote(QuoteEntity quoteEntity){
        return quoteRepository.save(quoteEntity);
    }

    @Override
    public void deleteQuote(Long id){
        quoteRepository.deleteById(id);
    }

    @Override
    //Gets List of all Quotes and gets a random entity from that List
    //I'm getting random quote this way because we may delete quote with included id
    //With this method we'll guarantee we get random quote
    public QuoteEntity getRandomQuote(){
        List<QuoteEntity> quoteList = findAllQuotes();
        Integer randId = ThreadLocalRandom.current().nextInt(1, quoteList.size());
        return quoteList.get(randId);
    }

}
