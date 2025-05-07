package com.example.demo.service;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Screen;
import com.example.demo.modal.Theatre;
import com.example.demo.repo.ScreenRepo;
import com.example.demo.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScreenServiceImp implements ScreenService {

	@Autowired
    private ScreenRepo screenRepo;
	
	@Autowired
    private TheatreRepo theatreRepo;

	@Override
	public Screen addScreen(Screen screen, Integer theatreId) throws GlobalException {
	    // Fetch the theatre from DB or throw error
	    Theatre theatre = theatreRepo.findById(theatreId)
	            .orElseThrow(() -> new GlobalException("Theatre not found"));

	    // Set the theatre to screen and save
	    screen.setTheatre(theatre);
	    Theatre t = theatreRepo.findById(theatreId).get();
	    t.setTotalScreens(t.getTotalScreens() + 1);
	    theatreRepo.save(t);
	    return screenRepo.save(screen);
	}

    @Override
    public Screen updateScreen(Screen screen) throws GlobalException {
        if (!screenRepo.existsById(screen.getScreenId())) {
            throw new GlobalException("Screen not found");
        }
        return screenRepo.save(screen);
    }

    @Override
    public void removeScreen(Integer screenId) throws GlobalException {
        if (!screenRepo.existsById(screenId)) {
            throw new GlobalException("Screen not found");
        }
        screenRepo.deleteById(screenId);
    }

    @Override
    public List<Screen> getScreensByTheatre(Integer theatreId) {
        return screenRepo.findByTheatre_TheatreId(theatreId);
    }

    @Override
    public Screen getScreenById(Integer screenId) throws GlobalException {
        return screenRepo.findById(screenId)
                .orElseThrow(() -> new GlobalException("Screen not found"));
    }

	@Override
	public List<Screen> getAllScreens() throws GlobalException {
		return screenRepo.findAll();
	}
}
