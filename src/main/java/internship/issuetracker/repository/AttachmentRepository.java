package internship.issuetracker.repository;

import internship.issuetracker.entities.Attachment;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AttachmentRepository {
	private static final Logger log = Logger.getLogger(ActivationRepository.class.getName());
	@PersistenceContext
	private EntityManager em;

	/*
	 * @Autowired private IssueRepository issueRepository;
	 */

	public void create(Attachment attachment) {
		em.persist(attachment);
		log.log(Level.INFO, "An attachment was persisted!");
	}

	public Attachment getAttachment(Long attachmentId) {
		Attachment attachment = new Attachment();
		attachment = em.find(Attachment.class, attachmentId);
		return attachment;
	}

	public void remove(Long attachmentId) {
		Attachment attachment = this.getAttachment(attachmentId);
		em.remove(attachment);
	}

	/*
	 * public void save(Attachment attachment, MultipartFile file, Long issueId)
	 * {
	 * 
	 * Session session = (Session)em.getDelegate(); Blob blob =
	 * session.getLobHelper().createBlob(file.getInputStream(), file.getSize());
	 * 
	 * 
	 * Issue issue = issueRepository.findIssue(issueId);
	 * this.create(attachment); issue.getAttachments().add(attachment);
	 * issueRepository.update(issue); }
	 */
}