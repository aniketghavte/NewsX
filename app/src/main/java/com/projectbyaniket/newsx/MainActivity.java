package com.projectbyaniket.newsx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.projectbyaniket.newsx.Models.CategoryRVModel;
import com.projectbyaniket.newsx.Models.NewsApiResponse;
import com.projectbyaniket.newsx.Models.NewsHeadlines;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener ,CategoryRVAdapter.CategoryClickInterface{

    RecyclerView recyclerView ,CategoryRV;
    CustomAdapter adapter;
    ProgressDialog dialog;
    private List<CategoryRVModel> categoryRVModelArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private Toolbar toolbar;

    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        CategoryRV = findViewById(R.id.idRvCategories);
        toolbar = findViewById(R.id.idToolbar);
        toolbar.inflateMenu(R.menu.options_menu);
        Intent i = new Intent(this,aboutActivity.class);
        Intent i1 = new Intent(this,SettingsActivity.class);

        /*
         This code is for operations for setting , refreshing news , about Activity and exit Application
         */
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.Setting){
                    Toast.makeText(MainActivity.this, "Opening Settings", Toast.LENGTH_SHORT).show();
                    startActivity(i1);
                } else if ( item.getItemId() == R.id.exit){
                    finish();
                    System.exit(0);
                } else if (item.getItemId() == R.id.refresh){
                    Toast.makeText(MainActivity.this, "Refreshing News", Toast.LENGTH_SHORT).show();
                    RequestManager manager = new RequestManager(MainActivity.this);
                    manager.getNewsHeadlines(listener,"general",null);
                } else if (item.getItemId() == R.id.about){
                    startActivity(i);
                }
                return false;
            }
        });

        //
        categoryRVModelArrayList = new ArrayList<>();

        //newsRVAdapter = new NewsRVAdapter(articlesArrayList, this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModelArrayList, this::onCategoryClick,this);
        CategoryRV.setAdapter(categoryRVAdapter);

        /*

         */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articals of" + query);
                dialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        dialog = new ProgressDialog(this);
        dialog.setTitle("Getting News");

        dialog.show();

        getCategories();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);

    }



    /*
           This Function is call when On create method is Called(means when App starts)
           in this As we created Arraylist of category RvModel we add Category in this Arraylist which further Implements in Recycler view of CategoryRVAdater

           This funciton sets Category and image gor category bg which
     */
    @SuppressLint("NotifyDataSetChanged")
    private void getCategories(){
        categoryRVModelArrayList.add(new CategoryRVModel("General","https://images.unsplash.com/photo-1493612276216-ee3925520721?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8Z2VuZXJhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVModelArrayList.add(new CategoryRVModel("Technology","https://images.unsplash.com/photo-1550751827-4bd374c3f58b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("Science","https://images.unsplash.com/photo-1564325724739-bae0bd08762c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("Sports","https://images.unsplash.com/photo-1459865264687-595d652de67e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("Business","https://images.unsplash.com/photo-1460925895917-afdab827c52f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGJ1c2luZXNzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVModelArrayList.add(new CategoryRVModel("Entertainment","https://media.istockphoto.com/photos/party-people-enjoy-concert-at-festival-summer-music-festival-picture-id1324561072?b=1&k=20&m=1324561072&s=170667a&w=0&h=LwWrgpVzxoznttv_6qXMVtZHer1QSLNbfHmORZCFhN0="));
        categoryRVModelArrayList.add(new CategoryRVModel("Health","https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGhlYWx0aHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVAdapter.notifyDataSetChanged();

    }


            /*

                */
    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String massage) {
            if (list.isEmpty()) {
                Toast.makeText(MainActivity.this, "No data Found!!!", Toast.LENGTH_SHORT).show();
            } else {
                showNews(list);
                dialog.dismiss();
            }
        }
        @Override
        public void onError(String massage) {

            Toast.makeText(MainActivity.this, "An Error Occured!!!", Toast.LENGTH_SHORT).show();
        }
    };

    /*
           THis function also called on On create method and this sets Arraylist of NewsHeadlines which we loaded with
                articals from Api To the Custom Adapter for News
     */

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNewsClicked(NewsHeadlines headlines) {

        startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("data",headlines));

    }




    @Override
    public void onCategoryClick(int position) {
        String category = categoryRVModelArrayList.get(position).getCategory();
        dialog.setTitle("Fetching news of "+ category);
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,category,null);
    }
}