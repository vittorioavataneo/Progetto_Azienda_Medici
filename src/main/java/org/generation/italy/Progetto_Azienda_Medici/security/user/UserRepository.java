package org.generation.italy.Progetto_Azienda_Medici.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  @Query("""
          select p.id
          from Person p join p.user u
          where u.email = :email
          """)
  Optional<Long> findPersonIdByUserEmail(String email);

}
