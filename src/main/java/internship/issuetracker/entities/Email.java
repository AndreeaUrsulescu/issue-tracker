package internship.issuetracker.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@Entity
@Table(name = "Emails")
public class Email implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "mailTo", nullable = false)
	@Size(min = 1, max = 150)
	private String to;

	@Column(name = "mailFrom", nullable = false)
	@Size(min = 1, max = 50)
	private String from;

	@Column(name = "content", nullable = false)
	@Size(min = 1, max = 500)
	private String content;

	@Column(name = "subject", nullable = false)
	@Size(min = 1, max = 50)
	private String subject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(to).append(from)
				.append(content).append(subject).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Email) {
			Email email = (Email) obj;
			return new EqualsBuilder().append(this.to, email.to)
						.append(this.from, email.from)
						.append(this.content, email.content)
						.append(this.subject, email.subject).isEquals();
		}
		return false;
	}
}
