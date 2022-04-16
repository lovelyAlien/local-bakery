package com.localbakery.account.controller;


import com.localbakery.account.service.HometownService;
import com.localbakery.api.controller.common.ResponseContainer;
import com.localbakery.authentication.payload.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private final HometownService hometownService;

    @ApiIgnore
    @GetMapping("/")
    public ResponseEntity<?> login(@RequestParam String token) {
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/api/account/hometown")
    public ResponseContainer<Long> registerHometown(@RequestParam("longitude") Long longitude, @RequestParam("latitude") Long latitude) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

        Long hometownId = hometownService.register(SecurityContextHolder.getContext().getAuthentication().getName(), point);

        return ResponseContainer.<Long>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(hometownId)
                .build();
    }
}
