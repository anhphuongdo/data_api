package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewArticleActivity extends AppCompatActivity {

    ImageView imvDetail;
    TextView tvTitle, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);
        imvDetail = findViewById(R.id.imv_detail);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvDescription = findViewById(R.id.tv_detail_description);

        Intent intent = getIntent();
        int id = (int) intent.getLongExtra("id", 0);
        Picasso.get().load(ArticleData.getPhotoFromId(id).getArticle_image()).into(imvDetail);
        tvTitle.setText(ArticleData.getPhotoFromId(id).getArticle_title());
        tvDescription.setText(ArticleData.getPhotoFromId(id).getArticle_description());
    }
}