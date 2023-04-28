CREATE TABLE adocao (
  id UUID PRIMARY KEY,
  animal_id BIGINT NOT NULL,
  tutor_id BIGINT NOT NULL,
  data DATE,
  CONSTRAINT fk_adocao_animal FOREIGN KEY (animal_id) REFERENCES pets (id),
  CONSTRAINT fk_adocao_tutor FOREIGN KEY (tutor_id) REFERENCES tutores (id)
);

