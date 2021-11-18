package net.miwashi.sti.controller;

import net.miwashi.sti.model.Artist;
import net.miwashi.sti.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/artist")
public class ArtistController {

    private static final Logger LOGGER = LoggerFactory.getLogger( ArtistController.class );

    Model model;

    @Autowired
    public void setModel(Model model) {
        this.model = model;
    }

    @GetMapping(path = "/{id}", produces = { "application/json"})
    public Artist findUser( @PathVariable(value = "id", required = true)  Integer id, HttpServletResponse response) {
        return model.getArtistById(id);
    }

    /**
     *
     * @return
     */
    @GetMapping(path = "/list", produces = { "application/json"})
    public Collection<Artist> listUsers() {
        return model.getArtists();
    }
}
