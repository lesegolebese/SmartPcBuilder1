
package za.ac.cput.service;

import za.ac.cput.domain.Specification;
import java.util.List;

public interface ISpecificationService extends Service<Specification, Long> {
    List<Specification> getAll();
}