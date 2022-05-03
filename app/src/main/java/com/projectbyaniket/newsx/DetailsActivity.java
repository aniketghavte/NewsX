package com.projectbyaniket.newsx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectbyaniket.newsx.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    NewsHeadlines headlines;
    String url;
    TextView txt_title,txt_time,txt_author,txt_detail,txt_content;
    ImageView img_news;
    Button readNewsBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //txt_author = findViewById(R.id.text_author_detail);
        txt_content = findViewById(R.id.text_content_detail);
        txt_detail = findViewById(R.id.text_detail_detail);
        //txt_time = findViewById(R.id.text_time_detail);
        txt_title = findViewById(R.id.text_Detail_title);
        img_news = findViewById(R.id.img_detail_news);
        readNewsBt = findViewById(R.id.btnReadNews);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        txt_title.setText(headlines.getTitle());
        //txt_author.setText(headlines.getAuthor());
        //txt_time.setText(headlines.getPublishedAt());
        txt_detail.setText(headlines.getDescription());
        txt_content.setText(headlines.getContent());
        url= headlines.getUrl();

        Picasso.get().load(headlines.getUrlToImage()).into(img_news);

        readNewsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(Intent.ACTION_SEND);
              //  i.setType("text/plane");
               // i.putExtra(Intent.EXTRA_TEXT,"massage1"+url);
               // startActivity(Intent.createChooser(i,"massage2"));
                //String url = "http://www.stackoverflow.com";
                Intent i = new Intent();
                i.setPackage("com.android.chrome");
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}