function loginInit(){
    var login = localStorage.getItem("login");
    var password = localStorage.getItem("password");
    
    if(login === null || password === null){
        $("#mainMenu").hide();
        uib_sb.open_sidebar($(".uib_w_1"));
    } else {
        $("#mainMenu").show();
        uib_sb.close_sidebar($(".uib_w_1"));
    }   
}
function collapsableAccordeon(){
     $('.my-collaspible').bind('expand', function () {
    $(this).children().slideDown(2000);
        }).bind('collapse', function () {
    $(this).children().next().slideUp(2000);
        });
}

function customersController($scope,$http) {
  $http.get("http://www.w3schools.com/website/Customers_JSON.php")
  .success(function(response) {$scope.names = response;});
}
