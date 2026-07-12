package za.ac.cput.service;

import za.ac.cput.domain.Admin;

import java.util.List;


public interface IAdminService extends Service<Admin, String> {
    List<Admin> getAll();
    Admin findByEmail(String email);
    Admin findByEmailAndPassword(String email, String password);
}
