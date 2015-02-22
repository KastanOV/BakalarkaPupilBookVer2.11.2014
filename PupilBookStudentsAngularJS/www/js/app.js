(function(){
    var URL = "http://localhost:8080/PupilBookV11/webresources/";
    //var URL = "http://86.49.147.135:9001/PupilBookV11/webresources/";
    var home = angular.module('home',['ui.bootstrap']);
    
    home.controller('homeController',function($scope,$http){
        console.log("controller initialized");
        $scope.loggedUser = false;
        $scope.loggedUserIsParrent = false;
        $scope.loggeduserName = "";
        $scope.showShedule = false;
        $scope.showMainPage = false;
        $scope.showResults = false;
        $scope.showInformations = false;
        
        this.showResults = function(){
            $scope.showShedule = false;
            $scope.showMainPage = false;
            $scope.showResults = true;
            $scope.showInformations = false;
        };
        this.showShedule = function(){
            $scope.showShedule = true;
            $scope.showMainPage = false;
            $scope.showResults = false;
            $scope.showInformations = false;
        };
        this.mainPage = function(){
            $scope.showMainPage = true;
            $scope.showShedule = false;
            $scope.showResults = false;
            $scope.showInformations = false;
        };
        this.Informations = function(){
            $scope.showMainPage = false;
            $scope.showShedule = false;
            $scope.showResults = false;
            $scope.showInformations = true;
        };
        
        var initPage = function(){
            initdb();
            var login = localStorage.getItem("login");
            var password = localStorage.getItem("password");
            var role = localStorage.getItem("role");
            if(role === "P") $scope.loggedUserIsParrent = true;
            else $scope.loggedUserIsParrent = false;
            $scope.monday = [];
            $scope.tuesday = [];
            $scope.wednesday = [];
            $scope.thursday = [];
            $scope.friday = [];
            
            if((login === "undefined" || password === "undefined") || login === null){
                //Uzivatel NENÍ přihlášen 
                $scope.loggedUser = false;
                $scope.showShedule = false;
                $scope.showMainPage = false;
                $scope.showResults = false;
                $scope.showInformations = false;
            } else {
                loggeduserName = localStorage.getItem("name");
                $scope.loggedUser = true;
                //INITIALIZATION SHEDULE
                var studyGroup = localStorage.getItem("studyGroup");
                //Načtení rozhrhu hodin, Rozvrh se nejdříve načte s lokální DB a teprve potom se pokusí připojit
                // k serveru a získat aktuální rozvrh
                $scope.shedule = {};
                getDBSheduleItems($scope);
                    $http.get(URL + "sheduleitems/" + studyGroup)
                       .success(function(data){
                           $scope.shedule = data;
                           setDBSheduleItems(data);
                           createSheduleItemsDays(data, $scope);
                    }).error(function(){
                        alert("Nyní pracujete offline");
                    });
                $scope.results ={};
                    $http.get(URL + "Students/" + login + "/" + password + "/results")
                            .success(function(data){
                                setDBResults(data);
                               $scope.results = data;
                       debugger;
                    }).error(function(){
                        //alert("připojení k serveru se nezdařilo");
                        
                    });
            } 
        };
        initPage();
        //Eventa for reload page
        $scope.$on('reloadPage', function(event, args) 
        {
            initPage();
        });
    });
    function createSheduleItemsDays(data, $scope){
        for(i = 0;i < data.length; i++){
           if(data[i].day === 0){
               $scope.monday[data[i].hour] = data[i];
               if(data[i].teacherName === ''){
                   $scope.monday[data[i].hour].BackgroundClass = "success"; 
               } else {
                   $scope.monday[data[i].hour].BackgroundClass = "danger";
               }
           } else if(data[i].day === 1){
               $scope.tuesday[data[i].hour] = data[i];
               if(data[i].teacherName === ''){
                   $scope.monday[data[i].hour].BackgroundClass = "success"; 
               } else {
                   $scope.monday[data[i].hour].BackgroundClass = "danger";
               }
           } else if(data[i].day === 2){
               $scope.wednesday[data[i].hour] = data[i];
               if(data[i].teacherName === ''){
                   $scope.monday[data[i].hour].BackgroundClass = "success"; 
               } else {
                   $scope.monday[data[i].hour].BackgroundClass = "danger";
               }
           } else if(data[i].day === 3){
               $scope.thursday[data[i].hour] = data[i];
               if(data[i].teacherName === ''){
                   $scope.monday[data[i].hour].BackgroundClass = "success"; 
               } else {
                   $scope.monday[data[i].hour].BackgroundClass = "danger";
               }
           } else if(data[i].day === 4){
               $scope.friday[data[i].hour] = data[i];
               if(data[i].teacherName === ''){
                   $scope.monday[data[i].hour].BackgroundClass = "success"; 
               } else {
                   $scope.monday[data[i].hour].BackgroundClass = "danger";
               }
           } 
       }
    }
    home.directive('loginDir', function(){
        return{
          restrict: 'E',
          templateUrl: 'login.html'
        };
    });
    home.directive('navigationBar', function(){
       return{
          restrist: 'E',
          templateUrl: 'navbar.html'
       } ;
    });
    home.directive('shedule', function(){
       return {
           restrist: 'E',
           templateUrl: 'shedule.html',
           controller: function($scope){
               
               
           },
           controllerAs: 'sheduleCtrl'
       };
    });
    home.directive('results', function(){
        return {
            restrict: 'E',
            templateUrl: 'results.html'
        };
    });
    home.directive('informations',  function(){
        return{
            restrict: 'E',
            templateUrl: 'information.html',
            controller: function($scope,$http){
                $scope.informations;
                var login = localStorage.getItem('login');
                var sg = localStorage.getItem('studyGroup');
                var role = localStorage.getItem('role');
                var tmp = URL + 'informations/' + login + "/" + sg + "/" + role;
                $http.get(URL + 'informations/' + login + "/" + sg + "/" + role)
                        .success(function(data){
                        $scope.informations = data;
                        }).error(function(){
                            //alert("Přihlášení se nepodařilo :( asi na <> heslo");
                        });
            },
            controllerAs: 'infoCtrl'
        };
    });
    home.controller('loginController', ['$scope','$http', function($scope,$http){
       console.log("LoginController INITIALIZED");
            this.login;
            this.password;
            this.HashedPassword;
            this.loggIn = function(){
                this.HashedPassword = calcMD5(this.password);
                $http.get(URL + "Login/" + this.login + "/" + this.HashedPassword)
                        .success(function(data){
                            if(data !== ""){
                                if(data.role === "P") {
                                    var tmp = data.login.substring(1,data.login.length);
                                    localStorage.setItem("login", tmp);
                                } else {
                                    localStorage.setItem("login", data.login);
                                }
                                
                                localStorage.setItem("name", data.firstName + " " + data.lastName);
                                localStorage.setItem("password", data.password);
                                localStorage.setItem("studyGroup", data.studyGroupID);
                                localStorage.setItem("email", data.email);
                                localStorage.setItem("role", data.role);
                                $scope.$emit('reloadPage', null);
                            }   else {
                                alert("Přihlášení se nepodařilo :( asi na <> heslo");
                            }  
                }).error(function(){
                    alert("neco je spatne :(");
                });
            };
            this.loggOut = function(){
                localStorage.setItem("login", "undefined");
                localStorage.setItem("name", "undefined");
                localStorage.setItem("password", "undefined");
                localStorage.setItem("studyGroup", "undefined");
                localStorage.setItem("email", "undefined");
                localStorage.setItem("role", "undefined");
                $scope.$emit('reloadPage', null);
                $scope.loggedUserIsParrent = false;
            };
    }]);
    
    
})();


