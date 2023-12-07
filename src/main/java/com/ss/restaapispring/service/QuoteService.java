package com.ss.restaapispring.service;

import com.ss.restaapispring.entity.QuoteEntity;

import java.util.List;
import java.util.Optional;

public interface QuoteService {
    List<QuoteEntity> findAllQuotes();
    Optional<QuoteEntity> findById(Long id);
    QuoteEntity saveQuote(QuoteEntity quoteEntity);
    QuoteEntity updateQuote(QuoteEntity quoteEntity);
    void deleteQuote(Long id);
    QuoteEntity getRandomQuote();
}
