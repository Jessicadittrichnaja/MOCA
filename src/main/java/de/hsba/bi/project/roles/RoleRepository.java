package de.hsba.bi.project.roles;

import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.Location;
import de.hsba.bi.project.events.Time;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.web.EventForm;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    de.hsba.bi.project.roles.Role findByRole(String role);

}

