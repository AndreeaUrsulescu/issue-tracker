<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	//this is a dummy example
	$(function() {
		var availableTags = [ "ActionScript", "AppleScript", "Asp", "BASIC",
				"C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang",
				"Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp",
				"Perl", "PHP", "Python", "Ruby", "Scala", "Scheme" ];
		$("#tags").autocomplete({
			source : availableTags
		});
	});
	//TODO: add label and remove label from array (it can be added once)
</script>
<div class="container">
	
	<h1 class="commentSectionTitle">Labels</h1>

	<div class="labelSection">
		<div class="input-group">
			<input id="tags" type="text" class="form-control">
			<div class="input-group-btn">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					 <span
				class="glyphicon glyphicon-plus"></span>
				</button>
			</div>
		</div>
		<div class="selected-labels">
			<span class="btn btn-primary"> java <span
				class="glyphicon glyphicon-remove"></span>
			</span> <span class="btn btn-primary"> spring <span
				class="glyphicon glyphicon-remove"></span>
			</span> <span class="btn btn-primary"> hibernate <span
				class="glyphicon glyphicon-remove"></span>
			</span> <span class="btn btn-primary"> jsp <span
				class="glyphicon glyphicon-remove"></span>
			</span>
		</div>
	</div>
</div>