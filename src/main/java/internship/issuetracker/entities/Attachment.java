package internship.issuetracker.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
@SuppressWarnings("serial")
@Entity
@Table(name="Attachments")
public class Attachment implements Serializable{
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
	@Column(name="filename")
    private String filename;
 
    @Column(name="content")
    @Lob
    private byte[] content;
     
    @Column(name="content_type")
    private String contentType;
     
    @Temporal(TemporalType.TIME)
    @Column(name="creation_date")
    private Date creationDate;
     
    //Getter and Setter methods
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(filename).append(content)
				.append(contentType).append(creationDate).toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Attachment) {
			Attachment attachment = (Attachment) obj;
			return new EqualsBuilder().append(this.filename, attachment.filename)
					.append(this.content, attachment.content)
					.append(this.contentType, attachment.contentType)
					.append(this.creationDate, attachment.creationDate).isEquals();
		}
		return false;
	}
}