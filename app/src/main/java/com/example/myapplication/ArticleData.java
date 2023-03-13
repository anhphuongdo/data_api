package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;


public class ArticleData extends AsyncTask<String, String, String> {
    private Context context;
    private GridView gridview;
    public static ArticleList data;

    public ArticleData(Context context, GridView gridview) {
        this.context = context;
        this.gridview = gridview;
    }
    public static Article getPhotoFromId(int id){
        for(int i = 0; i< data.getArticles().size();i++){
            if(data.getArticles().get(i).getArticle_id() == id){
                return data.getArticles().get(i);
            }
        }
        return null;
    }
    protected String doInBackground(String... params){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try{
            URL url = new URL("https://raw.githubusercontent.com/thanhdnh/json/main/products.json");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }
            return buffer.toString();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(connection != null) connection.disconnect();
                if(reader != null) reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Gson gson = new Gson();
        data = gson.fromJson(result, (Type) ArticleList.class);
        ArticleAdapter adapter = new ArticleAdapter(data.getArticles(), context);
        gridview.setAdapter(adapter);

    }

}
