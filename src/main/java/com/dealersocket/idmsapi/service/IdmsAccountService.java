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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IdmsAccountService {

    @Autowired
    private AccountDataRepository accountDataRepository;

    @Autowired
    private  RestTemplate restTemplate;
    public  String getVechicleData() throws Exception {

        String token= getJwtToken();
        return token;


    }


        @Value("${api.data.url}")
        private  String dataUrl;

        @Value("${api.username}")
        private  String username;

        @Value("${api.password}")
        private  String password;

     /*   private final DataRepository dataRepository;


       public DataService(DataRepository dataRepository, RestTemplate restTemplate) {
            this.dataRepository = dataRepository;
            this.restTemplate = restTemplate;
        }*/

        // Step 1: Authenticate and Get JWT Token
        private  String getJwtToken() throws Exception {
            RestTemplate restTemplate = new RestTemplate();

            // Prepare the authentication request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String urlWithParams = UriComponentsBuilder.fromHttpUrl(dataUrl+"/api/authenticate/GetUserAuthorizationToken")
                    .queryParam("username", username)
                    .queryParam("password", password)
                    .queryParam("InstitutionID", 107007)
                    .toUriString();
            try {
                // Make the authentication request

                //ResponseEntity<String> response = restTemplate.getForEntity(urlWithParams, String.class);
               // ResponseEntity<String> response = restTemplate.getForEntity();
                //MediaType headers1 = response.getHeaders().getContentType();
               ResponseEntity<AuthResponse> response = restTemplate.getForEntity(urlWithParams, AuthResponse.class);
                //ResponseEntity<AuthResponse> response = restTemplate.exchange(urlWithParams, HttpMethod.GET, null, AuthResponse.class);
                System.out.println("********response*******"+response);
                System.out.println("********response*******"+response.getBody().toString());

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    System.out.println("********inside *******"+response.toString());
                   return response.getBody().getToken();
                } else {
                    throw new RuntimeException("Failed to authenticate and retrieve token");
                }

            } catch (HttpClientErrorException e) {
                throw new Exception("Error during authentication: " + e.getMessage());
            }
        }

        // Step 2: Fetch Data Using JWT Token
        private List<DataWrapper> fetchData(String jwtToken) throws Exception {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + jwtToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            try {
                String urlWithParams = UriComponentsBuilder.fromHttpUrl(dataUrl+"/api/Account/GetAccountList")
                        .queryParam("Token", jwtToken)
                        .queryParam("LayoutID", 2006084)
                        .queryParam("PageNumber", 1)
                        .queryParam("InstitutionID", 107007)
                        .queryParam("AccountStatus", "a")
                        .toUriString();
                System.out.println("********Url fetch*******"+urlWithParams);
                System.out.println("********response before*******"+restTemplate.exchange(urlWithParams, HttpMethod.GET, null, new ParameterizedTypeReference<GetResponse>() {}));
              //  ResponseEntity<List<IdmsAccount>> response = restTemplate.getForEntity(urlWithParams, new ParameterizedTypeReference<List<IdmsAccount>>() {});
                ResponseEntity<GetResponse> response = restTemplate.exchange(urlWithParams, HttpMethod.GET, null, new ParameterizedTypeReference<GetResponse>() {});
                System.out.println("********response*******"+response.getBody());

                GetResponse getResponse=response.getBody();
                if (getResponse != null && getResponse.getData() != null) {
                    // Process the list of accounts
                    System.out.println("********response Data*******"+getResponse.getData());

                    List<DataWrapper> wrapper=getResponse.getData();
                    /*for (DataWrapper wrapper : getResponse.getData()) {
                        System.out.println("********response row*******"+wrapper.getRow());
                            IdmsAccount account = wrapper.getRow();
                        System.out.println("Account"+account);

                        // Extract the account from the 'row' field
                        System.out.println("Account ID: " + account.getDealId());
                        System.out.println("Account Name: " + account.getName());
                        // Save to the database (you can implement this part)
                    }*/
                }
                if (response.getStatusCode() == HttpStatus.OK) {
                    return getResponse.getData();
                    //return response.getBody();
                } else {
                    throw new Exception("Failed to fetch data from the API.");
                }
            } catch (HttpClientErrorException e) {
                throw new Exception("Error during data fetch: " + e.getMessage());
            }
        }

        // Step 3: Store Data in Database
        public void fetchDataAndStore() {
            try {
                // Step 1: Get JWT Token
                String jwtToken = getJwtToken();
                List<DataWrapper> data=fetchData(jwtToken);
                // Step 2: Fetch Data from the API using the JWT token
                //List<Map<Long, Object>> dataList = fetchData(jwtToken);
                for (DataWrapper wrapper : data) {
                    System.out.println("********response row*******"+wrapper.getRow());
                  //  IdmsAccount account = wrapper.getRow();
                    AccountData accountData = wrapper.getRow();
                    String acctID = accountData.getAcctID();
                    if (!accountDataRepository.existsByAcctID(acctID)) {
                        System.out.println("Account"+accountData);
                        accountDataRepository.save(accountData);
                       /* AccountData idmsAccount = new IdmsAccount();
                        idmsAccount.setDealId(dealId);
                        idmsAccount.setName(name);
                        idmsAccountRepository.save(idmsAccount);*/

                    } else {
                        System.out.println("Duplicate data found for: " + acctID);
                    }
                    System.out.println("Account"+accountData);


                    // Save to the database (you can implement this part)
                }


                // Step 3: Store data in PostgreSQL, preventing duplicates
                /*if (dataList != null) {
                    for (Map<Long, Object> data : dataList) {
                        Long id = (Long) data.get("FIUserID");
                        String name = (String) data.get("LenderName");

                        // Check if the record already exists
                        if (!idmsAccountRepository.findById(id).isPresent()) {
                            IdmsAccount idmsAccount = new IdmsAccount();
                            idmsAccount.setId(id);
                            idmsAccount.setName(name);
                            idmsAccountRepository.save(idmsAccount);
                        } else {
                            System.out.println("Duplicate data found for: " + name);
                        }
                    }
                }*/
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
                // You can also use a logging framework like SLF4J for logging the errors.
            }

        }
    public List<AccountData> getAllVehicleData() {
        return accountDataRepository.findAll();
    }




}





