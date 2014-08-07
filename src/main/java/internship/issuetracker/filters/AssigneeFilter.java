package internship.issuetracker.filters;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AssigneeFilter implements SearchFilterInt<Issue> {

    private String assignee;

    public AssigneeFilter(String creator) {
        this.assignee = creator.toUpperCase();
    }

    @Override
    public Predicate buildPredicate(CriteriaQuery<Issue> cq, CriteriaBuilder cb, Root<Issue> root) {
        Path<User> userPath = root.get("assignee");
        Path<String> userNamePath = userPath.get("userName");
        String aux = "%" + assignee + "%";

        if ("%%".equals(aux)) {
            return cb.conjunction();
        } else {
            return cb.like(cb.upper(userNamePath), aux);
        }
    }
}
