package dcomp.lpweb.projeto.api.model;

import java.util.Date;

public class Partida {
	
	private Integer id;
	private Date data;
	private Integer pontuacaoMandante;
	private Integer pontuacaoVisitante;
	private Time mandante;
	private Time visitante;
	private Campeonato campeonato;
}