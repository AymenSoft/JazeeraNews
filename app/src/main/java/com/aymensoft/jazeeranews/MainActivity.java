package com.aymensoft.jazeeranews;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aymensoft.jazeeranews.adapters.NewsAdapter;
import com.aymensoft.jazeeranews.model.News;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    private NewsAdapter adapter;
    private ArrayList<News>news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.lv_listview);

        GetNews();

    }

    public void GetNews(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.app_name));
        pDialog.show();
        AndroidNetworking.get(WebServices.NEWSLINK)
                .setTag("PostJson")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        Log.e("response", response.toString());
                        // do anything with response

                        try {
                            String success = response.getString("status");
                            if (success.equals("ok")) {
                                String data = response.getString("articles");
                                Log.e("data", data);
                                news=getListFromJson(data);
                                adapter=new NewsAdapter(MainActivity.this,R.layout.listview_news,news);
                                listView.setAdapter(adapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        pDialog.dismiss();
                        Log.e("error", error.getErrorBody()+"");
                    }
                });
    }

    public ArrayList<News> getListFromJson(String json)
    {
        News s = new News();
        ArrayList<News> list = new ArrayList<News>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                s = mapper.readValue(jsonObject.toString(), News.class);

                list.add(s);
                Log.e("datas", String.valueOf(list));
            }
        }catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON Parser", "JSONException = " + e.getMessage());
        } catch (JsonParseException e) {
            e.printStackTrace();
            Log.e("JSON Parser", "JsonParseException = " + e.getMessage());
        } catch (JsonMappingException e) {
            e.printStackTrace();
            Log.e("JSON Parser", "JsonMappingException = " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JSON Parser", "IOException = " + e.getMessage());
        }
        return list;
    }

}
