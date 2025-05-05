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
import com.example.demo.modal.Screen;
import com.example.demo.service.ScreenService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;
    
    @PostMapping("/{theatreId}")
    public ResponseEntity<Screen> addScreen(@PathVariable Integer theatreId, @RequestBody Screen screen) 
            throws GlobalException {
        
        // Keep controller clean; no need to manually set Theatre
        Screen savedScreen = screenService.addScreen(screen, theatreId);
        return new ResponseEntity<>(savedScreen, HttpStatus.CREATED);
    }

    @PutMapping("/{screenId}")
    public ResponseEntity<Screen> updateScreen(@PathVariable Integer screenId, @RequestBody Screen screenDetails) 
        throws GlobalException {
        screenDetails.setScreenId(screenId);
        Screen updatedScreen = screenService.updateScreen(screenDetails);
        return ResponseEntity.ok(updatedScreen);
    }

    @DeleteMapping("/{screenId}")
    public ResponseEntity<Void> deleteScreen(@PathVariable Integer screenId) throws GlobalException {
        screenService.removeScreen(screenId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/theatre/{theatreId}")
    public List<Screen> getScreensByTheatre(@PathVariable Integer theatreId) {
        return screenService.getScreensByTheatre(theatreId);
    }
    
    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens() throws GlobalException {
        return ResponseEntity.ok(screenService.getAllScreens());
    }

}
