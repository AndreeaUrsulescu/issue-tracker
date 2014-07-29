package internship.issuetracker.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "issue_label")
public class IssueLabel implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
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
