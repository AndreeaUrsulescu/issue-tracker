package internship.issuetracker.filters;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CreatorFilter implements SearchFilterInt<Issue> {

    private String creator;

    public CreatorFilter(String creator) {
        this.creator = creator.toUpperCase();
    }

    @Override
    public Predicate buildPredicate(CriteriaQuery<Issue> cq, CriteriaBuilder cb, Root<Issue> root) {
        Path<User> userPath = root.get("owner");
        Path<String> userNamePath = userPath.get("userName");
        return cb.like(cb.upper(userNamePath), "%" + creator + "%");
    }
}
