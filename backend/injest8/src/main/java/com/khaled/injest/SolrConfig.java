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

    @Value("${solrLink}")
    private String solrLink;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    SolrBookRepository solrBookRepository;


    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrLink).build();
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

