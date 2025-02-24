package annovation.javatokotlin.service

import annovation.javatokotlin.entity.UserEntity
import annovation.javatokotlin.repository.UserRepository
import java.util.*

class UserService(private val userRepository: UserRepository) {
    // C
    fun createUser(username: String, email: String): UserEntity {
        val userEntity = UserEntity()
        userEntity.username = username
        userEntity.email = email

        return userRepository.save(userEntity)
    }

    // R
    fun findById(id: Long): Optional<UserEntity> {
        return userRepository.findById(id)
    }

    // U
    fun updateUser(id: Long, username: String): UserEntity {
        val userEntity = userRepository.findById(id)
            .orElseThrow {
                RuntimeException(
                    "User not found with id$id"
                )
            }

        userEntity.username = username

        return userRepository.save(userEntity)
    }

    // D
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}
