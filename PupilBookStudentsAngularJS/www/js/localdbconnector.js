function getDBSheduleItems($scope){
    var db = getDB();
    var tmp = {};
        db.transaction(function (tx) {
            tx.executeSql("SELECT id, day, hour, idStudyGroup, idStudySubject, login, subjectName, teacherName FROM sheduleItemForStudent", [], 
            function (tx, results) {
                for (var i = 0; i < results.rows.length; i++){
                    tmp[i] = results.rows.item(i);
                    if(tmp[i].day === 0){
                       $scope.monday[tmp[i].hour] = tmp[i];
                       if(tmp[i].teacherName === ''){
                           tmp[tmp[i].hour].BackgroundClass = "success"; 
                       } else {
                           tmp[tmp[i].hour].BackgroundClass = "danger";
                       }
                   } else if(tmp[i].day === 1){
                       $scope.tuesday[tmp[i].hour] = tmp[i];
                       if(tmp[i].teacherName === ''){
                           $scope.monday[tmp[i].hour].BackgroundClass = "success"; 
                       } else {
                           $scope.monday[tmp[i].hour].BackgroundClass = "danger";
                       }
                   } else if(tmp[i].day === 2){
                       $scope.wednesday[tmp[i].hour] = tmp[i];
                       if(tmp[i].teacherName === ''){
                           $scope.monday[tmp[i].hour].BackgroundClass = "success"; 
                       } else {
                           $scope.monday[tmp[i].hour].BackgroundClass = "danger";
                       }
                   } else if(tmp[i].day === 3){
                       $scope.thursday[tmp[i].hour] = tmp[i];
                       if(tmp[i].teacherName === ''){
                           $scope.monday[tmp[i].hour].BackgroundClass = "success"; 
                       } else {
                           $scope.monday[tmp[i].hour].BackgroundClass = "danger";
                       }
                   } else if(tmp[i].day === 4){
                       $scope.friday[tmp[i].hour] = tmp[i];
                       if(tmp[i].teacherName === ''){
                           $scope.monday[tmp[i].hour].BackgroundClass = "success"; 
                       } else {
                           $scope.monday[tmp[i].hour].BackgroundClass = "danger";
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

function getDBResults(){
    
};
function setDBResults(){
    
};

function logOut(){
    var db = getDB();
    db.transaction(function(tx) {
        var query = "DELETE FROM sheduleItemForStudent";
            tx.executeSql(query, [],
            function (tx, results) {
                debugger;
            });
        });
};

function initdb(){
    var db = getDB();
    
    db.transaction(function (tx) {  
        tx.executeSql('CREATE TABLE IF NOT EXISTS sheduleItemForStudent ( id INTEGER PRIMARY KEY, day INTEGER, hour INTEGER, idStudyGroup INTEGER, idStudySubject INTEGER, login TEXT, subjectName TEXT, teacherName TEXT)');
        //tx.executeSql('CREATE TABLE IF NOT EXISTS TABLE_SHEDULEITEM (SHEDULE_ITEM_KEY_ID INTEGER PRIMARY KEY, SHEDULE_ITEM_KEY_DAY INTEGER, SHEDULE_ITEM_KEY_HOUR INTEGER, SHEDULE_ITEM_KEY_ID_STUDY_GROUP INTEGER, SHEDULE_ITEM_KEY_ID_STUDY_SUBJECT INTEGER, SHEDULE_ITEM_KEY_LOGIN TEXT)')
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
