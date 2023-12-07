package com.ss.restaapispring.controller;

import com.ss.restaapispring.entity.VotesEntity;
import com.ss.restaapispring.service.QuoteService;
import com.ss.restaapispring.service.VotesService;
import org.springframework.web.bind.annotation.*;
import com.ss.restaapispring.entity.QuoteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;
    private final VotesService votesService;

    public QuoteController(QuoteService quoteService, VotesService votesService)
    {
        this.quoteService = quoteService;
        this.votesService = votesService;
    }

    @GetMapping
    //Gets list of all existing quotes
    public List<QuoteEntity> findAllQuotes(){
        return quoteService.findAllQuotes();
    }

    @GetMapping("/votes")
    public List<VotesEntity> getAllVotes(){
        return votesService.getAllVotes();
    }
    @GetMapping("/{id}")
    //Finds quote by id, with check if it exists or not
    public Optional<QuoteEntity> findQuoteById(@PathVariable("id") Long id){
        return quoteService.findById(id);
    }

    @PostMapping
    //Creates new quote with given information and creates new Votes entity with same id.
    //Requires: content, creatorId
    public QuoteEntity saveQuote(@RequestBody QuoteEntity quoteEntity){
        quoteService.saveQuote(quoteEntity);
        VotesEntity votesEntity = new VotesEntity(quoteEntity.getId());
        votesService.saveVotes(votesEntity);
        return quoteEntity;
    }

    @PutMapping
    //Updates quote with given information
    public QuoteEntity updateQuote(@RequestBody QuoteEntity quoteEntity){
        return quoteService.updateQuote(quoteEntity);
    }

    @DeleteMapping("/{id}")
    //Deletes quote from database
    //Not quite sure if we keep history of votes for the deleted quotes
    //My decision was to remove them for easier finding quotes for top10 and flop10
    public void deleteQuote(@PathVariable("id") Long id){
        votesService.deleteVotes(id);
        quoteService.deleteQuote(id);
    }

    @GetMapping("/random")
    //Posts random quote from existing ones
    public QuoteEntity getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @PostMapping("/{id}/upvote")
    //Gives one vote to given quote
    public Optional<VotesEntity> upvoteQuote(@PathVariable("id") Long id){
        Optional<VotesEntity> votesEntity = votesService.findById(id);
        votesEntity.ifPresent(votesEntity1 -> votesService.upvoteQuote(votesEntity1));
        return votesEntity;
    }

    @PostMapping("/{id}/downvote")
    //Removes one vote from given quote
    public Optional<VotesEntity> downvoteQuote(@PathVariable("id") Long id){
        Optional<VotesEntity> votesEntity = votesService.findById(id);
        votesEntity.ifPresent(votesEntity1 -> votesService.downvoteQuote(votesEntity1));
        return votesEntity;
    }

    @GetMapping("/top10")
    //Gets 10 best voted quotes, then checks if those are existant in our list of quotes
    //Also if there is not enough quotes for some reason, we're getting all existant quotes
    //Sorted by votes in ascendant order
    public List<QuoteEntity> findTop10(){
        List<VotesEntity> entityList = votesService.getTopTen();
        List<QuoteEntity> quoteEntityList = new ArrayList<>();
        for(Integer i = 0; i < entityList.size(); i++){
            Long id = entityList.get(i).getQuoteId();
            Optional<QuoteEntity> quoteEntity = findQuoteById(id);
            quoteEntity.ifPresent(quoteEntity1 -> quoteEntityList.add(quoteEntity1));
        }
        return quoteEntityList;
    }

    @GetMapping("/flop10")
    //Gets 10 worst voted quotes, then checks if those are existant in our list of quotes
    //Also if there is not enough quotes for some reason, we're getting all existant quotes
    //Sorted by votes in descendant order
    public List<QuoteEntity> findFlop10(){
        List<VotesEntity> entityList = votesService.getFlopTen();
        List<QuoteEntity> quoteEntityList = new ArrayList<>();
        for(Integer i = 0; i < entityList.size(); i++){
            Long id = entityList.get(i).getQuoteId();
            Optional<QuoteEntity> quoteEntity = findQuoteById(id);
            quoteEntity.ifPresent(quoteEntity1 -> quoteEntityList.add(quoteEntity1));
        }
        return quoteEntityList;
    }

    //I don't know how to create graph of evolution for votes, but I'd like to implement it.
    //I hope that amount of code is enough for completing trial task
}
