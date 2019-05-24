CREATE TABLE IF NOT EXISTS partida (
  id INT NOT NULL AUTO_INCREMENT,
  data DATE,
  pontuacao_mandante INT NOT NULL,
  pontuacao_visitante INT NOT NULL,
  time_mandante_id INT NOT NULL,
  time_visitante_id INT NOT NULL,
  campeonato_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_time_mandante_partida FOREIGN KEY (time_mandante_id) REFERENCES time (id),
  CONSTRAINT fk_time_visitante_partida FOREIGN KEY (time_visitante_id) REFERENCES time (id),
  CONSTRAINT fk_campeonato_partida FOREIGN KEY (campeonato_id) REFERENCES campeonato (id)
)engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO partida (data, pontuacao_mandante, pontuacao_visitante, time_mandante_id, time_visitante_id, campeonato_id) VALUES ('2019-04-26',1,1,3,1,1);
INSERT INTO partida (data, pontuacao_mandante, pontuacao_visitante, time_mandante_id, time_visitante_id, campeonato_id) VALUES ('2019-01-01',3,0,2,3,1);
INSERT INTO partida (data, pontuacao_mandante, pontuacao_visitante, time_mandante_id, time_visitante_id, campeonato_id) VALUES ('2019-03-30',3,0,1,2,1);