package app.DAO;

import app.DTO.PoemDTO;
import app.Entity.Poem;
import app.controllers.PoemController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class PoemDAO {

    private static EntityManagerFactory emf;
    private static PoemDAO instance;


    private PoemDAO() {
    }

    public static PoemDAO getInstance(EntityManagerFactory _emf) {
        if (emf == null) {
            emf = _emf;
            instance = new PoemDAO();
        }
        return instance;
    }

    public PoemDTO save(PoemDTO poemDTO) {
        // Save the poemDTO to the database

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Poem poem = new Poem();
            poem.setId(poemDTO.getId());
            poem.setTitle(poemDTO.getTitle());
            poem.setAuthor(poemDTO.getAuthor());
            poem.setFirstPublished(poemDTO.getFirstPublished());
            poem.setOriginalLanguage(poemDTO.getOriginalLanguage());
            poem.setPoemStyle(poemDTO.getPoemStyle());
            poem.setTheme(poemDTO.getTheme());
            poem.setPoemText(poemDTO.getPoemText());

            em.merge(poem);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return poemDTO;
    }

    public PoemDTO getById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Poem> query = em.createQuery("SELECT p FROM Poem p WHERE p.id = :id", Poem.class);
            query.setParameter("id", id);
            Poem poem = query.getSingleResult();  // Brug getSingleResult() for at få et enkelt resultat

            // Konverter Poem til PoemDTO
            PoemDTO poemDTO = new PoemDTO();
            poemDTO.setId(poem.getId());
            poemDTO.setTitle(poem.getTitle());
            poemDTO.setFirstPublished(poem.getFirstPublished());
            poemDTO.setAuthor(poem.getAuthor());
            poemDTO.setPoemText(poem.getPoemText());
            poemDTO.setTheme(poem.getTheme());
            poemDTO.setPoemStyle(poem.getPoemStyle());
            poemDTO.setOriginalLanguage(poem.getOriginalLanguage());

            return poemDTO; // Returner PoemDTO objektet
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Håndter fejlsituationer og returner null
        }
    }

    public PoemDTO updateById(int id, PoemDTO poemDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Poem poem = em.find(Poem.class, id);

            if (poem == null) {
                return null;
            }

            poem.setAuthor(poem.getAuthor());
            poem.setPoemText(poem.getPoemText());
            poem.setPoemStyle(poem.getPoemStyle());
            poem.setTheme(poem.getTheme());
            poem.setTitle(poem.getTitle());
            poem.setFirstPublished(poem.getFirstPublished());
            poem.setOriginalLanguage(poem.getOriginalLanguage());

            em.merge(poem);

            em.getTransaction().commit();

            PoemDTO updatePoem = new PoemDTO();
            updatePoem.setAuthor(poem.getAuthor());
            updatePoem.setPoemText(poem.getPoemText());
            updatePoem.setPoemStyle(poem.getPoemStyle());
            updatePoem.setTheme(poem.getTheme());
            updatePoem.setTitle(poem.getTitle());
            updatePoem.setFirstPublished(poem.getFirstPublished());
            updatePoem.setOriginalLanguage(poem.getOriginalLanguage());

            return updatePoem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


