package com.mycompany.propertymanagement.serviceImpl;

import com.mycompany.propertymanagement.converter.PropertyConvertor;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    PropertyConvertor propertyConvertor;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        {
            PropertyEntity pe=propertyConvertor.convertDTOEntity(propertyDTO);
            pe=propertyRepository.save(pe);
            propertyDTO=propertyConvertor.convertEntityToDTO(pe);
            return propertyDTO;
        }

    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> propertyList=(List<PropertyEntity>)propertyRepository.findAll();
        List<PropertyDTO>dtoList=new ArrayList<>();
        for(PropertyEntity property:propertyList)
        {
            PropertyDTO dto=propertyConvertor.convertEntityToDTO(property);
            dtoList.add(dto);
            return dtoList;
        }
        return null;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity>optEnt=propertyRepository.findById(propertyId);

        PropertyDTO dto=null;
        if(optEnt.isPresent())
        {
            PropertyEntity propertyEntity=optEnt.get();

            propertyEntity.setTitle(propertyDTO.getTitle());
            propertyEntity.setDescription(propertyDTO.getDescription());
            propertyEntity.setPrice(propertyDTO.getPrice());

            dto=propertyConvertor.convertEntityToDTO(propertyEntity);

            propertyRepository.save(propertyEntity);
        }
        return dto;
    }

    @Override
    public PropertyDTO updateProperty_desc(PropertyDTO propertyDTO, Long proprtyId) {
        Optional<PropertyEntity>optEn=propertyRepository.findById(proprtyId);
        PropertyDTO dto=null;
        if(optEn.isPresent())
        {
            PropertyEntity entity=optEn.get();
            entity.setDescription(propertyDTO.getDescription());
            dto=propertyConvertor.convertEntityToDTO(entity);

            propertyRepository.save(entity);
        }
            return dto;

    }

    @Override
    public PropertyDTO updateProperty_price(PropertyDTO propertyDTO, Long propertyId) {
        return null;
    }

    @Override
    public void deleteProperty(Long propertyId) {

        propertyRepository.deleteById(propertyId);

    }
}
