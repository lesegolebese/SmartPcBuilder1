//
package za.ac.cput.service;

public interface Service<T, ID> {
    T create(T t);
    T read(ID id);
    T update(T t);
    boolean delete(ID id);
}