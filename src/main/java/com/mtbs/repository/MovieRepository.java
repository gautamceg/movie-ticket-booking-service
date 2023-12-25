package com.mtbs.repository;

import com.mtbs.enums.MovieLanguage;
import com.mtbs.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

	boolean existsByNameAndLanguage(String name, MovieLanguage language);
}