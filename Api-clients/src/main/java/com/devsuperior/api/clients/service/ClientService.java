package com.devsuperior.api.clients.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.api.clients.dto.ClientDto;
import com.devsuperior.api.clients.entity.Client;
import com.devsuperior.api.clients.entity.repository.ClientRepository;
import com.devsuperior.api.clients.service.exceptions.DataBaseException;
import com.devsuperior.api.clients.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDto> FindAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDto(x));
	}

	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found " + id));
		return new ClientDto(entity);
	}

	@Transactional
	public ClientDto insert(ClientDto dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDto(entity);
	}

	@Transactional
	public ClientDto update(Long id, ClientDto dto) {
		try {
			Client entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClientDto(entity);

		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found " + id);
		}
	}

	public void deleteById(Long id) {
		try {
		 repository.deleteById(id);
	
		}catch(EntityNotFoundException e) {
		throw new EntityNotFoundException("Entity not found " + id);
	
		}catch(DataIntegrityViolationException e) {
		
		throw new DataBaseException("Integrity violation");
	}
	}
	private void copyDtoToEntity(ClientDto dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());

	}
}
