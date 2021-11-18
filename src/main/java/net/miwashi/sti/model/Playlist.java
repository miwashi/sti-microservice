package net.miwashi.sti.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Playlist {
    Integer id;
    String name;
    Integer numberOfTracks = 0;
    List<Track> tracks = new ArrayList();

    public Playlist(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public void add(Track track) {
        tracks.add(track);
        numberOfTracks = tracks.size();
    }
}
