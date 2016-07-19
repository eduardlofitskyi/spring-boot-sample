package com.lofitskyi.repository;

import com.lofitskyi.entity.Contact;

import java.util.List;

public interface ContactRepository {

    List<Contact> findAll();

    void save(Contact contact);
}
