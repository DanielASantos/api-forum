INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '123456');

INSERT INTO CATEGORIA(nome) VALUES ('Back-End');
INSERT INTO CATEGORIA(nome) VALUES ('Front-End');
INSERT INTO CATEGORIA(nome) VALUES ('DevOps');

INSERT INTO CURSO(nome, categoria_id) VALUES('Spring Boot', 1);
INSERT INTO CURSO(nome, categoria_id) VALUES('HTML 5', 2);

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);