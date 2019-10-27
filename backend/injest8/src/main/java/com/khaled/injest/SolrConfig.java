package com.khaled.injest;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(
        basePackages = "com.khaled.injest")
@ComponentScan
public class SolrConfig {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    SolrBookRepository solrBookRepository;

    @Value("${solrLink}")
    private String solrLink;
    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder("http://127.0.0.1:8983/solr").build();
    }
    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
    @Bean
    public Boolean x() {
        Iterable<Book> postgresBooks =  bookRepository.findAll();
        for(Book singleBook : postgresBooks) {
            solrBookRepository.save(new SolrBook(singleBook.getName()));
        }
        return true;
    }

}

