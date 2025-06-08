# AtividadeHackaton

# TechNova - API de Produtos

API REST para gerenciamento de produtos, incluindo cadastro com múltiplas imagens.

---

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Hibernate / JPA
- MySQL
- Jackson (para JSON)
- Postman (testes)

---

## Configuração do Banco de Dados

Banco MySQL com tabela `produtos`:

```sql
CREATE TABLE produtos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  texto_descritivo VARCHAR(250) NOT NULL,
  cor VARCHAR(50) NOT NULL,
  fabricante VARCHAR(100) NOT NULL,
  preco DECIMAL(10, 2) NOT NULL,
  quantidade INT NOT NULL
);

CREATE TABLE imagens_produto (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  url_imagem VARCHAR(255),
  produto_id BIGINT,
  CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);
