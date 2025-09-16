package com.test.service;

import com.test.model.Contact;
import com.test.repository.ContactRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // CREATE
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // READ
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
    }

    // UPDATE
    public Contact updateContact(Long id, Contact updatedContact) {
        Contact existing = getContactById(id);

        existing.setName(updatedContact.getName());
        existing.setEmail(updatedContact.getEmail());
        existing.setContact(updatedContact.getContact());
        existing.setMessage(updatedContact.getMessage());
        existing.setLoans(updatedContact.getLoans());

        return contactRepository.save(existing);
    }

    // DELETE
    public void deleteContact(Long id) {
        Contact contact = getContactById(id);
        contactRepository.delete(contact);
    }
}
