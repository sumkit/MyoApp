package com.thalmic.android.sample.helloworld;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

//import java.net.Socket;
/**
 * Created by sunnysummer5 on 2/20/16.
 */
public class Server extends AsyncTask<String, Void, Void> {
    String input = "";
    public Server(String s) {
        input = s;
    }
    @Override
    protected Void doInBackground(String... strings) {
        try {
            this.sendPost();
        } catch (Exception e) {
            Log.e("HERE", "CRASH", e);
            e.printStackTrace();
        }
        return null;
    }

    // HTTP POST request
    private void sendPost() throws Exception {
        String urlstr = "http://ec2-52-36-212-178.us-west-2.compute.amazonaws.com/";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(urlstr);

        JSONObject jsonobj = new JSONObject();
        jsonobj.put("input", input);
        StringEntity se = new StringEntity(jsonobj.toString());
        se.setContentType("application/json;charset=UTF-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
        post.setEntity(se);

//        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//        pairs.add(new BasicNameValuePair("key1", "value1"));
//        pairs.add(new BasicNameValuePair("key2", "value2"));
//        post.setEntity(new UrlEncodedFormEntity(pairs));

        HttpResponse httpresponse = client.execute(post);
        Log.e("fuck3", input);
    }
}

