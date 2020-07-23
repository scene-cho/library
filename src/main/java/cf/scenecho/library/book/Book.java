package cf.scenecho.library.book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private Boolean available;
}