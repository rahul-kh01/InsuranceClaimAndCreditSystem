package com.ms.credit.entity;

import java.time.LocalDateTime;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_scores")
public class CreditScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "score")
    private Long score;

    @Column(name = "date")
    private Timestamp date;  // Using Timestamp for database compatibility with LocalDateTime

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;  // Timestamp of the last update
    // Default constructor
    public CreditScore() {
    }

    // Parameterized constructor
    public CreditScore(Long userId, Long score, Timestamp date) {
        this.userId = userId;
        this.score = score;
        this.date = date;
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

