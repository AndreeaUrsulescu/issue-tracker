package internship.issuetracker.repository;

import filters.SearchFilterInt;
import internship.issuetracker.entities.Issue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchRepository {

	public static int itemsPerPage = 10;

	@PersistenceContext
	private EntityManager em;

	public String convert(String x){
		if("Date".equals(x)){
			return "updateDate";
		}
		
		return "updateDate";
	}

	public List<Issue> findOrderedIssues(SearchFilterInt<Issue> filter, int currentPage, String orderField, String orderType) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);
		// //
		orderField = convert(orderField);
		criteriaQuery.where(filter.buildPredicate(criteriaQuery, criteriaBuilder, root));

		// //
		if ("Descending".equals(orderType))
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderField)), criteriaBuilder.desc(root.get("id")));
		else
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderField)), criteriaBuilder.asc(root.get("id")));
		TypedQuery<Issue> query = em.createQuery(criteriaQuery);
		query.setMaxResults(itemsPerPage);
		query.setFirstResult((currentPage - 1) * itemsPerPage);
		return query.getResultList();
	}

	public int numberOfIssues(SearchFilterInt<Issue> filter) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);
		// //
		criteriaQuery.where(filter.buildPredicate(criteriaQuery, criteriaBuilder, root));
		// //
		TypedQuery<Issue> query = em.createQuery(criteriaQuery);
		return query.getResultList().size();
	}
}
