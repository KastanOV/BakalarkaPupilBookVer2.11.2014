function getDBSheduleItems($scope){
    var db = getDB();
    var data = {};
        db.transaction(function (tx) {
            tx.executeSql("SELECT id, day, hour, idStudyGroup, idStudySubject, login, subjectName, teacherName FROM sheduleItemForStudent", [], 
            function (tx, results) {
                for (var i = 0; i < results.rows.length; i++){
                    data[i] = results.rows.item(i);
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
            }, null);
        });
    
};
function setDBSheduleItems(data){
    var db = getDB();
    db.transaction(function(tx) {
        var query = "DELETE FROM sheduleItemForStudent";
            tx.executeSql(query, [],
                function (tx, results) {
                    
                });
        });
    for (item in data){ 
        (function(row){
            db.transaction(function(tx) {
                var query = "INSERT INTO sheduleItemForStudent ( id, day, hour, idStudyGroup, idStudySubject, login, subjectName, teacherName) VALUES (?,?,?,?,?,?,?,?)";
                tx.executeSql(query, [row.id, row.day, row.hour, row.idStudyGroup, row.idStudySubject, row.login, row.subjectName, row.teacherName],
                function (tx, results) {
                });
            });
        })(data[item]);
    }
};

function getDBResults($scope){
    var db = getDB();
    var studySubjects = {};
    db.transaction(function (tx) {
            tx.executeSql("SELECT id, name FROM studySubjects", [], 
            function (tx, results) {
                for (var i = 0; i < results.rows.length; i++){
                    studySubjects[i] = results.rows.item(i);
                    buildResultsAccordeon($scope,results.rows.item(i), i);
                }
            }, null);
        });
};
function buildResultsAccordeon($scope, studySubjects, row){
    var db = getDB();
    var resultstmp = {};
    db.transaction(function (tx) {
        tx.executeSql("SELECT id, date, desc, score, ssId FROM results where ssId = ?", [studySubjects.id], 
                function (tx, res) {
                    for (var j = 0; j < res.rows.length; j++){
                        resultstmp[j] = res.rows.item(j);
                    }
                    studySubjects.results = resultstmp;
                    $scope.results[row] = studySubjects;
                }, null);
            });
};
function setDBResults(data){
    var db = getDB();
    db.transaction(function(tx) {
        var query = "DELETE FROM studySubjects";
            tx.executeSql(query, [],
                function (tx, results) {
                });
    }); 
    db.transaction(function(tx) {
        var query = "DELETE FROM results";
            tx.executeSql(query, [],
                function (tx, results) {
                });
    });
    for (item in data){ 
        (function(row){
            db.transaction(function(tx) {
                var query = "INSERT INTO studySubjects ( id, name) VALUES (?,?)";
                tx.executeSql(query, [row.results[0].ssId, row.name ],
                function (tx, results) {
                });
            });
            for (res in  row.results){
                (function(row2){
                    var tmp = row.results[res];
                    db.transaction(function(tx) {
                        var query = "INSERT INTO results (id, date, desc, score, ssId) VALUES (?,?,?,?,?)";
                        tx.executeSql(query, [tmp.id, tmp.date, tmp.desc, tmp.score, tmp.ssId],
                        function (tx, results) {
                        });
                    });
                })(row.results[res])
            }
            
        })(data[item]);
    }
};
function getDBInformations($scope){
    var db = getDB();
    var data = {};
    db.transaction(function (tx) {
            tx.executeSql("SELECT createDate, description, someMessage, teacherName FROM informations", [], 
            function (tx, results) {
                for (var i = 0; i < results.rows.length; i++){
                    data[i] = results.rows.item(i);
                }
                $scope.informations = data;
                debugger;
            }, null);
        });
}
function setDBInformation(data){
    var db = getDB();
    db.transaction(function(tx) {
        var query = "DELETE FROM informations";
            tx.executeSql(query, [],
                function (tx, results) {
                });
    }); 

    for (item in data){ 
        (function(row){
            db.transaction(function(tx) {
                var query = "INSERT INTO informations ( createDate, description, someMessage, teacherName) VALUES (?,?,?,?)";
                tx.executeSql(query, [row.createDate, row.description, row.someMessage, row.teacherName ],
                function (tx, results) {
                    
                });
            });
        })(data[item]);
    }
}
function logOut(){
    var db = getDB();
    db.transaction(function(tx) {
        var query = "DELETE FROM sheduleItemForStudent";
            tx.executeSql(query, [],
            function (tx, results) {

            });
        });
    db.transaction(function(tx) {
    var query = "DELETE FROM studySubjects";
        tx.executeSql(query, [],
        function (tx, results) {

        });
    });
    db.transaction(function(tx) {
    var query = "DELETE FROM results";
        tx.executeSql(query, [],
        function (tx, results) {

        });
    });
    db.transaction(function(tx) {
    var query = "DELETE FROM informations";
        tx.executeSql(query, [],
        function (tx, results) {

        });
    });
};

function initdb(){
    var db = getDB();
    db.transaction(function (tx) {  
        tx.executeSql('CREATE TABLE IF NOT EXISTS sheduleItemForStudent ( id INTEGER PRIMARY KEY, day INTEGER, hour INTEGER, idStudyGroup INTEGER, idStudySubject INTEGER, login TEXT, subjectName TEXT, teacherName TEXT)');
    });
    db.transaction(function (tx) {  
        tx.executeSql('CREATE TABLE IF NOT EXISTS studySubjects (id INTEGER PRIMARY KEY, name TEXT)');
    });
    db.transaction(function (tx) {  
        tx.executeSql('CREATE TABLE IF NOT EXISTS results (id INTEGER PRIMARY KEY, date TEXT, desc TEXT, score INTEGER, ssId INTEGER)');
    });
    db.transaction(function (tx) {  
        tx.executeSql('CREATE TABLE IF NOT EXISTS informations (id INTEGER PRIMARY KEY AUTOINCREMENT, createDate TEXT, description TEXT, someMessage TEXT, teacherName TEXT)');
    });
};

function getDB(){
    var db = openDatabase('popapu', '1.0', 'Popapu offline db', 5 * 1024 * 1024);
    return db;
}
//db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_SCHOOLYEAR + "(" + Utils.SCHOOL_YEAR_KEY_ID + " INTEGER PRIMARY KEY," + Utils.SCHOOL_YEAR_KEY_NAME + " TEXT)");
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_SHEDULEITEM + "(" + Utils.SHEDULE_ITEM_KEY_ID + " INTEGER PRIMARY KEY,"
//                + Utils.SHEDULE_ITEM_KEY_DAY + " INTEGER, "
//                + Utils.SHEDULE_ITEM_KEY_HOUR + " INTEGER, "
//                + Utils.SHEDULE_ITEM_KEY_ID_STUDY_GROUP + " INTEGER, "
//                + Utils.SHEDULE_ITEM_KEY_ID_STUDY_SUBJECT + " INTEGER, "
//                + Utils.SHEDULE_ITEM_KEY_LOGIN + " TEXT)");
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_STUDENT + "(" + Utils.STUDENT_KEY_ID + " TEXT PRIMARY KEY,"
//                + Utils.STUDENT_KEY_FIRSTNAME + " TEXT, "
//                + Utils.STUDENT_KEY_MIDDLENAME + " TEXT, "
//                + Utils.STUDENT_KEY_LASTNAME + " TEXT, "
//                + Utils.STUDENT_KEY_PHONE + " TEXT, "
//                + Utils.STUDENT_KEY_EMAIL + " TEXT, "
//                + Utils.STUDENT_KEY_PASSWORD + " TEXT, "
//                + Utils.STUDENT_KEY_STUDYGROUP + " INTEGER)");
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_STUDYGROUP + "(" + Utils.STUDY_GROUP_KEY_ID + " INTEGER PRIMARY KEY," + Utils.STUDY_GROUP_KEY_NAME + " TEXT)");
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_STUDYSUBJECT + "(" + Utils.STUDY_SUBJECT_KEY_ID + " INTEGER PRIMARY KEY, " + Utils.STUDY_SUBJECT_KEY_NAME + " TEXT, " + Utils.STUDY_SUBJECT_KEY_SHORT_NAME + " TEXT)");
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + Utils.TABLE_RESULTS + "(" + Utils.RESULTS_KEY_ID + " INTEGER, "
//                + Utils.RESULTS_DESCRIPTION + " TEXT, "
//                + Utils.RESULTS_SCORE + " INTEGER, "
//                + Utils.RESULTS_DATE + " TEXT, "
//                + Utils.RESULTS_STUDY_SUBJECT_ID + " INTEGER, "
//                + Utils.RESULTS_STUDENT_LOGIN + " TEXT,"
//                + Utils.RESULTS_TEACHER_LOGIN + " TEXT)");
