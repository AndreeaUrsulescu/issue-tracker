window.onload = function() {

	$("#nextButton").on("click",
			issuePagination('${plus}', '${listLength}', '${itemsPerPage}'));
	$("#previousButton").on("click",
			issuePagination('${minus}', '${listLength}', '${itemsPerPage}'));

	numberOfIssues = $('#numberOfIssues').text();
	issuesPerPage = $('#issuesPerPage').text();

	if ((numberOfIssues - issuesPerPage) <= 0)
		$("#nextButton").css("visibility", "hidden");

	$("#previousButton").css("visibility", "hidden");

	if (numberOfIssues > 0) {
		document.getElementById("pageNumber").innerHTML = 1;
	} else if (numberOfIssues == 0) {
		document.getElementById("pageNumber").innerHTML = 0;
	}

	var labelUrl = window.location.origin + window.location.pathname
			+ "/labels";
	$.ajax({
		dataType : "json",
		type : "GET",
		url : labelUrl,
		success : function(rsp) {
			console.log("cal");
			for ( var key in rsp) {
				availableTags.push({
					"label" : rsp[key],
					"value" : rsp[key],
					"id" : key
				});
			}
		}
	});
	$("#searchByLabel").autocomplete({
		source : availableTags,
	});
};

var availableTags = [];
var selected = 0;
var count = 1;

function issuePagination(type, issuesListSize, issuesPerPage) {

	if (type == "+" && (issuesListSize - (count * issuesPerPage)) > 0) {

		// /////////////////////////////////////////////////////////////////////////////////

		$("#issues").animate({
			marginLeft : '-1800px'
		}, 0, function() {
			$("#issues").fadeOut(1);
			$("#issues").animate({
				marginLeft : '1800px'
			}, 0, function() {
				$("#issues").fadeIn(1);
				$("#issues").animate({
					marginLeft : '80px'
				}, 1000);
			});
		});

		// /////////////////////////////////////////////////////////////////////////////////

		count = count + 1;
		if ((issuesListSize - (count * issuesPerPage)) <= 0) {
			$("#nextButton").css("visibility", "hidden");
			$("#previousButton").css("visibility", "visible");
		} else {
			$("#nextButton").css("visibility", "visible");
			$("#previousButton").css("visibility", "visible");

		}
	} else if (type == "-" && count > 1) {

		// /////////////////////////////////////////////////////////////////////////////////

		$("#issues").animate({
			marginLeft : '1800px'
		}, 0, function() {
			$("#issues").fadeOut(1);
			$("#issues").animate({
				marginLeft : '-1800px'
			}, 0, function() {
				$("#issues").fadeIn(1);
				$("#issues").animate({
					marginLeft : '80px'
				}, 1000);
			});
		});

		// /////////////////////////////////////////////////////////////////////////////////
		count = count - 1;
		$("#nextButton").css("visibility", "visible");

		if (count == 1) {
			$("#previousButton").css("visibility", "hidden");
		}
	}
	document.getElementById("pageNumber").innerHTML = count;

	ajaxForPagination(count);

}

var countOnSort = 1;

function searchIssues() {

	var span = $("#noSearchResults");
	span.text(" ");

	var string = $("#searchByLabel").val().trim();

	var selectedLabel = null;

	for (index in availableTags) {
		var element = availableTags[index];
		if (element.value === string) {
			selectedLabel = element.id;
		}
	}

	countOnSort = 1;

	var filterData = {
		title : $("#searchByTitle").val().trim(),
		content : $("#searchByContent").val().trim(),
		state : $("#searchByState").val().trim(),
		creator : $("#searchByCreator").val().trim(),
		assignee : $("#searchByAssignee").val().trim(),
		label : selectedLabel,

		pageNumber : 1,
		sortCriteria : $("#orderBy").val().trim(),
		sortType : $("#orderType").val().trim()
	};

	$
			.ajax({
				url : "issues/multipleSearchBy", // put some URL
				type : "GET",
				data : filterData,
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success : function(response) {

					document.getElementById("nextButton").setAttribute(
							"onclick",
							"sortIssuesPagination('+'," + response.listLength
									+ "," + response.issuesPerPage + ");");
					document.getElementById("previousButton").setAttribute(
							"onclick",
							"sortIssuesPagination('-'," + response.listLength
									+ "," + response.issuesPerPage + ");");

					$("#nextButton").css("visibility", "visible");
					if ((response.listLength - response.issuesPerPage) <= 0)
						$("#nextButton").css("visibility", "hidden");

					$("#previousButton").css("visibility", "hidden");

					if (response.listLength > 0) {
						document.getElementById("pageNumber").innerHTML = 1;
					} else if (response.listLength == 0) {
						document.getElementById("pageNumber").innerHTML = 0;
					}

					document.getElementById("total").innerHTML = "/"
							+ Math
									.ceil((response.listLength / response.issuesPerPage));
					parsingAjaxResponse(response.issuesList);
				}
			});
}

function sortIssuesPagination(type, issuesListSize, issuesPerPage) {
	if (type == "+" && (issuesListSize - (countOnSort * issuesPerPage)) > 0) {

		// /////////////////////////////////////////////////////////////////////////////////

		$("#issues").animate({
			marginLeft : '-1800px'
		}, 0, function() {
			$("#issues").fadeOut(1);
			$("#issues").animate({
				marginLeft : '1800px'
			}, 0, function() {
				$("#issues").fadeIn(1);
				$("#issues").animate({
					marginLeft : '80px'
				}, 1000);
			});
		});

		// /////////////////////////////////////////////////////////////////////////////////

		countOnSort = countOnSort + 1;
		if ((issuesListSize - (countOnSort * issuesPerPage)) <= 0) {
			$("#nextButton").css("visibility", "hidden");
			$("#previousButton").css("visibility", "visible");
		} else {
			$("#nextButton").css("visibility", "visible");
			$("#previousButton").css("visibility", "visible");

		}
	} else if (type == "-" && countOnSort > 1) {

		// /////////////////////////////////////////////////////////////////////////////////

		$("#issues").animate({
			marginLeft : '1800px'
		}, 0, function() {
			$("#issues").fadeOut(1);
			$("#issues").animate({
				marginLeft : '-1800px'
			}, 0, function() {
				$("#issues").fadeIn(1);
				$("#issues").animate({
					marginLeft : '80px'
				}, 1000);
			});
		});

		// /////////////////////////////////////////////////////////////////////////////////

		countOnSort = countOnSort - 1;
		$("#nextButton").css("visibility", "visible");

		if (countOnSort == 1) {
			$("#previousButton").css("visibility", "hidden");
		}
	}
	document.getElementById("pageNumber").innerHTML = countOnSort;
	document.getElementById("total").innerHTML = "/"
			+ Math.ceil((issuesListSize / issuesPerPage));
	ajaxForSearchPagination(countOnSort);

}

function ajaxForSearchPagination(page) {

	var filterData = {
		title : $("#searchByTitle").val().trim(),
		content : $("#searchByContent").val().trim(),
		state : $("#searchByState").val().trim(),
		creator : $("#searchByCreator").val().trim(),
		assignee : $("#searchByAssignee").val().trim(),
		label : $("#searchByLabel").val().trim(),

		pageNumber : page,
		sortCriteria : $("#orderBy").val().trim(),
		sortType : $("#orderType").val().trim()
	};

	$.ajax({
		url : "issues/multipleSearchBy",
		type : "GET",
		data : filterData,
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {

			parsingAjaxResponse(response.issuesList);
		}
	});
}

function ajaxForPagination(page) {

	$.ajax({
		url : "issues/page/" + page,
		type : "GET",

		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
			parsingAjaxResponse(response);

		}
	});
}

function parsingAjaxResponse(response) {
	document.getElementById("issues").innerHTML = "";
	if (!response.length == 0) {
		for ( var index = 0; index < response.length; index++) {

			var stickyNote = document.createElement("A");
			var stickyNoteHref = "issues/issue/" + response[index].id;
			stickyNote.setAttribute("href", stickyNoteHref);

			var stickyNoteContent = document.createElement("DIV");

			if (response[index].state == "New")
				stickyNoteContent.setAttribute("class", "issue iss" + 6);
			if (response[index].state == "Opened")
				stickyNoteContent.setAttribute("class", "issue iss" + 2);
			if (response[index].state == "Testing")
				stickyNoteContent.setAttribute("class", "issue iss" + 0);
			if (response[index].state == "Closed")
				stickyNoteContent.setAttribute("class", "issue iss" + 5);

			var title = document.createElement("DIV");
			title.setAttribute("class", "border");

			var state = document.createElement("LABEL");
			state.setAttribute("class", "state");
			state.appendChild(document.createTextNode(response[index].state));

			// var date = document.createElement("LABEL");
			// date.setAttribute("class", "date");
			// date.appendChild(document.createTextNode(response[index].updateDate));

			var assignee = document.createElement("LABEL");
			assignee.setAttribute("class", "assignee");
			var assigneeSpan = document.createElement("SPAN");
			assigneeSpan.setAttribute("class", "glyphicon glyphicon-tag");
			var newSpan = document.createElement("SPAN");

			assignee.appendChild(newSpan);

			if (response[index].assignee == null)
				newSpan.appendChild(document.createTextNode("Unassigned"));
			else {
				newSpan.appendChild(document
						.createTextNode(response[index].assignee + " "));
				assignee.appendChild(assigneeSpan);
			}

			title.appendChild(state);
			title.appendChild(assignee);

			var content = document.createElement("DIV");
			content.setAttribute("class", "content");

			var contentTitle = document.createElement("H4");
			contentTitle.setAttribute("class", "title");
			contentTitle.appendChild(document
					.createTextNode(response[index].title));

			var paragraf = document.createElement("P");
			var paragrafContent = response[index].content;

			paragraf.appendChild(document.createTextNode(paragrafContent))

			content.appendChild(contentTitle);
			content.appendChild(paragraf);

			var assignee = document.createElement("DIV");
			assignee.setAttribute("class", "assigneeInfoPostIt");
			var assigneeSpan = document.createElement("SPAN");
			assigneeSpan.appendChild(document.createTextNode("Assignee:"));

			var newSpan = document.createElement("SPAN");
			newSpan.appendChild(document
					.createTextNode(response[index].assignee));

			assignee.appendChild(assigneeSpan);
			assignee.appendChild(newSpan);

			var owner = document.createElement("LABEL");
			owner.setAttribute("class", "owner");
			owner.appendChild(document.createTextNode("Updated "
					+ response[index].update + " ago by "
					+ response[index].owner));
			
			stickyNoteContent.appendChild(title);
			stickyNoteContent.appendChild(content);
			// stickyNoteContent.appendChild(assignee);
			stickyNoteContent.appendChild(owner);

			stickyNote.appendChild(stickyNoteContent);
			document.getElementById("issues").appendChild(stickyNote);
		}
	} else {
		var span = $("#noSearchResults");
		span.text("No search results to match the criteria");
	}
}

function slideFilter() {
 
    	$("#filterBox").slideToggle("slow", function() {
    	});
    
}