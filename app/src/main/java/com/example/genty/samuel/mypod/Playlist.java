package com.example.genty.samuel.mypod;

import java.util.ArrayList;

/**
 * Created by Samuel on 9/20/2017.
 */

public class Playlist {
    private ArrayList<Song> songs_in_playlist;
    private int current_song;
    private String name;

    public void PlayList(){
        current_song = 0;
        songs_in_playlist = new ArrayList<Song>();
        name = "New-Playlist";
    }

    public void PlayList(String _name){
        current_song = 0;
        songs_in_playlist = new ArrayList<Song>();
        name = _name;
    }

    public Song get_song(){
        return songs_in_playlist.get(current_song);
    }

    public ArrayList<Song> get_playlist(){
        return songs_in_playlist;
    }

    // Append song to playlist ArrayList
    public void add_song(Song new_song){
        songs_in_playlist.add(new_song);
    }

    public Song play_next_song(){
        //range checking
        if(songs_in_playlist.size() <= current_song){
            return get_song();
            //return last song
        }
        current_song++;
        return get_song();
    }

    public Song play_previous_song(){
        //range checking
        if(songs_in_playlist.size() <= 1){
            return get_song();
            //return last song
        }
        current_song--;
        return get_song();
    }


}
