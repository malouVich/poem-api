package app.Entity;


import app.DTO.PoemDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Poem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private int firstPublished;
    private String originalLanguage;
    private String poemStyle;
    @Column(length = 4000)
    private String theme;
    @Column(length = 30000)
    private String poemText;

    public Poem(PoemDTO poemDTO) {
        this.title = poemDTO.getTitle();
        this.author = poemDTO.getAuthor();
        this.firstPublished = poemDTO.getFirstPublished();
        this.originalLanguage = poemDTO.getOriginalLanguage();
        this.poemStyle = poemDTO.getPoemStyle();
        this.theme = poemDTO.getTheme();
        this.poemText = poemDTO.getPoemText();
    }
}
