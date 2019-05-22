CREATE TABLE IF NOT EXISTS estadio (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  endereco VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO estadio (nome, endereco) VALUES('Anfield Road', 'Anfield Rd, Liverpool L4 0TH, Reino Unido');
INSERT INTO estadio (nome, endereco) VALUES('Etihad Staduim', 'Ashton New Rd, Manchester M11 3FF, Reino Unido');
INSERT INTO estadio (nome, endereco) VALUES('Stamford Bridge', 'Fulham Rd, Fulham, London SW6 1HS, Reino Unido');