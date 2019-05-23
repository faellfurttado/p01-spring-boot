package dcomp.lpweb.projeto.api.repository.estadio;

import dcomp.lpweb.projeto.api.model.Estadio;
import dcomp.lpweb.projeto.api.repository.filter.EstadioFiltro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EstadioRepositoryImpl implements EstadioRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Estadio> filtrar(EstadioFiltro filtro, Pageable pageable) {

        // Usamos o CriteriaBuilder(cb) para criar a CriteriaQueyr (cQuery)
        // com a tipagem do tipo a ser selecionado (Estadio)
        CriteriaBuilder cBuilder = manager.getCriteriaBuilder();


        // 1. Select p From Estadio p
        CriteriaQuery<Estadio> cQuery = cBuilder.createQuery(Estadio.class );

        // 2. clausula from e joins
        Root<Estadio> estadioRoot = cQuery.from(Estadio.class );

        // 3. adiciona as restrições (os predicados) que serão passadas na clausula where
        Predicate[] restricoes = this.criaRestricoes(filtro, cBuilder, estadioRoot  );


        // 4. monta a consulta com as restrições
        cQuery.select(estadioRoot)
              .where(restricoes )
              .orderBy( cBuilder.desc(estadioRoot.get("nome")) );

        // 5. cria e executa a consula
        TypedQuery<Estadio> query = manager.createQuery(cQuery);
        adicionaRestricoesDePaginacao(query, pageable);

        return new PageImpl<Estadio>(query.getResultList(), pageable, total(filtro) );
    }

    private Predicate[] criaRestricoes(EstadioFiltro filtro, CriteriaBuilder cBuilder, Root<Estadio> estadioRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if ( !StringUtils.isEmpty( filtro.getNome()) ) {
            // where nome like %Computador%
            predicates.add(cBuilder.like(cBuilder.lower(estadioRoot.get("nome")), "%" + filtro.getNome().toLowerCase() + "%" ) );
        }

        if ( Objects.nonNull(filtro.getEndereco()) ) {
            predicates.add( cBuilder.like( estadioRoot.get("endereco"), filtro.getEndereco() ));
        }

        if( Objects.nonNull( filtro.getNome()  ) ) {
            predicates.add( cBuilder.like( estadioRoot.get("nome"), filtro.getNome() ));
        }

        if (Objects.nonNull(filtro.getEstadioId()) ) {

            // antes fazemos o join com categorias
            Path<Integer> categoriaPath = estadioRoot.join("estadios").<Integer>get("id");

            // semelhante a clausula "on" do critério de junção
            predicates.add ( cBuilder.equal(categoriaPath, filtro.getEstadioId() ) );
        }

        return predicates.toArray(new Predicate[ predicates.size() ] );
    }

    private void adicionaRestricoesDePaginacao(TypedQuery<Estadio> query, Pageable pageable) {
        Integer paginaAtual = pageable.getPageNumber();
        Integer totalObjetosPorPagina = pageable.getPageSize();
        Integer primeiroObjetoDaPagina = paginaAtual * totalObjetosPorPagina;

        // 0 a n-1, n - (2n -1), ...
        query.setFirstResult(primeiroObjetoDaPagina );
        query.setMaxResults(totalObjetosPorPagina );

    }

    private Long total(EstadioFiltro filtro) {
        CriteriaBuilder cBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cQuery = cBuilder.createQuery(Long.class);

        Root<Estadio> rootEstadio = cQuery.from(Estadio.class);

        Predicate[] predicates = criaRestricoes(filtro, cBuilder, rootEstadio);

        cQuery.where(predicates );
        cQuery.select( cBuilder.count(rootEstadio) );

        return manager.createQuery(cQuery).getSingleResult();
    }
}