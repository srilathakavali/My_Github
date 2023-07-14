package com.demo.coins.coinchallenge;

import com.demo.coins.coinchallenge.controller.CoinController;
import com.demo.coins.coinchallenge.exception.InsufficientCoinsException;
import com.demo.coins.coinchallenge.model.ChangeRequest;
import com.demo.coins.coinchallenge.model.ChangeResponse;
import com.demo.coins.coinchallenge.service.CoinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CoinControllerTest {
    private CoinController coinController;

    @Mock
    private CoinService coinService;

    @BeforeEach
    public void setup() {
        coinService = mock(CoinService.class);
        coinController = new CoinController(coinService);
    }

    @Test
    public void testGetChange_ValidAmount_ReturnsOk() throws InsufficientCoinsException {
        // Arrange
        double amount = 10.0;
        ChangeRequest request = new ChangeRequest();
        request.setAmount(amount);

        int[] expectedCoinCounts = {0, 0, 1, 40}; // Example expected coin counts

        ChangeResponse expectedResponse = new ChangeResponse();
        expectedResponse.setCoinCounts(expectedCoinCounts);

        when(coinService.getChange(amount)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ChangeResponse> response = coinController.getChange(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testGetChange_InvalidAmount_ReturnsBadRequest() {
        // Arrange
        double invalidAmount = -5.0;
        ChangeRequest request = new ChangeRequest();
        request.setAmount(invalidAmount);

        // Act
        ResponseEntity<ChangeResponse> response = coinController.getChange(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testGetChange_InsufficientCoins_ReturnsInternalServerError() throws InsufficientCoinsException {
        // Arrange
        double amount = 1000.0;
        ChangeRequest request = new ChangeRequest();
        request.setAmount(amount);

        when(coinService.getChange(amount)).thenThrow(InsufficientCoinsException.class);

        // Act
        ResponseEntity<ChangeResponse> response = coinController.getChange(request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}
