package internship.issuetracker.filters;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.IssueLabel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

public class LabelFilter implements SearchFilterInt<Issue> {

    private Long id;

    public LabelFilter(Long id) {
        super();
        this.id = id;
    }

    @Override
    public Predicate buildPredicate(CriteriaQuery<Issue> cq, CriteriaBuilder cb, Root<Issue> root) {
        Subquery<Issue> subquery = cq.subquery(Issue.class);
        Root<IssueLabel> issueLabel = subquery.from(IssueLabel.class);
        subquery.where(cb.equal(issueLabel.get("label"), id));
        Path<Issue> issue = issueLabel.get("issue");
        subquery.select(issue);
        return root.in(subquery);
    }

}
