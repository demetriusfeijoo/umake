$(function(){

    $("nav ul li").hover(function(){

        $( "#"+$(this).attr("id")+" .subMenu").css("display", "block");

    }, function(){

        $( "#"+$(this).attr("id")+" .subMenu").css("display", "none");

    });


});