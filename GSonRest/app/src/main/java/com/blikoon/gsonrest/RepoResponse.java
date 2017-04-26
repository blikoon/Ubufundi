package com.blikoon.gsonrest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class RepoResponse {

    public RepoResponse()
    {
    }

    public static List<Repo> parseJSON(String response) {
        Gson gson = new GsonBuilder().create();

        //Source : http://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
        Type collectionType = new TypeToken<Collection<Repo>>(){}.getType();
        List<Repo> myRepos;

        myRepos = gson.fromJson(response, collectionType);
        return myRepos;
    }

}
