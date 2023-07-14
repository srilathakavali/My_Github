package com.demo.coins.coinchallenge.service;

import com.demo.coins.coinchallenge.exception.InsufficientCoinsException;
import com.demo.coins.coinchallenge.model.ChangeResponse;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CoinService {

    private static final int INITIAL_COIN_COUNT = 100;
    private static final double[] COIN_VALUES = {0.01, 0.05, 0.10, 0.25};

    private int[] coinCounts;


    public CoinService(int initialCoinCount) {
        coinCounts = new int[COIN_VALUES.length];
        resetCoinCounts();
    }

    public ChangeResponse getChange(double amount) throws InsufficientCoinsException {
        // Step 1: Calculate the minimum number of coins required to make the change
        int[] requiredCoinCounts = calculateRequiredCoinCounts(amount);

        // Step 2: Check if there are enough coins available to make the change
        if (!hasSufficientCoins(requiredCoinCounts)) {
            throw new InsufficientCoinsException("Insufficient coins to make the change");
        }

        // Step 3: Update the coin counts and return the response
        updateCoinCounts(requiredCoinCounts);

        ChangeResponse response = new ChangeResponse();
        response.setCoinCounts(requiredCoinCounts);
        return response;
    }

    private void resetCoinCounts() {
        for (int i = 0; i < coinCounts.length; i++) {
            coinCounts[i] = INITIAL_COIN_COUNT;
        }
    }

    private int[] calculateRequiredCoinCounts(double amount) {
        int[] requiredCoinCounts = new int[COIN_VALUES.length];
        double remainingAmount = amount;

        for (int i = 0; i < COIN_VALUES.length; i++) {
            int coinCount = (int) (remainingAmount / COIN_VALUES[i]);
            requiredCoinCounts[i] = coinCount;
            remainingAmount -= coinCount * COIN_VALUES[i];
        }

        return requiredCoinCounts;
    }

    private boolean hasSufficientCoins(int[] requiredCoinCounts) {
        for (int i = 0; i < requiredCoinCounts.length; i++) {
            if (requiredCoinCounts[i] > coinCounts[i]) {
                return false;
            }
        }
        return true;
    }

    private void updateCoinCounts(int[] requiredCoinCounts) {
        for (int i = 0; i < requiredCoinCounts.length; i++) {
            coinCounts[i] -= requiredCoinCounts[i];
        }
    }


}
