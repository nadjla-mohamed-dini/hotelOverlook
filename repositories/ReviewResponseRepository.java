package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ReviewResponse;


public interface ReviewResponseRepository extends JpaRepository<ReviewResponse, Long> {
    List<ReviewResponse> findByReviewId(ReviewResponse reviewrResponse);    
}
