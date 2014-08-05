package internship.issuetracker.service;

import internship.issuetracker.entities.Attachment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.repository.AttachmentRepository;
import internship.issuetracker.repository.IssueRepository;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentService {
	
	/*private static final Logger log = Logger.getLogger(CommentService.class
			.getName());*/
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	public Long uploadFile(Long issueId, MultipartFile mpf) {
		Attachment attachment = new Attachment();
		attachment.setFilename(mpf.getOriginalFilename());
		attachment.setCreationDate(new Date());
		attachment.setContentType(mpf.getContentType());
		
		try {
			attachment.setContent(mpf.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Issue issue = issueRepository.findIssue(issueId);
		attachmentRepository.create(attachment);
		issue.getAttachments().add(attachment);
		issueRepository.update(issue);
		return attachment.getId();
	}
	
	public Attachment getAttachment(Long id) {
		return attachmentRepository.getAttachment(id);
	}
}
