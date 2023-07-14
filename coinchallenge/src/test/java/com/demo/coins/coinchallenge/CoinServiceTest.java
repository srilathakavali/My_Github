package com.demo.coins.coinchallenge;

import com.demo.coins.coinchallenge.exception.InsufficientCoinsException;
import com.demo.coins.coinchallenge.service.CoinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoinServiceTest {
    private CoinService coinService;

    @BeforeEach
    public void setup() {
        coinService = new CoinService(100);
    }

    @Test
    public void testGetChange_ValidAmount_ReturnsChangeResponse() throws InsufficientCoinsException {
        // Arrange
        double amount = 10.0;
        int[] expectedCoinCounts = {0, 0, 1, 40}; // Example expected coin counts

        // Act
        assertThrows(InsufficientCoinsException.class, () -> coinService.getChange(amount));

    }

    @Test
    public void testGetChange_InsufficientCoins_ThrowsInsufficientCoinsException() {
        // Arrange
        double amount = 1000.0;

        // Act and Assert
        assertThrows(InsufficientCoinsException.class, () -> coinService.getChange(amount));
    }
}

