package app;

import app.DAO.PoemDAO;
import app.DTO.PoemDTO;
import app.config.HibernateConfig;
import app.controllers.PoemController;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private static final PoemDAO poemDAO = PoemDAO.getInstance(emf);

    public static void main(String[] args) {

        PoemController poemController = new PoemController();

        Javalin.create(config ->{
            config.router.contextPath = "/api";
            config.router.apiBuilder(() ->
            {

                path("poem", () ->{

//                    post("/", (ctx) -> {
//                        // Hent JSON-body og konverter til PoemDTO
//                        PoemDTO poemDTO = ctx.bodyAsClass(PoemDTO.class);
//
//                        // Gem digtet i databasen
//                        poemController.createPoem(poemDTO);
//
//                        // Returner succesmeddelelse
//                        ctx.status(201).json(poemDTO);
//                    });

                    // API endpoint for at hente alle digte
                    get("/", (ctx) -> {
                        ctx.json(poemController.getAllPoems());
                    });
                    post("/", (ctx) -> {
                        PoemDTO newPoem = ctx.bodyAsClass(PoemDTO.class);
                        PoemDTO returnedPoem = poemDAO.save(newPoem);
                        ctx.json(returnedPoem);
                    });
                    get("/{id}",(ctx)-> {
                        try {
                            PoemDTO id = poemDAO.getById(Integer.parseInt(ctx.pathParam("id")));
                            ctx.json(id);

                        }catch (Exception e){
                            ctx.status(404).result("Poem not found");
                        }
                    });


                });
            });
        }).start(7070);
    }
}