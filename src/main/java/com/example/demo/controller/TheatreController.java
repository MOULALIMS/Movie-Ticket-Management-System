package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Theatre;
import com.example.demo.service.TheatreServiceImp;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin/theatres")
public class TheatreController {

    @Autowired
    private TheatreServiceImp theatreService;

    @PostMapping("/{zipcode}")
    public ResponseEntity<Theatre> addTheatre(@PathVariable Integer zipcode, @RequestBody Theatre theatre) throws GlobalException {
        Theatre savedTheatre = theatreService.addTheatre(theatre, zipcode);
        return new ResponseEntity<>(savedTheatre, HttpStatus.CREATED);
    }

    @PutMapping("/{theatreId}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Integer theatreId, @RequestBody Theatre theatreDetails) 
        throws GlobalException {
        Theatre updatedTheatre = theatreService.updateTheatre(theatreId, theatreDetails);
        return ResponseEntity.ok(updatedTheatre);
    }

    @DeleteMapping("/{theatreId}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Integer theatreId) throws GlobalException {
        theatreService.deleteTheatre(theatreId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/city/{zipcode}")
    public ResponseEntity<List<Theatre>> getTheatresByCity(@PathVariable Integer zipcode) throws GlobalException {
        return ResponseEntity.ok(theatreService.getTheatresByCity(zipcode));
    }
    
    @GetMapping("/id/{theatreId}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable Integer theatreId) throws GlobalException {
        Theatre theatre = theatreService.getTheatreById(theatreId);
        return ResponseEntity.ok(theatre);
    }
    
    @GetMapping
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }
}
