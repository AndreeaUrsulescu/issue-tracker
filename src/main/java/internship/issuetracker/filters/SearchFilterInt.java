package internship.issuetracker.filters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface SearchFilterInt<Type> {
    Predicate buildPredicate(CriteriaQuery<Type> cq, CriteriaBuilder cb, Root<Type> root);
}
