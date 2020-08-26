package pl.lyszczarzmariusz.FacebookWeb.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lyszczarzmariusz.FacebookWeb.models.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);
}
