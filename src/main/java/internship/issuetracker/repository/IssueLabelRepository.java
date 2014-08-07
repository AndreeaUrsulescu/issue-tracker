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
        TypedQuery<IssueLabel> query = em.createNamedQuery(IssueLabel.FIND_LABELS_FOR_ISSUE, IssueLabel.class);

        List<IssueLabel> result = query.setParameter("issueId", id).getResultList();
        List<Label> labels = new ArrayList<Label>();

        for (IssueLabel issueLabel : result) {
            labels.add(issueLabel.getLabel());
        }

        return labels;
    }

    public IssueLabel findIssueLabel(Long issueId, String labelName) {
        TypedQuery<IssueLabel> query = em.createNamedQuery(IssueLabel.FIND_ISSUE_LABEL, IssueLabel.class);

        query.setParameter("issueId", issueId);
        query.setParameter("labelName", labelName);

        List<IssueLabel> result = query.getResultList();

        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public void removeLabelFromIssue(Long issueId, String labelName) {
        IssueLabel issueLabel = this.findIssueLabel(issueId, labelName);
        if (issueLabel != null) {
            this.delete(issueLabel.getId());
        }
    }

    public void addLabelForIssue(Long idIssue, String labelName) {
        Issue issue = issueRepository.findIssue(idIssue);
        Label label = labelRepository.findLabelByName(labelName);
        IssueLabel issueLabel = new IssueLabel();
        issueLabel.setIssue(issue);
        issueLabel.setLabel(label);
        this.create(issueLabel);

    }
}
