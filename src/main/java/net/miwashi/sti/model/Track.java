package net.miwashi.sti.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Track {
    Integer id;
    String name;
    Album album;
    MediaType mediaType;
    Genre genre;
    String composer;
    Integer milliseconds;
    Integer bytes;
    Double unitPrice;
}
