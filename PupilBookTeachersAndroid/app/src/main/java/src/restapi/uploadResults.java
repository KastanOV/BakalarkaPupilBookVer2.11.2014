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

import src.DBAdapter.Result;
import src.DBAdapter.ResultsTable;
import src.pupilbookteachers.MainActivity;

/**
 * Created by Topr on 12/6/2014.
 */
public class uploadResults {
    private static final String DEBUG_TAG = "PupilBook";
    private MainActivity context;
    private String targetURL;

    public uploadResults(String URL, MainActivity context){
        this.context = context;
        this.targetURL = URL;
        List<Result> res = prepareResults();
        prepareToUploadAndUpload(res);
    }

    private Integer uploadData(String xml){
        Integer newID = null;
        OutputStream out = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(targetURL + "Results");
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

    private void prepareToUploadAndUpload(List<Result> results){
        //List<String> XmlStrings = new ArrayList<String>();
        try {
            for (Result item: results){
                XmlSerializer serializer = Xml.newSerializer();
                StringWriter writer = new StringWriter();
                serializer.setOutput(writer);
                serializer.startDocument("UTF-8", true);
                serializer.startTag("", "results");
                    serializer.startTag("", "date");
                        if(item.getDate() != null) serializer.text(item.getDate().toString());
                    serializer.endTag("", "date");
                    serializer.startTag("", "desc");
                        if(item.getDesc() != null) serializer.text(item.getDesc());
                    serializer.endTag("", "desc");
                    serializer.startTag("", "id");
                        if(item.getId() != null) serializer.text(item.getId().toString());
                    serializer.endTag("", "id");
                    serializer.startTag("", "score");
                        if(item.getScore() != null) serializer.text(String.valueOf(item.getScore()));
                    serializer.endTag("", "score");
                    serializer.startTag("", "ssId");
                        if(item.getSsId() != null) serializer.text(String.valueOf(item.getSsId()));
                    serializer.endTag("", "ssId");
                    serializer.startTag("", "sL");
                        if(item.getsL() != null)serializer.text(item.getsL());
                    serializer.endTag("", "sL");
                    serializer.startTag("", "tL");
                        if(item.gettL() != null) serializer.text(item.gettL());
                    serializer.endTag("", "tL");
                    serializer.startTag("", "ps");
                        if(item.getPs() != null) serializer.text(item.getPs());
                    serializer.endTag("", "ps");
                serializer.endTag("", "results");
                serializer.endDocument();
                //XmlStrings.add(writer.toString());
                Integer tmps = uploadData(writer.toString());
                if(tmps != null){
                   item.setId(tmps);
                   updateUploadedResult(item);
                }
            }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    private void updateUploadedResult(Result res){
        ResultsTable tab = new ResultsTable(context);
        tab.updateUploadedResult(res);
    }
    private List<Result> prepareResults(){
        ResultsTable resTable = new ResultsTable(context);
        List<Result> resList = resTable.getNewResultsForUpload();
        for(Result item : resList){
            item.setPs(context.getSharedPreferences(MainActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE).getString(MainActivity.PASSWORD, "ERROR"));
        }
        return resList;
    }
}
