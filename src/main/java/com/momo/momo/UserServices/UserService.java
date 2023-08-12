package com.momo.momo.UserServices;

import com.momo.momo.EntitiesUser.User;
import com.momo.momo.userRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repo;
    public List<User> userList() {

        List<User> userList=  repo.findAll();
//        repo.findAll().stream().map(userMapper::toDto).collect(
//                Collectors.toList());
        return userList;
    }

    public void save(User user) {
        checkIdalreadyuse(user.getId());
        repo.save(user);

    }

    private void checkIdalreadyuse(Integer id) {
        if (repo.existsById(id)) {
            throw new RuntimeException("il existe deja un patient avec ce nom");
        }
    }

    public User get( Integer id) throws UserNotFoundException {
       Optional<User> users = repo.findById(id);
       if (users.isPresent()){
        return  users.get();}
       throw new UserNotFoundException("l'utilisateur avec ID "+id+ " n'est pas present");
    }


    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countByid(id);
        Countelement(id, count);
        repo.deleteById(id);
    }

    private static void Countelement(Integer id, Long count) throws UserNotFoundException {
        if (count == null || count == 0) {
            throw new UserNotFoundException("could not find any user with id:" + id);
        }
    }
}
