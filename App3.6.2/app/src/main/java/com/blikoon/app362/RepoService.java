package com.blikoon.app362;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RepoService {
    @GET("orgs/blikoon/repos")
    Call<List<Repo>> getRepos();
    //You can add other methods as need arises.


}
