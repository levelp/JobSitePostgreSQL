package levelp.model;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
@Transactional
public class ResumeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Resume save(Resume resume) {
        entityManager.persist(resume);
        return resume;
    }

    public List<Resume> findByEmail(String email) {
        try {
            return entityManager.createNamedQuery("Resume.findByEmail",
                    Resume.class)
                    .setParameter("email", email)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }
}
