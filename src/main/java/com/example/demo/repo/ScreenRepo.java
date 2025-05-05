package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modal.Screen;

public interface ScreenRepo extends JpaRepository<Screen, Integer> {
	List<Screen> findByTheatre_TheatreId(Integer theatreId);
}
