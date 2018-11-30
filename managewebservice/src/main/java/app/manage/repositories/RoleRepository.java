package app.manage.repositories;

import app.manage.domain.UserRoles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRoles,String> {
}
