package jbc.oct21.jindanupajit.blogapplication.repository;

import jbc.oct21.jindanupajit.blogapplication.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Long> {
    User findByName(String name);
}
