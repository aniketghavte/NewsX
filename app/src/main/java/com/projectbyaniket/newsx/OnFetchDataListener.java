package com.projectbyaniket.newsx;

import com.projectbyaniket.newsx.Models.NewsApiResponse;
import com.projectbyaniket.newsx.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadlines> list ,String massage);
    void onError(String massage);

}
