package com.dealersocket.idmsapi.controller;

import com.dealersocket.idmsapi.model.AccountData;
import com.dealersocket.idmsapi.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dealersocket.idmsapi.service.IdmsAccountService;

import java.util.List;

@RestController

public class IdmsAccountController {

    @Autowired
    private IdmsAccountService idmsAccountService;

    @GetMapping("/fetchDataAndStore")
    public ResponseEntity<ApiResponse> fetchDataAndStore() {
        idmsAccountService.fetchDataAndStore();
        ApiResponse<String> response = new ApiResponse<>("200", "Account data fetched and saved from IDMS API successfully", true, "");
        return ResponseEntity.ok(response);
        //return ResponseEntity.ok("Account data fetched and saved successfully");
    }


    @GetMapping("/getAllVehicleData")
    public ResponseEntity<ApiResponse> getAllVehicleData() {
        ApiResponse<List<AccountData>> response = new ApiResponse<>("200", "Account data fetched successfully", true, idmsAccountService.getAllVehicleData());
        return ResponseEntity.ok(response);

    }
}
