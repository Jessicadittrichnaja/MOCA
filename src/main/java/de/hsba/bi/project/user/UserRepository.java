package de.hsba.bi.project.user;

import de.hsba.bi.project.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<de.hsba.bi.project.user.User, Long> {

    de.hsba.bi.project.user.User findByName(String name);

    Optional<User> findById(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u Set u.password = :password where u.id = :id")
    void updateUserPassword(@Param("password") String password, @Param("id") Integer id);

    // um sicherzustellen, dass ein Username nur einmal existiert

    @Query("SELECT Count(id) from User u where u.name= :name")
    Integer countNumberUsersWithSameName(@Param("name")String name);

    @Query("SELECT Count(id) from User u where u.name= :name and u.id != :id")
    Integer countNumberUsersWithSameNameThatAreNotEditedUser(@Param("name")String name, @Param("id")Integer id);

    @Query(value = "SELECT Count(id) from users_roles r INNER JOIN Role ro ON r.role_id = ro.id where ro.role = 'PERSONALABTEILUNG'",  nativeQuery = true)
    Integer countNumberUsersWithRoleHR();

    @Query(value = "SELECT Count(id) from users_roles r INNER JOIN Role ro ON r.role_id = ro.id where ro.role = 'PERSONALABTEILUNG' and r.user_id = :id",  nativeQuery = true)
    Integer checkIfUserHasRoleHR(@Param("id")Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isEnabled = false where u.id = :id")
    void disableUser(@Param("id") Integer id);

    @Query("SELECT u.isEnabled FROM User u WHERE u.id = :id")
    boolean isUserDisabled(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isEnabled = true where u.id = :id")
    void enableUser(@Param("id") Integer id);

    @Query("SELECT u.isEnabled FROM User u WHERE u.id = :id")
    boolean isUserEnabled(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("Delete FROM User u Where u.id = :id")
    void deleteUser(@Param("id") Integer id);
}
