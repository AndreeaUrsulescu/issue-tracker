<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div  class="container">

	<h1 class="commentSectionTitle">Comment section</h1>

	<div class="commentSection">

		<form  id="comments" role="form">

			<div class="form-group">

				<textarea id="comment-area" class="form-control textArea" rows="7"
					maxlength="500"></textarea>

			</div>

			<div class="commentFormButtons">

				<span id="error-msg" class="error"></span>

				<div id="counter" class="label label-default">500</div>

				<div id="clear-btn" class="btn btn-default">Clear</div>

				<div id="send-btn" class="btn btn-primary">Save</div>

			</div>

		</form>

		<br>

		<div class="commentView">

			<!-- Comment template should receive data from the DB. Pending, it seems. -->

			<c:forEach var="comment" items="${viewIssue.getComments()}">
				<div class="commentTemplate">

					<div class="commentBox">

						<strong class="commentUser text-left"> <c:out
								value="${comment.getOwner()}" />
						</strong>

						<div class="clear"></div>

						<div class="commentContent">
							<c:out value="${comment.getContent()}" />
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
