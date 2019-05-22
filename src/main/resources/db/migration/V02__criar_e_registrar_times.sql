CREATE TABLE IF NOT EXISTS time (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  estadio_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_estadio_time FOREIGN KEY (estadio_id) REFERENCES estadio (id)
)engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO time (nome, estadio_id) VALUES ('Liverpool', 1);
INSERT INTO time (nome, estadio_id) VALUES ('Manchester City', 2);
INSERT INTO time (nome, estadio_id) VALUES ('Chelsea', 3);

