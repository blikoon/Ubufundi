package com.blikoon.app360;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/*
We implement the Parcelable interface so that we can keep these objects during configuration changes of the activity where
they will be shown. That way, we don’t have to reload the data from the server when the device rotates.
 */

public class Post implements Parcelable {

    public String ver;
    public String name;
    public String api;

    public String getVer() {
        return ver;
    }

    public String getName() {
        return name;
    }

    public String getApi() {
        return api;
    }

    protected Post(Parcel in) {
        ver = in.readString();
        name = in.readString();
        api = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(ver);
        dest.writeString(name);
        dest.writeString(api);
    }
    public static final Parcelable.Creator<Post> CREATOR
            = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }
        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return api != null ? api.equals(post.api) : post.api == null;
    }
    @Override
    public int hashCode() {
        return api != null ? api.hashCode() : 0;
    }
}
