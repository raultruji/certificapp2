package com.raultruji.certificapp2.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Genera la respuesta que nos devuelve el token,
 * tanto en auth como para login
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

	String token;
}
