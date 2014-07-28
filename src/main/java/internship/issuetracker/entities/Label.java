package internship.issuetracker.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Labels")
public class Label implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "label_name", nullable = false, unique = true)
	@Size(min = 3, max = 20)
	@Pattern(regexp= "^[a-zA-Z]{3,20}$")
	private String labelName;
	
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable (name = "issue_labels", joinColumns = @JoinColumn(name = "id_label"), inverseJoinColumns  = @JoinColumn (name = "id_issue"))
	private Set<Issue> issues = new HashSet <Issue>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}


	
	
}

