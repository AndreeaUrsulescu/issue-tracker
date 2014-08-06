package internship.issuetracker.service;

import internship.issuetracker.entities.Attachment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.AttachmentPojo;
import internship.issuetracker.repository.AttachmentRepository;
import internship.issuetracker.repository.IssueRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentService {
	
	private static final Logger LOG = Logger.getLogger(AttachmentService.class	.getName());
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	public void uploadFile(Long issueId, MultipartFile multipartfile) {
		Attachment attachment = new Attachment();
		attachment.setFilename(multipartfile.getOriginalFilename());
		attachment.setCreationDate(new Date());
		attachment.setContentType(multipartfile.getContentType());
		
		try {
			attachment.setContent(multipartfile.getBytes());
		} catch (IOException e) {
			LOG.log(Level.WARNING, "IOException for multipartfile getBytes");
		}
		
		Issue issue = issueRepository.findIssue(issueId);
		attachmentRepository.create(attachment);
		issue.getAttachments().add(attachment);
		issueRepository.update(issue);
	}
	
	public Attachment getAttachment(Long id) {
		return attachmentRepository.getAttachment(id);
	}
	
	public void removeAttachment(Long id, Long issueId) {
		attachmentRepository.remove(id);
	}
	
	public List<AttachmentPojo> convertToPojo(Long issueId) {
		List<AttachmentPojo> attachmentPojos = new ArrayList<AttachmentPojo>();
		
		Issue issue = issueRepository.findIssue(issueId);
		if (issue.getAttachments().size() > 0) {
			for (Attachment attachment : issue.getAttachments()) {
				AttachmentPojo attachmentPojo = new AttachmentPojo();
				attachmentPojo.setIssueId(issue.getId());
				attachmentPojo.setId(attachment.getId());
				attachmentPojo.setFilename(attachment.getFilename());
				attachmentPojo.setFileType(attachment.getContentType());
				attachmentPojo.setContent(attachment.getContent());
				attachmentPojos.add(attachmentPojo);
			}
		}
		return attachmentPojos;
	} 
}
