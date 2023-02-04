package ru.Kuznetsov.dto;

import retrofit.http.GET;
import retrofit.http.Query;


public interface RequestServer {
    @GET("/scripts/XML_daily.asp")
    ValCurs getExchange(@Query("date_req") String date);
}
