package internship.issuetracker.filters;

import internship.issuetracker.entities.Issue;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ContentFilter implements SearchFilterInt<Issue> {

    private String content;

    public ContentFilter(String content) {
        this.content = content.toLowerCase();
    }

    @Override
    public Predicate buildPredicate(CriteriaQuery<Issue> cq, CriteriaBuilder cb, Root<Issue> root) {

        String pattern = "%" + content + "%";
        Path<String> path = root.get("searchContent");
        Predicate predicate = cb.like(cb.lower(path), pattern);
        return predicate;

    }
}
