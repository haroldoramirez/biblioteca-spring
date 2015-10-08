package br.org.itai.identidade.services;

import br.org.itai.identidade.models.Autor;
import br.org.itai.identidade.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    public Autor inserir(Autor autor) {
        return this.autorRepository.save(autor);
    }

    public Autor atualizar(Autor autor) {
        return this.autorRepository.save(autor);
    }

    public Autor buscaPorId(Long id) {
        return this.autorRepository.findOne(id);
    }

    public List<Autor> buscaTodos() {
        return this.autorRepository.findAll();
    }

    public void remover(Long id) {
        Autor autor = this.autorRepository.findOne(id);
        this.autorRepository.delete(autor);
    }

}
