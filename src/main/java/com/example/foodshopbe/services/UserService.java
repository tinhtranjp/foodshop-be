package com.example.foodshopbe.services;

import com.example.foodshopbe.components.JwtTokenUtil;
import com.example.foodshopbe.dtos.UserDTO;
import com.example.foodshopbe.exceptions.DataNotFoundException;
import com.example.foodshopbe.models.Role;
import com.example.foodshopbe.models.User;
import com.example.foodshopbe.repositories.RoleRepository;
import com.example.foodshopbe.repositories.UserRepository;
import com.example.foodshopbe.services.Imp.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public User createUser(UserDTO userDTO) throws Exception {

        String email = userDTO.getEmail();
        String loginId = userDTO.getLoginId();


        if(userRepository.existsByLoginId(loginId)) {
            throw new Exception("LoginId 既に利用しました。");
        }

        if (userRepository.existsByEmail(email)) {
            throw new Exception("Email 既に利用しました。");
        }


        User newUser = User
                .builder()
                .fullName(userDTO.getFullName())
                .furiganaName(userDTO.getFuriganaName())
                .phoneNumber(userDTO.getPhoneNumber())
                .faxNumber(userDTO.getFaxNumber())
                .gender(userDTO.getGender())
                .dateOfBirth(userDTO.getDateOfBirth())
                .emailAccept(userDTO.getEmailAccept())
                .loginId(userDTO.getLoginId())
                .password(userDTO.getPassword())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .isActive(true)
                .email(userDTO.getEmail())
                .build();

        Role role = roleRepository.findByName("USER")
                .orElseThrow(()-> new DataNotFoundException("Khong tim thay role nao voi ten la USER"));

        newUser.setRole(role);

        if(userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
            String password = userDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            newUser.setPassword(encodedPassword);
        }

        return userRepository.save(newUser);

    }

    @Override
    public String login(String loginId, String password, Integer roleId) throws Exception {
        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        if(optionalUser.isEmpty()) {
            throw new DataNotFoundException("LoginIdとパスワードとどちらかが正しくないです。");
        }

        User existingUser = optionalUser.get();

        if(existingUser.getFacebookAccountId() == 0 && existingUser.getGoogleAccountId() == 0) {
            if (!passwordEncoder.matches(password, existingUser.getPassword())) {
                throw new BadCredentialsException("LoginIdとパスワードとどちらかが正しくないです。");
            }
        }

        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if(optionalRole.isEmpty() || !roleId.equals(existingUser.getRole().getId())) {
            throw new DataNotFoundException("LoginIdとパスワードとどちらかが正しくないです。");
        }
        if(!optionalUser.get().getIsActive()) {
            throw new DataNotFoundException("Tai khoan cua ban chua duoc kich hoat, vui long lien he voi admin");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginId,password, existingUser.getAuthorities()
        );



        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser);
    }
}





















