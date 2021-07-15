package com.mancala.api.repository;

import com.mancala.api.model.GameData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameData, Long> {
}
