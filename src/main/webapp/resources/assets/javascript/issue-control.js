$(document).ready(function(){

	$(".editIssueContent").hide();
    $("#edit").click(function(){
        $(".editIssueContent").show();
        $(".viewIssue").hide();
        //TODO: add ajax call here to populate the form
    });
    $("#send").click(function(){
        //TODO: add ajax call to update the information
    });
    $("#reset").click(function(){
        location.reload();
        //reload page in order to get the most recent "news"
    });
    //for each subdiv
    $('#issue-state .viewIssueState').on('click',function(){
       $('div').removeClass('active');
       $(this).addClass('current');
       //set current active state
    });
});
