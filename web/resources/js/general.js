 $(document).ready(function(){
    $("#action").click(function(){
        $("#hide").fadeToggle(1500);
    });

     $(document).click( function(event){
         if( $(event.target).closest("#hide").length )
             return;
         $("#hide").fadeOut("1500");
         event.stopPropagation();
     });


          $("#but1").click(function(){
             $("#scrol").toggle();
         });

});



