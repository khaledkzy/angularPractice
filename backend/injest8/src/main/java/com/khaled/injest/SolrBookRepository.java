package com.khaled.injest;

import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrBookRepository extends SolrCrudRepository<SolrBook,Long>{
    public List<SolrBook> findByName(String name);
}
