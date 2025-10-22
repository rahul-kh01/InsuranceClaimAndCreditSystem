package com.ms.credit.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ScoreHistoryDTO {
    private Long userId;
    private List<CreditScoreDetails> scores;

    public static class CreditScoreDetails {
        private Long score;
        private LocalDateTime date;  // The date when the score was recorded

        public CreditScoreDetails() {
        }

        public CreditScoreDetails(Long score, LocalDateTime date) {
            this.score = score;
            this.date = date;
        }

        // Getters
        public Long getScore() {
            return score;
        }

        public LocalDateTime getDate() {
            return date;
        }

        // Setters
        public void setScore(Long score) {
            this.score = score;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }
    }

    // Default constructor
    public ScoreHistoryDTO() {
    }

    // Parameterized constructor
    public ScoreHistoryDTO(Long userId, List<CreditScoreDetails> scores) {
        this.userId = userId;
        this.scores = scores;
    }

    // Getters
    public Long getUserId() {
        return userId;
    }

    public List<CreditScoreDetails> getScores() {
        return scores;
    }

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setScores(List<CreditScoreDetails> scores) {
        this.scores = scores;
    }
}

