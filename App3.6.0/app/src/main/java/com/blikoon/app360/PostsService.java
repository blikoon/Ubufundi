package com.blikoon.app360;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostsService {
    @GET("android/jsonandroid")
    Call<JSONResponse> getPosts();
    //We are currently only using the api above but the ones below are kept just for
    //later reference.
//    @GET("posts")
//    Call<List<Post>> getPosts(@Query("userId") Long userId);
//    @POST("posts")
//    Call<Post> createPost(@Body Post post);
//    @PUT("posts/{id}")
//    Call<Post> updatePost(@Path("id") Long id, @Body Post post);
//    @DELETE("posts/{id}")
//    Call<Void> deletePost(@Path("id") Long id);
}
