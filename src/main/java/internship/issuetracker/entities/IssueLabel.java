package internship.issuetracker.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = IssueLabel.FIND_LABELS_FOR_ISSUE, query = "select a from IssueLabel a inner join a.issue b where b.id = :issueId"), @NamedQuery(name = IssueLabel.FIND_ISSUE_LABEL, query = "select a from IssueLabel a inner join a.issue b inner join a.label l where b.id = :issueId and l.labelName = :labelName") })
@SuppressWarnings("serial")
@Entity
@Table(name = "issues_labels")
public class IssueLabel implements Serializable {

    public static final String FIND_LABELS_FOR_ISSUE = "IssueLabel.findLabelsForIssue";
    public static final String FIND_ISSUE_LABEL = "IssueLabel.findIssueLabel";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "issue_id")
    @ManyToOne
    private Issue issue;

    @JoinColumn(name = "label_id")
    @ManyToOne
    private Label label;

    public Long getId() {
        return id;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
