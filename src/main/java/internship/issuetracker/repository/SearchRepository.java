package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.filters.AssigneeFilter;
import internship.issuetracker.filters.ContentFilter;
import internship.issuetracker.filters.CreatorFilter;
import internship.issuetracker.filters.LabelFilter;
import internship.issuetracker.filters.SearchFilterInt;
import internship.issuetracker.filters.StateFilter;
import internship.issuetracker.filters.TitleFilter;
import internship.issuetracker.pojo.MultipleSearchParameter;
import internship.issuetracker.utils.ApplicationParameters;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchRepository {

    public static final int itemsPerPage = ApplicationParameters.itemsPerPage;
    public static final String DATE = "latestUpdateDate";
    public static final String TITLE = "Title";

    @PersistenceContext
    private EntityManager em;

    public String convertToSortType(String x) {
        if (DATE.equals(x)) {
            return "lastDate";
        } else if (TITLE.equals(x)) {
            return "title";
        }
        return "lastDate";
    }

    public List<Issue> multiplePredicates(MultipleSearchParameter parameters) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
        Root<Issue> root = criteriaQuery.from(Issue.class);

        Predicate[] predicates = generratePredicates(parameters, criteriaBuilder, criteriaQuery, root);

        String orderField = convertToSortType(parameters.getSortCriteria());
        criteriaQuery.where(predicates);

        if ("Descending".equals(parameters.getSortType())){
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderField)));
        }
        else{
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderField)));
        }
        TypedQuery<Issue> query = em.createQuery(criteriaQuery);
        query.setMaxResults(itemsPerPage);
        query.setFirstResult((parameters.getPageNumber() - 1) * itemsPerPage);
        return query.getResultList();
    }

    public int numberOfIssuesMultipleSearch(MultipleSearchParameter parameters) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
        Root<Issue> root = criteriaQuery.from(Issue.class);

        Predicate[] predicates = generratePredicates(parameters, criteriaBuilder, criteriaQuery, root);

        criteriaQuery.where(predicates);
        TypedQuery<Issue> query = em.createQuery(criteriaQuery);
        return query.getResultList().size();
    }

    public Predicate[] generratePredicates(MultipleSearchParameter parameters, CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if (null != parameters.getTitle()) {
            SearchFilterInt<Issue> filter = new TitleFilter(parameters.getTitle());
            predicateList.add(filter.buildPredicate(criteriaQuery, criteriaBuilder, root));
        }
        if (null != parameters.getContent()) {
            SearchFilterInt<Issue> filter = new ContentFilter(parameters.getContent());
            predicateList.add(filter.buildPredicate(criteriaQuery, criteriaBuilder, root));
        }
        if (null != parameters.getState()) {
            SearchFilterInt<Issue> filter = new StateFilter(parameters.getState());
            predicateList.add(filter.buildPredicate(criteriaQuery, criteriaBuilder, root));
        }
        if (null != parameters.getAssignee()) {
            SearchFilterInt<Issue> filter = new AssigneeFilter(parameters.getAssignee());
            predicateList.add(filter.buildPredicate(criteriaQuery, criteriaBuilder, root));
        }
        if (null != parameters.getCreator()) {
            SearchFilterInt<Issue> filter = new CreatorFilter(parameters.getCreator());
            predicateList.add(filter.buildPredicate(criteriaQuery, criteriaBuilder, root));
        }
        if (null != parameters.getLabel()) {
            SearchFilterInt<Issue> filter = new LabelFilter(parameters.getLabel());
            predicateList.add(filter.buildPredicate(criteriaQuery, criteriaBuilder, root));
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicates = predicateList.toArray(predicates);

        return predicates;
    }
}
