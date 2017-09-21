package com.example.genty.samuel.mypod;

/**
 * Created by Samuel Genty on 9/19/2017.
 */

public class Song {
    private String artist;
    private String name;
    private int song_length;

    // Main constructor Method
    public Song(String song_title, String song_artist) {
        name = song_title;
        artist = song_artist;
        song_length = 300;
    }

    /////// Getters ////////////
    public String get_artist(){
        return artist;
    }

    public String get_name(){
        return name;
    }

    public int get_song_length(){
        return song_length;
    }

    public Song get_song(){
        return this;
    }

    ////////////////////////////
}
