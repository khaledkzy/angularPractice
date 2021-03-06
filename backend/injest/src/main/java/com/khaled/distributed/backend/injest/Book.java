package com.khaled.distributed.backend.injest;



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

    public Book() {
    }

    /**
     * The constructor method
     * @param name
     */
    public Book(String name) {
        this.name = name;
    }

    public Book(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
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