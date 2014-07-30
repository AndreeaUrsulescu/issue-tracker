package internship.issuetracker.filters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import internship.issuetracker.entities.Issue;

public class ContentFilter implements SearchFilterInt<Issue> {

    private String content;

    public ContentFilter(String content) {
	this.content = content.toUpperCase();
    }

    @Override
    public Predicate buildPredicate(CriteriaQuery<Issue> cq,
	    CriteriaBuilder cb, Root<Issue> root) {

	String pattern = "%" + content + "%";
	Path<String> path = root.get("content");
	Predicate predicate = cb.like(cb.upper(path), pattern);
	return predicate;
    }
}