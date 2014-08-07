package internship.issuetracker.filters;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.enums.State;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StateFilter implements SearchFilterInt<Issue> {

    private State state;

    public StateFilter(State state) {
        this.state = state;
    }

    @Override
    public Predicate buildPredicate(CriteriaQuery<Issue> cq, CriteriaBuilder cb, Root<Issue> root) {
        return cb.equal(root.get("state"), state);
    }

}
