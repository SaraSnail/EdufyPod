package com.example.EdufyPod.clients;

import com.example.EdufyPod.exceptions.CallFailException;
import com.example.EdufyPod.exceptions.RestClientException;
import com.example.EdufyPod.models.DTO.callDTOs.UserDTO;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

//ED-283-SA
@Service
public class UserClientImpl implements UserClient {

    //ED-283-SA
    private final RestClient restClient;
    private final LoadBalancerClient loadBalancerClient;
    private final String lbUser = "EDUFYUSER";

    public UserClientImpl(RestClient.Builder restClientBuilder, LoadBalancerClient loadBalancerClient) {
        this.restClient = restClientBuilder.build();
        this.loadBalancerClient = loadBalancerClient;
    }

    //ED-283-SA
    public UserDTO getUserById(Long userId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(lbUser);
        String uri = "/user/user-id/" + userId + "/clientcall";
        try {
            return restClient.get()
                    .uri(serviceInstance.getUri()+"/user/user-id/" + userId + "/clientcall")
                    .retrieve()
                    .body(UserDTO.class);

        }catch (RestClientResponseException e){
            throw new CallFailException("User", uri, String.format(e.getMessage(), e));
        }catch (ResourceAccessException e){
            throw new RestClientException("Edufy Pod", "Edufy User");
        }

    }

    @Override
    public UserDTO getUserBySUB(String sub) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(lbUser);
        String uri = "/user/user-sub/" + sub + "/clientcall";
        try {
            return restClient.get()
                    .uri(serviceInstance.getUri()+"/user/user-sub/" + sub + "/clientcall")
                    .retrieve()
                    .body(UserDTO.class);

        }catch (RestClientResponseException e){
            throw new CallFailException("User", uri, String.format(e.getMessage(), e));
        }catch (ResourceAccessException e){
            throw new RestClientException("Edufy Pod", "Edufy User");
        }
    }
}
