package internship.issuetracker.service;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.repository.CommentRepository;


import internship.issuetracker.utils.XSSescape;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public void addComment(Comment comment) {
		comment.setContent(XSSescape.revert(comment.getContent()));
		this.commentRepository.create(comment);
	}

	public void updateComment(Comment comment) {
		comment.setContent(XSSescape.revert(comment.getContent()));
		this.commentRepository.update(comment);
	}
	
	private List<Comment> escape(List<Comment> commentList){
		for (Comment comment : commentList) {
			comment.setContent(XSSescape.convert(comment.getContent()));			
			}			
		return commentList;
	}

	public List<Comment> getCommentsForIssue(Issue issue) {
		return escape(this.commentRepository.findCommentsByIssue(issue));
	}

	public List<Comment> getCommentForOwner(User user) {
		return escape(this.commentRepository.findCommentByOwner(user));
	}
}
