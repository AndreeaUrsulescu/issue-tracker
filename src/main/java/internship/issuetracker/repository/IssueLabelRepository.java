package internship.issuetracker.repository;

import java.util.ArrayList;
import java.util.List;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.IssueLabel;
import internship.issuetracker.entities.Label;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IssueLabelRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private LabelRepository labelRepository;
	
	public void create(IssueLabel issueLabel) {
		em.persist(issueLabel);
	}
	
	public void delete(Long id) {
		IssueLabel issueLabel = em.find(IssueLabel.class, id);
		em.remove(issueLabel);
	}
	
	public List<Label> getLabelsForIssue(Long id) {
		Issue issue = issueRepository.findIssue(id);
		TypedQuery<IssueLabel> query = em.createNamedQuery(IssueLabel.FIND_LABELS_FOR_ISSUE,
				IssueLabel.class);

		List<IssueLabel> result = query.setParameter("issue", issue).getResultList();
		List<Label> labels = new ArrayList<Label>();
		
		for (IssueLabel issueLabel : result) {
			labels.add(issueLabel.getLabel());
		}
		
		return labels;
	}
	
	public IssueLabel findIssueLabel(Issue issue, Label label) {
		TypedQuery<IssueLabel> query = em.createNamedQuery(IssueLabel.FIND_ISSUE_LABEL, IssueLabel.class);
		issue = issueRepository.findIssue(issue.getId());
		label = labelRepository.findLabelByName(label.getLabelName());
		
		query.setParameter("issue", issue);
		query.setParameter("label", label);
		
		return query.getResultList().get(0);
	}
	
	public void removeLabelFromIssue(Long issueId, String labelName) {
		Issue issue = issueRepository.findIssue(issueId);
		Label label = labelRepository.findLabelByName(labelName);
		IssueLabel issueLabel = this.findIssueLabel(issue, label);
		this.delete(issueLabel.getId());
	}
}
