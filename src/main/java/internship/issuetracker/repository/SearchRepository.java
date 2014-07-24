package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.enums.State;

import java.util.ArrayList;
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

	public int numberOfIssuesByTitle(String title) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);
		TitleFilter titleFilter = new TitleFilter(title);
		criteriaQuery.where(titleFilter.buildPredicate(criteriaQuery, criteriaBuilder, root));
		TypedQuery<Issue> query = em.createQuery(criteriaQuery);
		return query.getResultList().size();
	}

	public List<Issue> findOrderedIssuesByTitle(String title, int currentPage) {
		return findOrderedIssuesByTitleAux(title, currentPage, "updateDate", "desc");
	}

	public List<Issue> findOrderedIssuesByTitle(String title, int currentPage, String orderField) {
		return findOrderedIssuesByTitleAux(title, currentPage, orderField, "desc");
	}

	public List<Issue> findOrderedIssuesByTitle(String title, int currentPage, String orderField, String orderType) {
		return findOrderedIssuesByTitleAux(title, currentPage, orderField, orderType);
	}

	private List<Issue> findOrderedIssuesByTitleAux(String title, int currentPage, String orderField, String orderType) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);
		TitleFilter titleFilter = new TitleFilter(title);
		criteriaQuery.where(titleFilter.buildPredicate(criteriaQuery, criteriaBuilder, root));
		if ("desc".equals(orderType))
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderField)), criteriaBuilder.desc(root.get("id")));
		else
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderField)), criteriaBuilder.asc(root.get("id")));
		TypedQuery<Issue> query = em.createQuery(criteriaQuery);
		query.setMaxResults(itemsPerPage);
		query.setFirstResult((currentPage - 1) * itemsPerPage);
		return query.getResultList();
	}

	public int numberOfIssuesByContent(String content) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);
		ContentFilter contentFilter = new ContentFilter(content);
		criteriaQuery.where(contentFilter.buildPredicate(criteriaQuery, criteriaBuilder, root));

		TypedQuery<Issue> query = em.createQuery(criteriaQuery);
		return query.getResultList().size();
	}

	public List<IssuePojo> findOrderedIssuesByContent(String content, int currentPage) {
		return findOrderedIssuesByContentAux(content, currentPage, "updateDate", "desc");
	}

	public List<IssuePojo> findOrderedIssuesByContent(String content, int currentPage, String orderField) {
		return findOrderedIssuesByContentAux(content, currentPage, orderField, "desc");
	}

	public List<IssuePojo> findOrderedIssuesByContent(String content, int currentPage, String orderField, String orderType) {
		return findOrderedIssuesByContentAux(content, currentPage, orderField, orderType);
	}

	public List<IssuePojo> findOrderedIssuesByContentAux(String content, int currentPage, String orderField, String orderType) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);
		ContentFilter contentFilter = new ContentFilter(content);
		criteriaQuery.where(contentFilter.buildPredicate(criteriaQuery, criteriaBuilder, root));
		if ("desc".equals(orderType))
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderField)), criteriaBuilder.desc(root.get("id")));
		else
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderField)), criteriaBuilder.asc(root.get("id")));
		TypedQuery<Issue> query = em.createQuery(criteriaQuery);
		query.setMaxResults(itemsPerPage);
		query.setFirstResult((currentPage - 1) * itemsPerPage);
		List<Issue> issues = query.getResultList();
		List<IssuePojo> pojoIssues = new ArrayList<IssuePojo>();

		for (Issue issue : issues) {
			IssuePojo pojo = new IssuePojo(issue.getId(), issue.getOwner().getUserName(), issue.getTitle(), issue.getContent(), issue.getUpdateDate(), issue.getState());
			pojoIssues.add(pojo);
		}

		return pojoIssues;
	}

	public int numberOfIssuesByState(State state) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);
		StateFilter stateFilter = new StateFilter(state);
		criteriaQuery.where(stateFilter.buildPredicate(criteriaQuery, criteriaBuilder, root));
		TypedQuery<Issue> query = em.createQuery(criteriaQuery);
		return query.getResultList().size();
	}

	public List<Issue> findOrderedIssuesByState(State state, int currentPage) {
		return findOrderedIssuesByStateAux(state, currentPage, "updateDate", "desc");
	}

	public List<Issue> findOrderedIssuesByState(State state, int currentPage, String orderField) {
		return findOrderedIssuesByStateAux(state, currentPage, orderField, "desc");
	}

	public List<Issue> findOrderedIssuesByState(State state, int currentPage, String orderField, String orderType) {
		return findOrderedIssuesByStateAux(state, currentPage, orderField, orderType);
	}

	public List<Issue> findOrderedIssuesByStateAux(State state, int currentPage, String orderField, String orderType) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);
		StateFilter stateFilter = new StateFilter(state);
		criteriaQuery.where(stateFilter.buildPredicate(criteriaQuery, criteriaBuilder, root));
		if ("desc".equals(orderType))
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderField)), criteriaBuilder.desc(root.get("id")));
		else
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderField)), criteriaBuilder.asc(root.get("id")));
		TypedQuery<Issue> query = em.createQuery(criteriaQuery);
		query.setMaxResults(itemsPerPage);
		query.setFirstResult((currentPage - 1) * itemsPerPage);
		return query.getResultList();
	}

}
