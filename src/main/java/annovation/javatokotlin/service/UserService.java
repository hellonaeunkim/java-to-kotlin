package annovation.javatokotlin.service;

import annovation.javatokotlin.repository.UserRepository;
import annovation.javatokotlin.entity.UserEntity;

import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // C
    public UserEntity createUser(String username, String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(email);

        return userRepository.save(userEntity);
    }

    // R
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    // U
    public UserEntity updateUser(Long id, String username) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id" + id));

        userEntity.setUsername(username);

        return userRepository.save(userEntity);
    }

    // D
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
