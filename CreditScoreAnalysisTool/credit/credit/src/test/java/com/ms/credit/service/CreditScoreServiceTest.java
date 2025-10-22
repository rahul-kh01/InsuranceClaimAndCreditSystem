package com.ms.credit.service;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.ms.credit.repository.CreditScoreRepository;

import reactor.core.publisher.Mono;

import com.ms.credit.client.UserManagementClient;
import com.ms.credit.dto.CreditScoreDTO;
import com.ms.credit.dto.ScoreHistoryDTO;
import com.ms.credit.entity.CreditScore;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.List;
import java.util.ArrayList;

public class CreditScoreServiceTest {

    @InjectMocks
    private CreditScoreService creditScoreService;

    @Mock
    private CreditScoreRepository creditScoreRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private UserManagementClient userManagementClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCreditScoreByEmailId() {
        // Setup
        String emailId = "test@example.com";
        CreditScore creditScore = new CreditScore();
        when(userManagementClient.getUserDetails(anyLong())).thenReturn(Mono.just(emailId));
        when(redisTemplate.opsForValue().get(anyString())).thenReturn(creditScore);

        // Action
        CreditScoreDTO result = creditScoreService.getCreditScoreByEmailId(1L);

        // Assert
        assertNotNull(result);
        verify(kafkaTemplate).send(anyString(), any());
    }

    @Test
    public void testCalculateCreditScore() {
        // Setup
        CreditScoreDTO creditScoreDTO = new CreditScoreDTO(1L, 700L, null);
        when(userManagementClient.getUserDetails(anyLong())).thenReturn(Mono.just("test@example.com"));
        when(creditScoreRepository.save(any(CreditScore.class))).thenReturn(new CreditScore());

        // Action
        CreditScoreDTO result = creditScoreService.calculateCreditScore(creditScoreDTO);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteCreditScoreByUserId() {
        // Action
        creditScoreService.deleteCreditScoreByUserId(1L);

        // Assert
        verify(creditScoreRepository).deleteByUserId(1L);
    }

    @Test
    public void testGetCreditScoreHistoryByUserId() {
        // Setup
        List<CreditScore> history = new ArrayList<>();
        history.add(new CreditScore());
        when(creditScoreRepository.findByUserId(anyLong())).thenReturn(history);

        // Action
        List<ScoreHistoryDTO> result = creditScoreService.getCreditScoreHistoryByUserId(1L);

        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetAverageCreditScore() {
        // Setup
        when(creditScoreRepository.findAll()).thenReturn(List.of(new CreditScore(), new CreditScore()));

        // Action
        double avgScore = creditScoreService.getAverageCreditScore();

        // Assert
        assertNotNull(avgScore);
    }

    // Add more tests for other methods...
}
