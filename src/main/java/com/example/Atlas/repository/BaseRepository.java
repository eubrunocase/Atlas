package com.example.Atlas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.UserDetails;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

}
