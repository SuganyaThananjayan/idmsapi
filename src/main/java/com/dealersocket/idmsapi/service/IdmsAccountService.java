package com.dealersocket.idmsapi.service;

import com.dealersocket.idmsapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.dealersocket.idmsapi.repository.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class IdmsAccountService {

    @Autowired
    private AccountDataRepository accountDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.data.url}")
    private String dataUrl;

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

    @Value("${api.layout-id}")
    private String layoutId;

    @Value("${api.account-status}")
    private String accountStatus;

    @Value("${api.institution-id}")
    private String institutionId;

    @Value("${api.page-number}")
    private String pageNumber;

    // Step 1: Authenticate and Get JWT Token From IDMSAPI
    private String getJwtToken() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // Prepare the authentication request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String urlWithParams = UriComponentsBuilder.fromHttpUrl(dataUrl + "/api/authenticate/GetUserAuthorizationToken")
                .queryParam("username", username)
                .queryParam("password", password).
                queryParam("InstitutionID", institutionId)
                .toUriString();
        try {
            // Make the authentication request
            ResponseEntity<AuthResponse> response = restTemplate.getForEntity(urlWithParams, AuthResponse.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().getToken();
            } else {
                throw new RuntimeException("Failed to authenticate and retrieve token");
            }

        } catch (HttpClientErrorException e) {
            throw new Exception("Error during authentication: " + e.getMessage());
        }
    }

    // Step 2: Fetch Data From IDMSAPI Using JWT Token
    private List<DataWrapper> fetchData(String jwtToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            String urlWithParams = UriComponentsBuilder.fromHttpUrl(dataUrl + "/api/Account/GetAccountList")
                    .queryParam("Token", jwtToken)
                    .queryParam("LayoutID", layoutId)
                    .queryParam("PageNumber", pageNumber)
                    .queryParam("InstitutionID", institutionId)
                    .queryParam("AccountStatus", accountStatus)
                    .toUriString();
            ResponseEntity<GetResponse> response = restTemplate.exchange(urlWithParams, HttpMethod.GET, null, new ParameterizedTypeReference<GetResponse>() {
            });

            GetResponse getResponse = response.getBody();
            if (getResponse != null && getResponse.getData() != null) {
                // Process the list of accounts
                List<DataWrapper> wrapper = getResponse.getData();
            }
            if (response.getStatusCode() == HttpStatus.OK) {
                return getResponse.getData();

            } else {
                throw new Exception("Failed to fetch data from the API.");
            }
        } catch (HttpClientErrorException e) {
            throw new Exception("Error during data fetch: " + e.getMessage());
        }
    }

    // Step 3: Store IDMSAPI Data in Database
    public void fetchDataAndStore() {
        try {
            // Step 1: Get JWT Token
            String jwtToken = getJwtToken();

            // Step 2: Fetch Data from the API using the JWT token
            List<DataWrapper> data = fetchData(jwtToken);

            for (DataWrapper wrapper : data) {
                AccountData accountData = wrapper.getRow();
                String acctID = accountData.getAcctID();
                if (!accountDataRepository.existsByAcctID(acctID)) {
                    accountDataRepository.save(accountData);

                } else {
                    System.out.println("Duplicate data found for: " + acctID);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

    }
    public List<AccountData> getAllVehicleData() {
        return accountDataRepository.findAll();
    }


}





