CREATE TABLE pets (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(20) NOT NULL,
  imagem VARCHAR(255),
  idade VARCHAR(25) NOT NULL,
  categoria VARCHAR(20),
  personalidade VARCHAR(50) NOT NULL,
  tamanho VARCHAR(20) NOT NULL,
  adotado BOOLEAN DEFAULT FALSE,
  abrigo_id BIGINT,
  FOREIGN KEY (abrigo_id) REFERENCES abrigos (id) ON DELETE CASCADE
);
