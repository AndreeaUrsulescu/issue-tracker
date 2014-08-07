package internship.issuetracker.filters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface SearchFilterInt<TYPE> {
    Predicate buildPredicate(CriteriaQuery<TYPE> cq, CriteriaBuilder cb, Root<TYPE> root);
}
