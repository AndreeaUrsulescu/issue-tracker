<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div id="commentsViewSection" class="container commentsContainer">
	<div class="col-xs-8 commentsContainerCol">
		<h1 class="commentSectionTitle">Comment section</h1>
	
		<div class="commentSection">
	
			<div id="newComment">
			
				<form id="comments" class="richTextBorder" role="form">
	
					<div class="form-group">
	
						<textarea id="comment-area" class="form-control textArea" rows="7"
							maxlength="500"></textarea>
	
					</div>
	
					<div class="commentFormButtons">
	
						<span id="error-msg" class="error"></span>
	
						<div id="clear-btn" class="btn btn-default">Clear</div>
	
						<div id="send-btn" class="btn btn-primary">Save</div>
	
					</div>
	
				</form>
				
			</div>
	
			<br>
	
			<div class="commentView">
		
				<c:forEach var="comment" items="${viewIssue.getComments()}">
					<div class="commentTemplate">
	
						<div class="commentBox">
	
							<strong class="commentUser text-left"> <c:out
									value="${comment.getOwner()}" />
							</strong>
	
							<div class="clear"></div>
	
							<div class="commentContent">
								${comment.getContent()}
							</div>
	
							<p class="text-right commentDate">
								<small> ${comment.getCreationDate()}</small>
							</p>
	
						</div>
	
					</div>
				</c:forEach>
			</div>
	
		</div>
	</div>
</div>
