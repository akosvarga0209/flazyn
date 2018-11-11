package com.flazyn.map;

import com.flazyn.dto.UserObjectDTO;
import com.flazyn.dto.UserProfileDTO;
import com.flazyn.entities.User;

public class UserMapper {

    public static UserObjectDTO userToUserObject(User user) {
        return new UserObjectDTO(user.getEmail(),
                user.getProfilePicture(),
                user.getFullName(),
                user.getBalance(),
                user.getAge());
    }

    public static User profileToUser(UserProfileDTO dto, User user) {
        user.setRoomMateSearch(dto.getRoomMateSearch());
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());
        user.setPet(dto.getPet());
        user.setSmoke(dto.getSmoke());
        user.setMinPrice(dto.getPriceMin());
        user.setMaxPrice(dto.getPriceMax());
        user.setLocation(dto.getLocation());
        user.setSharedRoom(dto.getSharedRoom());
        user.setFurnished(dto.getFurnished());

        return user;
    }

    public static UserProfileDTO userToProfile(User user) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setAge(user.getAge());
        dto.setFurnished(user.getFurnished());
        dto.setGender(user.getGender());
        dto.setLocation(user.getLocation());
        dto.setMaxNumberOfRoomMates(user.getMaxNumberOfRoomMates());
        dto.setNature(NatureMapper.entityToDTO(user.getNatures()));
        dto.setPet(user.getPet());
        dto.setPriceMax(user.getMaxPrice());
        dto.setPriceMin(user.getMinPrice());
        dto.setSharedRoom(user.getSharedRoom());
        dto.setRoomMateSearch(user.getRoomMateSearch());
        dto.setSmoke(user.getSmoke());

        return dto;
    }
}