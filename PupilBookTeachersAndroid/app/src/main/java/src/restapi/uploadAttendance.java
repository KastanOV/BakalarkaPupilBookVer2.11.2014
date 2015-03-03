package src.restapi;

import android.content.Context;
import android.util.Xml;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;

import src.DBAdapter.Attendance;
import src.DBAdapter.AttendanceTable;
import src.DBAdapter.Result;
import src.DBAdapter.ResultsTable;
import src.pupilbookteachers.MainActivity;

/**
 * Created by Jaroslav on 3/2/2015.
 */
public class uploadAttendance {
    private static final String DEBUG_TAG = "PupilBook";
    private MainActivity context;
    private String targetURL;

    public uploadAttendance(String URL, MainActivity context){
        this.context = context;
        this.targetURL = URL;
        AttendanceTable attTable = new AttendanceTable(context);
        List<Attendance> tmp = attTable.getNewAttendanceForUpload();
        prepareToUploadAndUpload(tmp);
    }
    private Integer uploadData(String xml){
        Integer newID = null;
        OutputStream out = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(targetURL + "attendance/");
        try {
            StringEntity se = new StringEntity(xml, HTTP.UTF_8);
            se.setContentType("application/xml");
            httppost.setEntity(se);
            HttpResponse httpresponse = httpclient.execute(httppost);
            HttpEntity resEntity = httpresponse.getEntity();
            String tmp = EntityUtils.toString(resEntity);
            newID = Integer.parseInt(tmp);
        } catch (ClientProtocolException e) {
            newID = null;
            e.printStackTrace();
        } catch (IOException e) {
            newID = null;
            e.printStackTrace();
        } finally {
            return newID;
        }
    }
    private void prepareToUploadAndUpload(List<Attendance> at){
        //List<String> XmlStrings = new ArrayList<String>();
        try {
            for (Attendance item: at){
                XmlSerializer serializer = Xml.newSerializer();
                StringWriter writer = new StringWriter();
                    serializer.setOutput(writer);
                    serializer.startDocument("UTF-8", true);
                    serializer.startTag("", "attendanceDTO");
                    serializer.startTag("", "end");
                        if(item.getEnd() != null) serializer.text(String.valueOf(item.getEnd()));
                    serializer.endTag("", "end");
                    serializer.startTag("", "start");
                        if(item.getStart() != null) serializer.text(String.valueOf(item.getStart()));
                    serializer.endTag("", "start");
                    serializer.startTag("", "id");
                        if(item.getId() != null)
                            serializer.text(String.valueOf(item.getId()));
                        else
                            serializer.text("null");
                    serializer.endTag("", "id");
                    serializer.startTag("", "excused");
                        if(item.getExcused() != null) serializer.text(String.valueOf(item.getExcused()));
                    serializer.endTag("", "excused");
                    serializer.startTag("", "login");
                        if(item.getLogin() != null) serializer.text(item.getLogin());
                    serializer.endTag("", "login");
                    serializer.endTag("", "attendanceDTO");
                    serializer.endDocument();
                String wtmp = writer.toString();
                Integer tmps = uploadData(wtmp);
                if(tmps != null){
                    item.setId(tmps);
                    updateUploadedResult(item);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void updateUploadedResult(Attendance at){
        AttendanceTable tab = new AttendanceTable(context);
        tab.updateUploadedAttendance(at);
    }

}
