package com.example.genty.samuel.mypod;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samuel Genty on 9/19/2017.
 */

public class Song_Adapter extends BaseAdapter {
    private ArrayList<Song> all_songs;
    private LayoutInflater songInflater;

    public Song_Adapter(Context context, ArrayList<Song> _songs){
        all_songs = _songs;
        songInflater = LayoutInflater.from(context);
    }

    //Mostly just auto-populated methods here (required?)
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //return number of songs
        return all_songs.size(); // modified auto generated getCount
    }
    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int pos, View changeView, ViewGroup parent) {
        // inflate layout to java object
        LinearLayout song_layout = (LinearLayout)songInflater.inflate(R.layout.song_display, parent, false);
        //get name and artist textviews
        TextView song_display = (TextView)song_layout.findViewById(R.id.song_name);
        TextView artist_display = (TextView)song_layout.findViewById(R.id.song_artist);
        Song current_song = all_songs.get(pos);

        //set name and artist into textviews
        song_display.setText(current_song.get_name());
        artist_display.setText(current_song.get_artist());
        song_layout.setTag(pos);
        return song_layout;
    }
}
