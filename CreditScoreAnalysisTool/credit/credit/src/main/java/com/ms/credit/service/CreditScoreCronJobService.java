package com.ms.credit.service;

// Import necessary Spring framework and Java utility classes.
import org.springframework.scheduling.annotation.Scheduled;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms.credit.dto.CreditScoreDTO;

/**
 * CreditScoreCronJobService is a service class that schedules and executes periodic tasks
 * related to credit scores using Spring's scheduling capabilities.
 */
@Service  // Marks this class as a Spring-managed service.
public class CreditScoreCronJobService {

    private static final Logger logger = LogManager.getLogger(CreditScoreCronJobService.class);

    // Automatically injects CreditScoreService to access its methods.
    @Autowired
    private CreditScoreService creditScoreService; 

    /**
     * Scheduled method to process pending credit scores on an hourly basis.
     * It fetches credit scores that have been updated more than 24 hours ago and processes them.
     */
    @Scheduled(cron = "0 0 * * * *") // Cron expression for hourly execution: runs at the top of every hour.
    public void processPendingClaims() {
        // Fetch a list of credit scores that need to be updated or processed.
        List<CreditScoreDTO> creditScores = creditScoreService.fetchScoresUpdatedMoreThan24HoursAgo(); 
        for (CreditScoreDTO creditScore : creditScores) {
            try {
                // Attempt to process each credit score, here simplified to retrieving a score by user ID.
                creditScoreService.getCreditScoreByEmailId(creditScore.getUserId());
            } catch (Exception e) {
                // Log or handle the exception if processing fails.
                logger.error("Error processing Credit ID: {} with error: {}", creditScore.getUserId(), e.getMessage(), e);
            }
        }
    }
}
