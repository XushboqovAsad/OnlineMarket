package com.OnlineMarket.domain.status.location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {
    @GetMapping("/get-districts")
    public ResponseEntity<List<String>> getDistrictsByRegion(@RequestParam Region region) {
        List<String> districts = DistrictValidator.getDistrictsByRegion(region);
        return ResponseEntity.ok(districts);
    }
}
