package de.hsba.bi.project.user;

import de.hsba.bi.project.events.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<de.hsba.bi.project.user.User, Long> {

    de.hsba.bi.project.user.User findByName(String name);

    List<de.hsba.bi.project.user.User> findByRole(String role);

    Optional<User> findById(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u Set u.password = :password where u.id = :id")
    void updateUserPassword(@Param("password") String password, @Param("id") Integer id);

    // um sicherzustellen, dass ein Username nur einmal existiert

    @Query("SELECT Count(id) from User u where u.name= :name")
    Integer countNumberUsersWithSameName(@Param("name")String name);

    @Transactional
    @Modifying
    @Query("UPDATE User u Set u.name = :name , u.role = :role where u.id = :id")
    void updateUserName(@Param("name") String name, @Param("id") Integer id, @Param("role") Role role);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isDeactive = true where u.id = :id")
    void deactiveUser(@Param("id") Integer id);

    @Query("SELECT u.isDeactive FROM User u WHERE u.id = :id")
    boolean isUserDeactive(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isDeactive = false where u.id = :id")
    void activeUser(@Param("id") Integer id);

    @Query("SELECT u.isDeactive FROM User u WHERE u.id = :id")
    boolean isUserActive(@Param("id") Integer id);
}
