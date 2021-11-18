package net.miwashi.sti.controller;

import net.miwashi.sti.model.Customer;
import net.miwashi.sti.model.Model;
import net.miwashi.sti.model.Playlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/playlist")
public class PlayListController {

    private static final Logger LOGGER = LoggerFactory.getLogger( PlayListController.class );

    Model model;

    @Autowired
    public void setModel(Model model) {
        this.model = model;
    }

    @GetMapping(path = "/{id}", produces = { "application/json"})
    public Playlist findCustomer(@PathVariable(value = "id", required = true)  Integer id, HttpServletResponse response) {
        return model.getPlaylistById(id);
    }

    /**
     *
     * @return
     */
    @GetMapping(path = "/list", produces = { "application/json"})
    public Collection<Playlist> listPlaylists() {
        return model.getPlaylists();
    }
}