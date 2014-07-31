$(document).ready(function(){
	var availableTags = [];
	
	$("#selectT").hide();
	$("#selectS").change(function(){
		if($(this).val()==="state"){
			$("#searchField").hide("slide");
			$("#selectT").show("slide");
			availableTags = [];
		}
		else
		{
			availableTags = [];
			$("#selectT").hide("slide");
			$("#searchField").show("slide");
			
			if($(this).val()==="label"){			
				var labelUrl = "issues/labels";
				$.ajax({
					dataType: "json",
					type: "GET",
					url: labelUrl,
					success: function(rsp) {
						for(var index = 0; index < rsp.length; index++){
							availableTags.push(rsp[index].labelName);
						}
						$( "#searchField" ).autocomplete({
							source: availableTags
						});
					}
				});

			}
		}
	});

});

