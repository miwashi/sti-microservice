package net.miwashi.sti.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Artist {
    Integer id;
    String name;
    List<Album> albums = new ArrayList();

    public Artist(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public void add(Album album){
        albums.add(album);
    }

    public String toString(){
        return id + " "  + name;
    }
}
