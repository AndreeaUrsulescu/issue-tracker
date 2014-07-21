package internship.issuetracker.service;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.repository.CommentRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public void addComment(Comment comment) {
		this.commentRepository.create(comment);
	}

	public void updateComment(Comment comment) {
		this.commentRepository.update(comment);
	}

	public List<Comment> getCommentsForIssue(Issue issue) {
		return this.commentRepository.findCommentsByIssue(issue);
	}

	public List<Comment> getCommentForOwner(User user) {
		return this.commentRepository.findCommentByOwner(user);
	}
}
