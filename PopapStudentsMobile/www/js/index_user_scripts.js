(function()
{
 "use strict";
    
 /*
   hook up event handlers 
 */
 function register_event_handlers()
 {
    
    
     /* button  #doLogin */
    $(document).on("click", "#doLogin", function(evt)
    {
        /* your code goes here */ 
    });
    
        /* button  #showhide */
    $(document).on("click", "#showhide", function(evt)
    {
         uib_sb.toggle_sidebar($("#loginSideBar"));
    });
    
        /* button  test */
    $(document).on("click", ".uib_w_7", function(evt)
    {
         /* Other possible functions are: 
           uib_sb.open_sidebar($sb)
           uib_sb.close_sidebar($sb)
           uib_sb.toggle_sidebar($sb)
            uib_sb.close_all_sidebars()
          See js/sidebar.js for the full sidebar API */
        
         uib_sb.toggle_sidebar($("#loginSideBar"));
        debugger;
    });
    
    }
 document.addEventListener("app.Ready", register_event_handlers, false);
})();
