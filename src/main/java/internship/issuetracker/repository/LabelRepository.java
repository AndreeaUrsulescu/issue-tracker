package internship.issuetracker.repository;

import internship.issuetracker.entities.Label;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LabelRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Label> findLabels() {
		TypedQuery<Label> query = em.createNamedQuery(Label.FIND_ALL,
				Label.class);
		return query.getResultList();
	}

	public void create(Label label) {
		em.persist(label);
	}

	public void update(Label label) {
		em.merge(label);
	}

	public Label findLabelByName(String labelName) {
		TypedQuery<Label> query = em.createNamedQuery(Label.FIND_BY_NAME,
				Label.class);
		List<Label>labels = query.setParameter("labelName", labelName).getResultList();
		//we need this check in case this is a new label
		if(labels.size() > 0){
		    return labels.get(0);
		}
		return (new Label());
	}
}
