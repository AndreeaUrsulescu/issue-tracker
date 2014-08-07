package internship.issuetracker.repository;

import internship.issuetracker.entities.Email;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailRepository {

    @PersistenceContext
    private EntityManager em;

    public void create(Email email) {
        em.persist(email);
    }
}
