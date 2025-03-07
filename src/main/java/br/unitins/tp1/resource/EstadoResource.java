package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.model.Estado;
import br.unitins.tp1.repository.EstadoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("estados")
public class EstadoResource {

    @Inject
    protected EstadoRepository estadoRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> buscarTodos() { 
        return estadoRepository.findAll().list();
    }


    @GET
    @Path("/sigla/{sigla}")
    @Produces(MediaType.APPLICATION_JSON)
    public Estado buscarPorSigla(@PathParam("sigla") String sigla) {
        return estadoRepository.findBySigla(sigla);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Estado incluir(Estado estado) {

        Estado novoEstado = new Estado();
        novoEstado.setNome(estado.getNome());
        novoEstado.setSigla(estado.getSigla());

        // realizando inclusao
        estadoRepository.persist(novoEstado);

        return novoEstado;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void alterar(@PathParam("id") Long id, Estado estado) {

        Estado edicaoEstado = estadoRepository.findById(id);

        edicaoEstado.setNome(estado.getNome());
        edicaoEstado.setSigla(estado.getSigla());
        
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(@PathParam("id") Long id) {
        estadoRepository.deleteById(id);
    }

    @GET
    @Path("/nome/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> buscarPorNome(@PathParam("nome") String nome) {
        return estadoRepository.findByNome(nome);
    }
}
