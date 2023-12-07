package com.ss.restaapispring.entity;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class VotesEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "votes")
    private Long votes = (long)0;


    @Column(name = "quote_id")
    private Long quoteId;

    public VotesEntity(Long quoteId){
        this.quoteId = quoteId;
        this.id = quoteId;
    }

    public VotesEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }
}
