package com.example.postblog.API;

import com.example.postblog.Method.PostResult;
import com.example.postblog.Method.UserResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIinterface {

    @GET("Postblog/Post/post.php")
    Call<PostResult>getall();

    @FormUrlEncoded
    @POST("Postblog/User/profile.php")
    Call<UserResult>fetchprofile(@Field("id") String id);

    @FormUrlEncoded
    @POST("Postblog/User/login.php")
    Call<UserResult>login(@Field("user_name") String user_name,@Field("password") String password);


    @FormUrlEncoded
    @POST("Postblog/User/postslist.php")
    Call<PostResult>postlist(@Field("id") String id);

    @FormUrlEncoded
    @POST("Postblog/User/postlistlike.php")
    Call<PostResult>postlistlike(@Field("id") String id);

    @FormUrlEncoded
    @POST("Postblog/User/postlistsaved.php")
    Call<PostResult>postlistsave(@Field("id") String id);


    @FormUrlEncoded
    @POST("Postblog/Post/subpost.php")
    Call<PostResult>contentlist(@Field("id") String id);

    @FormUrlEncoded
    @POST("Postblog/Post/postid.php")
    Call<PostResult>postid(@Field("id") String id);


    @FormUrlEncoded
    @POST("Postblog/Post/postlike.php")
    Call<PostResult>postlikes(@Field("id") String id,@Field("post_id") String post_id);

    @FormUrlEncoded
    @POST("Postblog/Post/likedone.php")
    Call<PostResult>setlike(@Field("user_id") String id,@Field("post_id") String post_id);


    @FormUrlEncoded
    @POST("Postblog/Post/dislike.php")
    Call<PostResult>deslike(@Field("user_id") String id,@Field("post_id") String post_id);


    @FormUrlEncoded
    @POST("Postblog/Post/save.php")
    Call<PostResult>savepost(@Field("user_id") String id,@Field("post_id") String post_id);


}
