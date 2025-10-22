package com.ms.credit.dto;


public class FinancialDataDTO {
    private Long userId;
    private double creditScore;
    private double transactionAmount;
    private String emailId;
    private String transactionType;  // e.g., credit, debit, loan payment

    // Constructors
    public FinancialDataDTO() {
    }

    public FinancialDataDTO(Long userId, double transactionAmount, String transactionType) {
        this.userId = userId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
    }

    public FinancialDataDTO(Long userId, double creditScore, double transactionAmount, String emailId, String transactionType) {
        this.userId = userId;
        this.creditScore = creditScore;
        this.transactionAmount = transactionAmount;
        this.emailId = emailId;
        this.transactionType = transactionType;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }


    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}

