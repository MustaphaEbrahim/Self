package com.mustafa.self.Utilities;

/**
 * Created by Youssif Hamdy on 3/10/2020.
 */
public class Constants {

    public static final String API_KEY = "4aa5d7c7a7fd278a4448a8067f332ace";
    public static final String API_READ_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YWE1ZDdjN2E3ZmQyNzhhNDQ0OGE4MDY3ZjMzMmFjZSIsInN1YiI6IjVlNjdhMjhlMTUxYzVjMDAxMzAzNTJjNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FpSaDAdQ3k4WmRFDXJzSTGxmZSNj_6sOq_-lHiiYqwU";





    public static String getImageURL(int size , String path) {

        return "https://image.tmdb.org/t/p/w" + size + path;

    }

}
