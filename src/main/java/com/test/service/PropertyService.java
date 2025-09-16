package com.test.service;

import com.test.model.Property;
import com.test.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with ID: " + id));
    }

    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, Property updatedProperty) {
        Property existing = getPropertyById(id);
        existing.setTitle(updatedProperty.getTitle());
        existing.setType(updatedProperty.getType());
        existing.setPrice(updatedProperty.getPrice());
        existing.setBedrooms(updatedProperty.getBedrooms());
        existing.setBathrooms(updatedProperty.getBathrooms());
        existing.setArea(updatedProperty.getArea());
        existing.setLocation(updatedProperty.getLocation());
        existing.setImage(updatedProperty.getImage());
        existing.setFeatured(updatedProperty.isFeatured());
        return propertyRepository.save(existing);
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
