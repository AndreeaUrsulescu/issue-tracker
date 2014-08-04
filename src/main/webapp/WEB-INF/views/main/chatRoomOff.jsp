<div class="container" style="min-height: 780px;">
	
		<div class="messageView">

			<c:forEach var="message" items="${chats}">
				<div class="messageTemplate">

					<div class="messageBox">

						<strong class="messageUser text-left"> <c:out
								value="${message.getOwner()}" />
						</strong>

						<div class="clear"></div>

						<div class="messageContent">
							<c:out value="${message.getContent()}" />
						</div>
					</div>

				</div>
			</c:forEach>
		</div>


	<div class="addMessage">

		<div clas="newMsg">
			<form id="Addmessage" role="form">
					<textarea id="message-area" class="form-control textArea" rows="7"
						maxlength="500"></textarea>
					<p>${userName}</p>

				<div class="messageFormButtons">

					<span id="error-msg" class="error"></span>
					
					<div id="counter" class="label label-default">700</div>
					
					<div id="clear-btn" class="btn btn-default">Clear</div>

					<div id="sendMsg" class="btn btn-primary">Send</div>

				</div>
			</form>
			
		</div>

		<br>

	</div>
</div>