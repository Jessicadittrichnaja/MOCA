package de.hsba.bi.project.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface UserRepository extends JpaRepository<de.hsba.bi.project.user.User, Long> {

    de.hsba.bi.project.user.User findByName(String name);

    List<de.hsba.bi.project.user.User> findByRole(String role);
}
