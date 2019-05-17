package com.adundar.userservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.adundar.userservice.bus.producer.MessageProducerService;
import com.adundar.userservice.exception.AlreadyExistException;
import com.adundar.userservice.exception.BadRequestException;
import com.adundar.userservice.exception.NotFoundException;
import com.adundar.userservice.model.Result;
import com.adundar.userservice.model.User;
import com.adundar.userservice.repository.UserRepository;
import com.adundar.userservice.utils.Utils;

@Service
public class UserService {

    private final Logger   LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository         userRepository;

    @Autowired
    MessageProducerService messageProducerService;

    public Result<?> createUser(User user) throws AlreadyExistException {

        LOGGER.debug("[createUser]: User is : {}", user.toString());

        if (userRepository.findByName(user.getName()) != null)
            throw new AlreadyExistException(Utils.getUserNameAlreadyExistError(user.getName()));

        userRepository.save(user);

        messageProducerService.sendCreateUserEvent(user);

        return Result.success(HttpStatus.CREATED, user);
    }

    public Result<?> getAllUsers() {
        LOGGER.debug("[getAllUsers]: Get All Users request is processing.");
        return Result.success(HttpStatus.OK, userRepository.findAll());
    }

    public Result<?> retrieveUser(String userId) throws NotFoundException {
        LOGGER.debug("[retrieveUser]: Get User request is processing. userId -> {}", userId);

        User user = userRepository.findOne(userId);
        if (user == null)
            throw new NotFoundException(Utils.getUserIdNotFoundError(userId));

        LOGGER.debug("[retrieveUser]: Get User request is processing. user -> {}", user.toString());

        return Result.success(HttpStatus.OK, user);
    }

    public Result<?> retrieveUserByName(String name) throws NotFoundException {
        LOGGER.debug("[retrieveUserByName]: Get User request is processing. name -> {}", name);

        User user = userRepository.findByName(name);
        if (user == null)
            throw new NotFoundException(Utils.getUserNameNotFoundError(name));

        LOGGER.debug("[retrieveUserByName]: Get User request is processing. user -> {}", user.toString());

        return Result.success(HttpStatus.OK, user);
    }

    public Result<?> deleteUser(String userId) throws NotFoundException {
        LOGGER.debug("[deleteUser]: Delete User request is processing. userId -> {}", userId);

        User user = userRepository.findOne(userId);
        if (user == null)
            throw new NotFoundException(Utils.getUserIdNotFoundError(userId));

        userRepository.delete(user);

        messageProducerService.sendDeleteUserEvent(user);

        LOGGER.debug("[deleteUser]: Delete User request is processing. user -> {}", user.toString());

        return Result.success(HttpStatus.OK, user);
    }

    public Result<?> updateUser(User user, String userId) throws BadRequestException, NotFoundException {
        LOGGER.debug("[updateUser]: Update User request is processing. userId -> {}", userId);

        User userOld = userRepository.findOne(userId);
        if (userOld == null)
            throw new NotFoundException(Utils.getUserIdNotFoundError(userId));

        if (!userOld.getName().equals(user.getName()))
            throw new BadRequestException(Utils.USER_NAME_CHANGE_ERROR);

        user.setId(userOld.getId());

        User updatedUser = userRepository.save(user);

        messageProducerService.sendUpdateUserEvent(updatedUser);

        LOGGER.debug("[updateUser]: Update User request is processing. user -> {}", updatedUser.toString());

        return Result.success(HttpStatus.OK, updatedUser);
    }
}
