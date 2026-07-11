//
package za.ac.cput.service;

import za.ac.cput.domain.User;
import java.util.List;

public interface IUserService extends Service<User, String> {
    List<User> getAll();
}
