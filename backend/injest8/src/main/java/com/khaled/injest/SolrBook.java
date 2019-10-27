package com.khaled.injest;




import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "documentstore")
public class SolrBook {
    @Id
    @Indexed(name = "id", type = "long")
    private Long id;
    @Indexed(name = "name", type = "string")
    private String name;
    @Indexed(name = "content", type = "string")
    private String content;

    public SolrBook(String name) {
        this.name = name;
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}




