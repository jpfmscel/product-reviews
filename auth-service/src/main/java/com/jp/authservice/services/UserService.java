package com.jp.authservice.services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jp.authservice.entities.User;
import com.jp.authservice.exceptions.RestResponseException;
import com.jp.authservice.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	protected UserRepository repository;

	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public User findById(String id) throws RestResponseException {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		throw new RestResponseException("User does not exist!");
	}

	public User update(User u) throws RestResponseException {
		return repository.save(u);
	}

	public User insert(User u) throws RestResponseException {
		try {
			User findByEmail = findByEmail(u.getEmail());
			if (findByEmail != null) {
				throw new RestResponseException("This e-mail is already registered!");
			}
			applyHashAndSalt(u);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return repository.save(u);
	}

	public String getHash(User u) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(u.getPassword().toCharArray(), u.getSalt().getBytes(), 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		byte[] hash = factory.generateSecret(spec).getEncoded();
		return String.copyValueOf(Hex.encodeHex(hash, true));
	}

	public void applyHashAndSalt(User u) throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (StringUtils.isEmpty(u.getSalt())) {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			u.setSalt(String.copyValueOf(Hex.encodeHex(salt, true)));
		}

		if (StringUtils.isEmpty(u.getHash())) {
			u.setHash(getHash(u));
		}
		u.setPassword(null);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public void delete(String id) throws RestResponseException {
		User user = findById(id);
		if (user != null)
			repository.deleteById(id);
	}
}
