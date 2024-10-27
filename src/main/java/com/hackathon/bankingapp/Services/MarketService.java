package com.hackathon.bankingapp.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

@Service
public class MarketService {
    private final static String apiURL = "https://faas-lon1-917a94a7.doserverless.co/" +
            "api/v1/web/fn-e0f31110-7521-4cb9-86a2-645f66eefb63/default/market-prices-simulator";

    private Map getPricesMap () {
        try {
            URL url = new URI(apiURL).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            if (response != 200) {
                return null;
            }
            StringBuilder msg = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                msg.append(scanner.nextLine());
            }
            scanner.close();

            ObjectMapper mapper = new ObjectMapper();
            Map marketMap = mapper.readValue(msg.toString(), Map.class);
            return marketMap;
        } catch (Exception e) {
            return null;
        }
    }
    public ResponseEntity<?> getAllPrices() {
        Map map = getPricesMap();
        if (map == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(map);
    }

    public ResponseEntity<?> getItemPrice(String symbol) {
        Map map = getPricesMap();
        if (map == null) {
            return ResponseEntity.internalServerError().build();
        }
        if (!map.containsKey(symbol)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(map.get(symbol));
    }
}
