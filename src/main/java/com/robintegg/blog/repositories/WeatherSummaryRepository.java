package com.robintegg.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robintegg.blog.domain.WeatherSummary;

public interface WeatherSummaryRepository extends JpaRepository<WeatherSummary, Long> {

}
