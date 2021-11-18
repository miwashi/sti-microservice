package net.miwashi.sti.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope("singleton")
public class Model {

    private static final Logger LOGGER = LoggerFactory.getLogger(Model.class);
    private static final ConcurrentHashMap<Integer, Artist> artists = new ConcurrentHashMap();
    private static final ConcurrentHashMap<Integer, Album> albums = new ConcurrentHashMap();

    private static final ConcurrentHashMap<Integer, Customer> customers = new ConcurrentHashMap();

    private static final ConcurrentHashMap<Integer, Playlist> playlists = new ConcurrentHashMap();
    private static final ConcurrentHashMap<Integer, MediaType> mediaTypes = new ConcurrentHashMap();
    private static final ConcurrentHashMap<Integer, Genre> genres = new ConcurrentHashMap();

    @PostConstruct
    public void init() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "sti", "sti");
            loadArtists(con);
            loadAlbums(con);
            loadCustomers(con);
            loadGenres(con);
            loadMediaTypes(con);
            loadPlaylists(con);
            //FIXME AlbumId | MediaTypeId | GenreId
        } catch (SQLException e) {
            LOGGER.warn("Unable to load data from mysql!", e);
        } catch (ClassNotFoundException e) {
            LOGGER.warn("Driver for mysql is not in classpath!", e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                    LOGGER.warn("Unable to close connection!", ignore);
                }
            }
        }
    }

    private void loadArtists(Connection con) throws SQLException {
        ResultSet rs = null;
        rs = con.createStatement().executeQuery("SELECT * FROM Artist");
        while (rs.next()) {
            Integer id = rs.getInt("ArtistId");
            String name = rs.getString("Name");
            Artist artist = new Artist(id, name);
            artists.put(artist.getId(), artist);
        }
        rs.close();
    }

    private void loadAlbums(Connection con) throws SQLException {
        ResultSet rs = null;
        rs = con.createStatement().executeQuery("SELECT * FROM Album");
        while (rs.next()) {
            Integer id = rs.getInt("AlbumId");
            Integer artistId = rs.getInt("ArtistId");
            String name = rs.getString("Title");
            Album album = new Album(id, name);
            albums.put(id, album);
            if (artists.get(artistId) != null) {
                artists.get(artistId).add(album);
            }
        }
        rs.close();
    }

    private void loadMediaTypes(Connection con) throws SQLException {
        ResultSet rs = null;
        rs = con.createStatement().executeQuery("SELECT * FROM MediaType");
        while (rs.next()) {
            Integer id = rs.getInt("MediaTypeId");
            String name = rs.getString("Name");
            MediaType mediaType = new MediaType(id, name);
            mediaTypes.put(id, mediaType);
        }
        rs.close();
    }

    private void loadGenres(Connection con) throws SQLException {
        ResultSet rs = null;
        rs = con.createStatement().executeQuery("SELECT * FROM Genre");
        while (rs.next()) {
            Integer id = rs.getInt("GenreId");
            String name = rs.getString("Name");
            Genre genre = new Genre(id, name);
            genres.put(id, genre);
        }
        rs.close();
    }

    private void loadPlaylists(Connection con) throws SQLException {
        ResultSet rs = null;
        rs = con.createStatement().executeQuery("SELECT * FROM Playlist");
        while (rs.next()) {
            Integer id = rs.getInt("PlaylistId");
            String name = rs.getString("Name");
            Playlist playlist = new Playlist(id, name);
            playlists.put(id, playlist);
        }
        rs.close();

        rs = con.createStatement().executeQuery("SELECT PlayListId, Track.*  FROM PlaylistTrack INNER JOIN Track ON PlaylistTrack.TrackId = Track.TrackId");
        while (rs.next()) {
            Integer playlistId = rs.getInt("PlaylistId");
            Integer trackId = rs.getInt("TrackId");
            String name = rs.getString("Name");

            Integer albumId = rs.getInt("AlbumId");
            Album album = albums.get(albumId);

            Integer mediaTypeId = rs.getInt("MediaTypeId");
            MediaType mediaType = mediaTypes.get(mediaTypeId);

            Integer genreId = rs.getInt("GenreId");
            Genre genre = genres.get(genreId);

            String composer = rs.getString("Composer");
            Integer milliseconds = rs.getInt("Milliseconds");
            Integer bytes = rs.getInt("Bytes");
            Double unitPrice = rs.getDouble("UnitPrice");

            Track track = Track.builder()
                .id(trackId)
                .name(name)
                .album(album)
                .mediaType(mediaType)
                .genre(genre)
                .composer(composer)
                .milliseconds(milliseconds)
                .bytes(bytes)
                .unitPrice(unitPrice)
            .build();

            Playlist playlist = playlists.get(playlistId);
            if(playlist!=null) {
                playlist.add(track);
            }
        }
        rs.close();
    }

    private void loadCustomers(Connection con) throws SQLException {
        ResultSet rs = null;
        rs = con.createStatement().executeQuery("SELECT * FROM Customer");
        while (rs.next()) {
            Integer id = rs.getInt("CustomerId");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String company = rs.getString("Company");
            String address = rs.getString("Address");
            String city = rs.getString("City");
            String state = rs.getString("State");
            String country = rs.getString("Country");
            String postalCode = rs.getString("PostalCode");
            String phone = rs.getString("Phone");
            String fax = rs.getString("Fax");
            String email = rs.getString("Email");
            //Integer supportRepId = rs.getInt("SupportRepId");

            Customer customer = Customer.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .company(company)
                .address(address)
                .city(city)
                .state(state)
                .country(country)
                .postalCode(postalCode)
                .phone(phone)
                .fax(fax)
                .email(email)
            .build();

            customers.put(id, customer);
        }
        rs.close();
    }

    public Collection<Artist> getArtists() {
        return artists.values();
    }

    public Artist getArtistById(Integer id) {
        return artists.get(id);
    }

    public Collection<Album> getAlbums() {
        return albums.values();
    }

    public Album getAlbumById(Integer id) {
        return albums.get(id);
    }

    public Collection<Customer> getCustomers() {
        return customers.values();
    }

    public Customer getCustomerById(Integer id) {
        return customers.get(id);
    }

    public Playlist getPlaylistById(Integer id) {
        return playlists.get(id);
    }

    public Collection<Playlist> getPlaylists() {
        return playlists.values();
    }
}
