package internship.issuetracker.filters;
import internship.issuetracker.entities.Issue;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LabelFilter implements SearchFilterInt<Issue>{

	private Long labelId;

	public LabelFilter(Long labelId) {
		this.labelId = labelId;
	}
	
	@Override
	public Predicate buildPredicate(CriteriaQuery<Issue> cq, CriteriaBuilder cb, Root<Issue> root) {
		cq.subquery(null);
		
		return null;
	}	
}

	
