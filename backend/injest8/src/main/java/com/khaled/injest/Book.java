package com.khaled.injest;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 🔮 is used with the findByName
     */
    private String name;

    @Column(columnDefinition="text")
    private String content;

    public Book() {
    }

    /**
     * The constructor method
     * @param name
     */
    public Book(String name) {
        this.name = name;
        this.id = id;
    }

    public Book(String name, String content) {
        this.content = content;
        this.name = name;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
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
}
