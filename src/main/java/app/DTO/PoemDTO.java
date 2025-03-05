package app.DTO;

import app.Entity.Poem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoemDTO {
    @JsonIgnore
    private int id;
    private String title;
    private String author;
    @JsonProperty("first_published")
    private int firstPublished;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("poem_style")
    private String poemStyle;
    private String theme;
    @JsonProperty("poem")
    private String poemText;


    public PoemDTO(Poem poem){
        this.id = poem.getId();
        this.title = poem.getTitle();
        this.author = poem.getAuthor();
        this.firstPublished = poem.getFirstPublished();
        this.originalLanguage = poem.getOriginalLanguage();
        this.poemStyle = poem.getPoemStyle();
        this.theme = poem.getTheme();
        this.poemText = poem.getPoemText();
    }
}
