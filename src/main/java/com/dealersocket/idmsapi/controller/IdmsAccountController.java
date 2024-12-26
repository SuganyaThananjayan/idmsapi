package com.dealersocket.idmsapi.controller;

import com.dealersocket.idmsapi.model.AccountData;
import com.dealersocket.idmsapi.repository.IdmsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dealersocket.idmsapi.service.IdmsAccountService;

import java.util.List;

@RestController
//@Tag(name = "Vehicle API", description = "Endpoints for managing vehicle data")
public class IdmsAccountController {

    @Autowired
    private IdmsAccountService idmsAccountService;
    //@Operation(summary = "getVechicleData")
   // @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of vehicles")
    //@ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/getVechicleData")
    public String getVechicleData() throws Exception {
       // String token = idmsAccountService.getVechicleData();
       // return token;
        idmsAccountService.fetchDataAndStore();
         return "Account data fetched and saved successfully";
    }
   // @Operation(summary = "getAllVechicleData")
   // @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of vehicles")
   // @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/vehicle-data")
    public List<AccountData> getAllVehicleData() throws Exception {
        return idmsAccountService.getAllVehicleData();

    }
}
