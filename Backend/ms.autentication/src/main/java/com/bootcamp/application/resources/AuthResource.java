package com.bootcamp.application.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bootcamp.application.dtos.AuthToken;
import com.bootcamp.application.dtos.BasicCredential;
import com.bootcamp.application.dtos.NuevoUsuarioDTO;
import com.bootcamp.application.dtos.UsuarioDTO;
import com.bootcamp.domains.contracts.services.UsuarioService;
import com.bootcamp.domains.entities.Usuario;
import com.bootcamp.exceptions.DuplicateKeyException;
import com.bootcamp.exceptions.InvalidDataException;
import com.bootcamp.exceptions.NotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
//	@CrossOrigin(origins = "http://localhost:4200", allowCredentials="true", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
//	@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials="false")
public class AuthResource {
	@Value("${jwt.secret}")
	private String SECRET;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UsuarioService srv;

	@RequestMapping(path = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<AuthToken> login(@RequestParam("name") String username,
			@RequestParam("password") String pwd) {

		Usuario usr;
		try {
			usr = srv.getOne(username);
			if (!passwordEncoder.matches(pwd, usr.getPassword()))
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		return ResponseEntity.ok(new AuthToken(true, getJWTToken(usr), username, usr.getRol()));
	}

	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<AuthToken> loginPostJSON(@RequestBody BasicCredential usr) {
		return login(usr.getUsername(), usr.getPassword());
	}

	private String getJWTToken(Usuario usr) {
		List<GrantedAuthority> grantedAuthorities = usr.getRol().contains(",")
				? AuthorityUtils.commaSeparatedStringToAuthorityList(usr.getRol())
				: AuthorityUtils.createAuthorityList(usr.getRol());
		String token = Jwts.builder().setId("MicroserviciosJWT").setSubject(usr.getUsername())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
		return "Bearer " + token;
	}

	/**
	 * /register (anonimo) /profile (Authorization) (get, put) menos la contraseña
	 * /users (Admin)(get, post, put, delete) + roles menos la contraseña
	 */
	@PostMapping(path="/register")
	public ResponseEntity<Object> create(@Valid @RequestBody NuevoUsuarioDTO item) throws InvalidDataException, DuplicateKeyException {
		Usuario usuario = NuevoUsuarioDTO.from(item);
		// Minimo 8 caracteres
//		Maximo 15
//		Al menos una letra mayúscula
//		Al menos una letra minucula
//		Al menos un dígito
//		No espacios en blanco
//		Al menos 1 caracter especial
//		/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,15}/
		usuario.setPassword(passwordEncoder.encode(item.getPassword()));
		if(usuario.isInvalid())
			throw new InvalidDataException(usuario.getErrorsMessage());
		usuario = srv.add(usuario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(usuario.getUsername()).toUri();
		return ResponseEntity.created(location).build();

	}
}
