CREATE TABLE IF NOT EXISTS jogador (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  genero VARCHAR(20) NOT NULL,
  altura DECIMAL(10,2) NOT NULL,
  capitao BOOLEAN NOT NULL,
  nascimento DATE,
  time_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_time_jogador FOREIGN KEY (time_id) REFERENCES time (id)
)engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO jogador (nome, genero, altura, capitao, nascimento, time_id) VALUES ('Mohammed Salah', 'Masculino','1.75', false, '1992-06-15', 1);
INSERT INTO jogador (nome, genero, altura, capitao, nascimento, time_id) VALUES ('Roberto Firmino', 'Masculino', '1.81', false, '1991-10-02', 1);
INSERT INTO jogador (nome, genero, altura, capitao, nascimento, time_id) VALUES ('Sadio Mane', 'Masculino', '1.75', false, '1992-04-10', 1);
INSERT INTO jogador (nome, genero, altura, capitao, nascimento, time_id) VALUES ('Virgil van Dijk', 'Masculino', '1.93', false, '1991-07-08', 1);
INSERT INTO jogador (nome, genero, altura, capitao, nascimento, time_id) VALUES ('Jordan Henderson', 'Masculino', '1.82', true, '1990-06-17', 1);


