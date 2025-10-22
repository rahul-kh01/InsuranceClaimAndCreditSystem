package com.ms.credit.dto;

import java.time.LocalDateTime;

public class CreditScoreDTO {
    private Long userId;
    private Long score;
    private LocalDateTime date;  // The date when the score was recorded

    // Default constructor
    public CreditScoreDTO() {
    }

    // Parameterized constructor
    public CreditScoreDTO(Long userId, Long score, LocalDateTime date) {
        this.userId = userId;
        this.score = score;
        this.date = date;
    }

    // Getters
    public Long getUserId() {
        return userId;
    }

    public Long getScore() {
        return score;
    }

    public LocalDateTime getDate() {
        return date;
    }
    

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
