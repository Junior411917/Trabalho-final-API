package org.serratec.backend.service;

import jakarta.transaction.Transactional;
import org.serratec.backend.config.MailConfig;
import org.serratec.backend.dto.ClienteRequestDTO;
import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.ClientePerfil;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.exception.ClienteException;
import org.serratec.backend.repository.ClientePerfilRepository;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ClientePerfilRepository clientePerfilRepository;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private MailConfig mailConfig;

    public List<ClienteResponseDTO> findAll(){
        return clienteRepository.findAll().stream().map(ClienteResponseDTO::new).collect(Collectors.toList());
    }

    public ClienteResponseDTO findById(Long id){
        return clienteRepository.findById(id).map(ClienteResponseDTO::new).orElseThrow(() -> new ClienteException("ID não encontrado."));
    }

    @Transactional
    public ClienteResponseDTO save(ClienteRequestDTO clienteRequestDTO){
        Optional<Cliente> cliente = clienteRepository.findByEmail(clienteRequestDTO.getEmail());

        cliente.ifPresent(c -> {
            throw new ClienteException("O email" + c.getEmail() + " já esta cadastrado.");
        });

        Cliente clienteEntity = new Cliente();
        Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(clienteRequestDTO.getCep()));
        if(endereco.isEmpty()){
            RestTemplate rs = new RestTemplate();
            String url = "http://viacep.com.br/ws/" + clienteRequestDTO.getCep() + "/json/";
            Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(url, Endereco.class));

            if(enderecoViaCep.isPresent()){
                String cepFormatado = enderecoViaCep.get().getCep().replaceAll("-", "");
                enderecoViaCep.get().setCep(cepFormatado);
                enderecoRepository.save(enderecoViaCep.get());
                clienteEntity.setEndereco(enderecoViaCep.get());
            }
        } else {
            clienteEntity.setEndereco(endereco.get());
        }

        clienteEntity.setNome(clienteRequestDTO.getNome());
        clienteEntity.setTelefone(clienteRequestDTO.getTelefone());
        clienteEntity.setEmail(clienteRequestDTO.getEmail());
        clienteEntity.setSenha(encoder.encode(clienteRequestDTO.getSenha()));
        clienteEntity.setCpf(clienteRequestDTO.getCpf());
        clienteEntity.setCadastro(LocalDate.now());
        clienteEntity.setStatus(true);

        for(ClientePerfil cp : clienteRequestDTO.getClientePerfis()){
            cp.setCliente(clienteEntity);
            cp.setPerfil(perfilService.findById(cp.getPerfil().getId()));
            cp.setDataCadastro(LocalDateTime.now());
        }

        clienteRepository.save(clienteEntity);
        clientePerfilRepository.saveAll(clienteRequestDTO.getClientePerfis());

        mailConfig.save(clienteEntity.getEmail(), "Confirmação de cadastro", clienteEntity.toString());

        return new ClienteResponseDTO(clienteEntity);
    }

    @Transactional
    public ClienteResponseDTO update(Long id, ClienteRequestDTO clienteRequestDTO){
        Optional<Cliente> cliente = Optional.ofNullable(clienteRepository.findById(id).orElseThrow(() -> new ClienteException("ID não encontrado.")));

        cliente.ifPresent(c -> {
            c.setId(id);
            c.setTelefone(clienteRequestDTO.getTelefone());
            c.setEmail(clienteRequestDTO.getEmail());
            c.setSenha(encoder.encode(clienteRequestDTO.getSenha()));
        });

        for(ClientePerfil cp : clienteRequestDTO.getClientePerfis()){
            cp.setCliente(cliente.get());
            cp.setPerfil(perfilService.findById(cp.getPerfil().getId()));
            cp.setDataCadastro(LocalDateTime.now());
        }

        clienteRepository.save(cliente.get());
        clientePerfilRepository.saveAll(clienteRequestDTO.getClientePerfis());

        mailConfig.update(cliente.get().getEmail(), "Atualização de cadastro", cliente.get().toString());

        return new ClienteResponseDTO(cliente.get());
    }

    @Transactional
    public String delete(Long id){
        Optional<Cliente> cliente = Optional.ofNullable(clienteRepository.findById(id).orElseThrow(() -> new ClienteException("ID não encontrado.")));
        if (cliente.get().getStatus().equals(false)) {
        	throw new ClienteException("O cliente já está desativado");
        }else {
        	cliente.get().setStatus(false);
        	clienteRepository.save(cliente.get());
        	mailConfig.delete(cliente.get().getEmail(), "Sua conta foi desativada", cliente.get().toString());
        	return "Cliente desativado!";
        }    
    }
}
