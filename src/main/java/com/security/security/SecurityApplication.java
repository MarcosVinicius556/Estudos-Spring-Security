package com.security.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	
}

@RestController
class HttpController {

	@GetMapping("/public")
	String publicRoute(){
		return "<h1>Rota pública!</h1>";
	}

	/**
	 * @apiNote Esta rota utiliza o padrão de cookie para verificar a autenticação do usuário
	 * @param principal
	 * @return
	 */
	@GetMapping("/cookie") //Ao ser chamado, faz a integração com o Google, e após feiot o login, pega o Client retornado pelo google, para poder utilizar nas proximas requisições
	String cookieRoute(@AuthenticationPrincipal OidcUser principal){ 
		return "<h1>Rota privada utilizando cookies para autenticação! "+ principal.getEmail() +" </h1>";
	}

	@GetMapping("/jwt")
	String jwtRoute(@AuthenticationPrincipal Jwt jwt){
		return "<h1>Rota privada utilizando JWT para autenticação! "+ jwt.getClaim("email") +"</h1>";
	}


}