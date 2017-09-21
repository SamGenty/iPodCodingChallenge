package com.example.genty.samuel.mypod;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Samuel Genty on 9/19/2017.
 */
/*
a. Display all songs sorted alphabetically by either Artist or Name
b. Playlist creation (songs can be added/removed)
c. Shuffle to randomly play songs
d. Previous and Next to go to the last song played or the next in the list
*/


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Defining structures to use list of songs
    private ListView view_songs;
    private ArrayList<Song> songs_list;
    private ArrayList<Song> shuffle_song_list;
    private Song current_song;
    //private ProgressBar prog_bar = null;
    private Button next_button;
    private Button prev_button;
    private Switch shuffle_switch;
    private TextView current_playing_song_view;
    private int song_control = 0;
    private boolean shuffle_control;
    private int shuffle_itr = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_songs = (ListView)findViewById(R.id.all_songs);

        // Instantiate
        songs_list = new ArrayList<Song>();
        shuffle_song_list = new ArrayList<Song>();

        //Populate list with dummy data for now
        set_song_list();

        //set up button callbacks
        next_button = (Button)findViewById(R.id.button1);
        next_button.setOnClickListener(this);
        prev_button = (Button)findViewById(R.id.button2);
        prev_button.setOnClickListener(this);
        shuffle_switch = (Switch)findViewById(R.id.switch1);

        // Click listener for shuffle switch
        shuffle_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    shuffle_song_list.clear();
                    shuffle_itr = 0;
                    shuffle_control = true;
                    next_song();
                } else {
                    shuffle_control = false;
                }
            }

        });

        //Populate current song being played
        current_playing_song_view = (TextView)findViewById(R.id.textView);

        //Sort song names alphabetically
        // can change to artist sort if preferred
        Collections.sort(songs_list, new Comparator<Song>(){
            public int compare(Song a, Song b){
                return a.get_name().compareTo(b.get_name());
            }
        });

        //New Song Adapter to handle listview
        Song_Adapter song_adpt = new Song_Adapter(this, songs_list);
        view_songs.setAdapter(song_adpt);

        // Retrieve song currently being played
        current_song = songs_list.get(song_control);
        set_current_song(current_song);
    }


    //Setup Button callbacks
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1: // next song
                next_song();
                break;

            case R.id.button2: // previous song
                previous_song();
                break;
            default:
                break;
        }
    }
    //Method to retrieve songs and their attributes
    public void set_song_list() {

        //set dummy song data
            songs_list.add(new Song( "Circadian Rhythm", "Silversun Pickups"));
            songs_list.add(new Song( "Hold On", "Alabama Shakes"));
            songs_list.add(new Song( "Cough Syrup", "Young the Giant"));
            songs_list.add(new Song( "Lazy Eye", "Silversun Pickups"));
            songs_list.add(new Song( "Do You", "Spoon"));
            songs_list.add(new Song( "Old Friends", "Pinegrove"));
            songs_list.add(new Song( "Put a Light On", "Generationals"));
            songs_list.add(new Song( "Walking on a Dream", "Empire of the Sun"));
            songs_list.add(new Song( "Way It Goes", "Hippocampus"));
            songs_list.add(new Song( "Apologize", "OneRepublic"));
            songs_list.add(new Song( "What You Know", "Two Door Cinema Club"));
            songs_list.add(new Song( "My Body", "Young the Giant"));
            songs_list.add(new Song( "Trouble", "Cage the Elephant"));
            songs_list.add(new Song( "Hot Thoughts", "Spoon"));
            songs_list.add(new Song( "Float On", "Modest Mouse"));
            songs_list.add(new Song( "Inside Out", "Spoon"));
            songs_list.add(new Song( "Greek Tragedy", "The Wombats"));
            songs_list.add(new Song( "I'm Only Joking", "KONGOS"));
            songs_list.add(new Song( "Twin Size Mattress", "The Front Bottoms"));
            songs_list.add(new Song( "The Funeral", "Band of Horses"));
        //implement importing from device folders next
    }

    public void next_song(){
        if (song_control == songs_list.size()-1 && !shuffle_control){
            // Hitting next on the last song won't do anything
        } else {
            if (shuffle_control){
                Random r = new Random();
                int begin = 0; int end = songs_list.size();
                int Result = (r.nextInt(end-begin) + begin);
                current_song = songs_list.get(Result);
                shuffle_song_list.add(current_song);
                // keeps track of songs in shuffle list
                shuffle_itr++;
                song_control = Result;
                set_current_song(current_song);
            } else {
                //keep track of songs
                song_control++;
                current_song = songs_list.get(song_control);
                set_current_song(current_song);
            }
        }
    }

    public void previous_song(){
        if (song_control == 0 && !shuffle_control){

        } else {
            if (shuffle_control){
                if (shuffle_song_list.size() == 0){

                } else if (shuffle_song_list.size() == 1){
                    current_song = shuffle_song_list.get(0);
                    set_current_song(current_song);
                }
                else {
                    // keeps track of songs in shuffle list
                    shuffle_itr--;
                    current_song = shuffle_song_list.get(shuffle_itr-1);

                    shuffle_song_list.remove(shuffle_itr);
                    set_current_song(current_song);
                }

            } else {
                //keep track of songs
                song_control--;
                current_song = songs_list.get(song_control);
                set_current_song(current_song);
            }
        }
    }

    public void set_current_song(Song new_song){
        String str = "Playing: " + new_song.get_name() + " by " + new_song.get_artist();
        current_playing_song_view.setText(str);
    }

    // Built in call back to finger tap selection of song (added functionality)
    // callback defined in activity_main.xml
    public void select_song(View view){
        int song_id = Integer.parseInt(view.getTag().toString());
        current_song = songs_list.get(song_id);
        song_control = song_id;
        set_current_song(current_song);
    }
}
