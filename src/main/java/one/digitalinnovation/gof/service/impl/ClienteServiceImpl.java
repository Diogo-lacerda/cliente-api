package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.controller.dto.ClienteRequest;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.EnderecoRepository;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void inserir(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();

        cliente.setNome(clienteRequest.getNome());
        endereco.setCep(clienteRequest.getCep());

        cliente.setEndereco(endereco);

        salvarCliente(cliente);
    }

    private void salvarCliente(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        clienteRepository.findById(id)
                .ifPresent((clienteEncontrado) -> salvarCliente(cliente));
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.findById(id)
                .ifPresent(cliente -> clienteRepository.delete(cliente));
    }
}
