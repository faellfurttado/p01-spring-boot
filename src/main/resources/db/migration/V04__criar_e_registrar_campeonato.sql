CREATE TABLE IF NOT EXISTS campeonato (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  ano INT NOT NULL,
  PRIMARY KEY (id)
)engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO campeonato (nome, ano) VALUES ('Premier League', 2019);