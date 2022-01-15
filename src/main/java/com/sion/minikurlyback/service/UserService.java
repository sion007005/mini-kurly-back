package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.UserDto;
import com.sion.minikurlyback.entity.User;
import com.sion.minikurlyback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long create(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        User user = new User(userDto);
        userRepository.save(user);

        return user.getIdx();
    }

    public UserDto findOneByIdx(Long idx) {
        User user = userRepository.findOneByIdx(idx);
        return new UserDto(user);
    }
}
