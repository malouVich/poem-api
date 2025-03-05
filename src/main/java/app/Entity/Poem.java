package app.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private int firstPublished;
    private String originalLanguage;
    private String poemStyle;
    private String theme;
    private String poemText;
}
