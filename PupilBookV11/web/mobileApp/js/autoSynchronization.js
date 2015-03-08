var uploadChangedAttendance = function ($scope, $http, URL){
    var db = getDB();
    var data = {};
        db.transaction(function (tx) {
            var login = localStorage.getItem('login');
            var password = localStorage.getItem('password');
            tx.executeSql("SELECT id, start, end, excused, login, changed FROM attendance where changed = 1", [], 
            function (tx, results) {
                for (var i = 0; i < results.rows.length; i++){
                    data[i] = results.rows.item(i);
                    $http.get(URL + "attendance/" + login + "/" + password + "/" + data[i].id + "/empt")
                       .success(function(data){
                           if(data.status === "OK"){
                               var db = getDB();
                                db.transaction(function(tx) {
                                            var query = "UPDATE attendance SET excused = 'true', changed = 0 WHERE id = ?";
                                            tx.executeSql(query, [data.id],
                                            function (tx, results) {

                                            });
                                        });
                                getBAttendance($scope);        
                           }
                    }).error(function(){
                        //excuseAttendance($scope, id);
                        debugger;
                    });
                }
            }, null);
        });
}





