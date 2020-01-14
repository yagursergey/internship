package com.syagur.resourceserver.common;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SystemController {

    private final HttpServletRequest request;

    public SystemController(HttpServletRequest request) {
        this.request = request;
    }

    @GetMapping("/logout")
    public String logout() throws ServletException {
        request.logout();
        return "";
    }

    @GetMapping("/token")
    public String getToken() {
        return getKeycloakSecurityContext().getTokenString();
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }

    @GetMapping("sso/login")
    public String customSSO() {
        return "";
    }

}
