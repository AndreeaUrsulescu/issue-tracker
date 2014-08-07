package internship.issuetracker.filters;

import internship.issuetracker.entities.Issue;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TitleFilter implements SearchFilterInt<Issue> {

    private String title;

    public TitleFilter(String title) {
        this.title = title.toUpperCase();
    }

    @Override
    public Predicate buildPredicate(CriteriaQuery<Issue> cq, CriteriaBuilder cb, Root<Issue> root) {
        String pattern = "%" + title + "%";
        Path<String> path = root.get("title");
        return cb.like(cb.upper(path), pattern);
    }

}
