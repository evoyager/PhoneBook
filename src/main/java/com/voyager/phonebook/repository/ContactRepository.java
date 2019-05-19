package com.voyager.phonebook.repository;

import com.voyager.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByEmail(String email);
}
