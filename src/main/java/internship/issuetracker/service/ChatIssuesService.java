package internship.issuetracker.service;
import internship.issuetracker.entities.ChatIssues;
import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.ChatIssuePojo;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.repository.ChatIssuesRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatIssuesService  {

	private static final Logger log = Logger.getLogger(ChatIssuesService .class.getName());

	@Autowired
	private ChatIssuesRepository chatRepository;

	public void addMessage(ChatIssuePojo message) {
		ChatIssues com = new ChatIssues();
		com.setContent(message.getContent());
		com.setCreationDate(new Date());
		com.setOwner(message.getOwner());
		com.setLink(message.getLink());
		com.setRange(message.getRange());
		this.chatRepository.create(com);
	}

	public List<ChatIssuePojo> getMessages() {
		List<ChatIssuePojo> pojoMessages = new ArrayList<ChatIssuePojo>();
		List<ChatIssues> messages = chatRepository.getMessages();

		for (ChatIssues com : messages) {
			ChatIssuePojo pojoMsg = new ChatIssuePojo(com.getOwner(), com.getContent(), com.getLink(), 
					com.getCreationDate(), com.getRange());
			pojoMessages.add(pojoMsg);
		}

		return pojoMessages;
	}
}
