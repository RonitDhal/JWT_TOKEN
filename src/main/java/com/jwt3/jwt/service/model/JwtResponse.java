package com.jwt3.jwt.service.model;

public class JwtResponse {
    private String jwtToken;
    private String username;

    // Private constructor to prevent direct instantiation
    private JwtResponse(String jwtToken, String username) {
        this.jwtToken = jwtToken;
        this.username = username;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getUsername() {
        return username;
    }

    // Builder static inner class
    public static class JwtResponseBuilder {
        private String jwtToken;
        private String username;

        public JwtResponseBuilder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public JwtResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(jwtToken, username);
        }
    }

    // Static method to create a builder instance
    public static JwtResponseBuilder builder() {
        return new JwtResponseBuilder();
    }
}


