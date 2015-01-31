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

function resultsController($scope,$http) {
    var login = localStorage.getItem("login");
    var password = localStorage.getItem("password");
    var url = "http://192.168.1.61:8080/PupilBookV11/webresources/Students/" + login + "/" + password + "/info";
    $scope.loadData = function () {
        for(;;){
         setTimeout(function() {
        $http.get(url)
        .success(function(response) {$scope.subjects = response;});
             }, 1000);  
        }
    }
}


