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
    debugger; 
    var URL = "http://192.168.1.61:8080/PupilBookV11/webresources/Students/buk000/bf48f8277f8b8c8de85f27890d76cf99/picus";
    
    $http.get("http://192.168.1.61:8080/PupilBookV11/webresources/Students/buk000")
        .success(function(response) {$scope.names = response;});
    /*$.get(URL,function(data,status){
            $scope.results = data;
        });*/
 
}
