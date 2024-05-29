package com.rahul.companyMicroService.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("REVIEW-SERVICE")
public interface ReviewClients {

    @GetMapping("/reviews/average-ratings")
    Double getAverateRatingForCompany(@RequestParam("companyId") Long companyId);


}
