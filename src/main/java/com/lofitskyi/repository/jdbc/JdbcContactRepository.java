package com.lofitskyi.repository.jdbc;

import com.lofitskyi.entity.Contact;
import com.lofitskyi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcContactRepository implements ContactRepository{

    @Autowired
    private JdbcTemplate jdbc;


    @Override
    public List<Contact> findAll() {
        return jdbc.query(
                "select id, firstName, lastName, phoneNumber, emailAddress " +
                        "from contacts order by lastName",
                (rs, rowNum) -> {
                    Contact contact = new Contact();
                    contact.setId(rs.getLong(1));
                    contact.setFirstName(rs.getString(2));
                    contact.setLastName(rs.getString(3));
                    contact.setPhoneNumber(rs.getString(4));
                    contact.setEmailAddress(rs.getString(5));
                    return contact;
                });
    }

    @Override
    public void save(Contact contact) {
        jdbc.update(
                "insert into contacts " +
                        "(firstName, lastName, phoneNumber, emailAddress) " +
                        "values (?, ?, ?, ?)",
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneNumber(),
                contact.getEmailAddress());
    }
}
