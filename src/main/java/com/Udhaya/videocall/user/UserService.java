package com.Udhaya.videocall.user;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class UserService {
    private static final List<User> users_list = new ArrayList<>();

    public void register(User user){
        user.setStatus("online");
        users_list.add(user);
    }

    public User login(User user){
        var userIndex = IntStream.range(0,users_list.size())
                .filter(i -> users_list.get(i).getEmail().equals(user.getEmail()))
                .findAny()
                .orElseThrow( () -> new RuntimeException("User not found"));

        var cUser = users_list.get(userIndex);
        if(!cUser.getPassword().equals(user.getPassword())){
            throw new RuntimeException("Password is incorrect");
        }
        cUser.setStatus("online");
        return cUser;
    }

    public void logout(String email){
        var userIndex = IntStream.range(0,users_list.size())
                .filter(i -> users_list.get(i).getEmail().equals(email))
                .findAny()
                .orElseThrow( () -> new RuntimeException("User not found"));

        users_list.get(userIndex).setStatus("offline");
    }

    public List<User> findAll(){
        return users_list;
    }
}
