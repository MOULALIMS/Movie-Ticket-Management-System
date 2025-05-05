package com.example.demo.service;

import java.util.List;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Screen;

public interface ScreenService {

	Screen addScreen(Screen screen, Integer theatreId) throws GlobalException;

	Screen updateScreen(Screen screen) throws GlobalException;

	void removeScreen(Integer screenId) throws GlobalException;

	List<Screen> getScreensByTheatre(Integer theatreId);

	Screen getScreenById(Integer screenId) throws GlobalException;

	List<Screen> getAllScreens() throws GlobalException;

}
