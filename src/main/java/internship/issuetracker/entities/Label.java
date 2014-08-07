package internship.issuetracker.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@NamedQueries({ @NamedQuery(name = Label.FIND_BY_NAME, query = "select a from Label a where a.labelName = :labelName"), @NamedQuery(name = Label.FIND_ALL, query = "select a from Label a") })
@Entity
@Table(name = "Labels")
public class Label implements Serializable {

    public static final String FIND_BY_NAME = "Label.findByName";
    public static final String FIND_ALL = "Label.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "label_name", nullable = false, unique = true)
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$")
    private String labelName;

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(labelName).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Label) {
            Label label = (Label) obj;
            return new EqualsBuilder().append(this.labelName, label.labelName).isEquals();
        }
        return false;
    }
}
