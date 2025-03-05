package app.controllers;

import app.DAO.PoemDAO;
import app.DTO.PoemDTO;
import app.Entity.Poem;
import app.config.HibernateConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoemController {
    private final PoemDAO poemDAO;

    private List<PoemDTO> poems = new ArrayList<>();

    public PoemController() {
        this.poemDAO = PoemDAO.getInstance(HibernateConfig.getEntityManagerFactory());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Læs JSON-filen og konverter den direkte til en liste af Poem-objekter
            this.poems = objectMapper.readValue(new File("poems.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, PoemDTO.class));

//            for (PoemDTO poemDTO : poems) {
//                createPoem(poemDTO);  // Kald metoden for at gemme digtet
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hent alle digte
    public List<PoemDTO> getAllPoems() {
        return poems;
    }


    public void createPoem(PoemDTO poemDTO) {
        poemDAO.save(poemDTO); // Kalder save-metoden på DAO
    }

    public PoemDTO createNewPoem(PoemDTO poemDTO){
        poemDAO.save(poemDTO);
        return poemDTO;
    }

}

