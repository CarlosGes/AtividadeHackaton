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

-- Inserir dados na tabela 'produtos'
INSERT INTO produtos (nome, textoDescritivo, cor, fabricante, preco, quantidade)
VALUES 
    ('Fone com Microfone', 'Fone de Ouvido com Microfone, Loop Way, P3', 'Branco', 'Philips', 39.99, 27),
    ('Mouse Gamer', 'Mouse Gamer Óptico Nitro 7200 DPI, 6 Botões, USB', 'Preto', 'Acer', 79.99, 18),
    ('HeadSet', 'HeadSet Gamer com Microfone Quantum II, P3', 'Preto', 'JBL', 119.99, 19),
    ('Teclado Gamer', 'Teclado Mecânico Gamer Phantom, ABNT2', 'Preto', 'Fortrek', 99.99, 12),
    ('Fone Bluetooth', 'Fone de Ouvido Tune Buds, Bluetooth', 'Preto', 'JBL', 389.90, 3),
    ('Teclado Slim', 'Teclado Membrana Fortrek K15, ABNT2, USB', 'Preto', 'Fortrek', 89.99, 11),
    ('Mouse HX', 'Mouse Pulsefire Haste 2 Mini, 26000dpi, 6 Botões, Wireless', 'Preto', 'Hyperx', 379.99, 5),
    ('Fone Wireless', 'Fone de Ouvido Tune Flex, Wireless', 'Preto', 'JBL', 439.99, 14),
    ('Teclado Slim', 'Teclado Membrana Fortrek K15, ABNT2, USB', 'Branco', 'Fortrek', 105.99, 8),
    ('Mouse King', 'Mouse King Pro Horda World of Warcraft, 26000dpi, 5 Botões', 'Vermelho', 'Redragon', 329.99, 6),
    ('Fone', 'Fone de Ouvido Tune 500 Pure Bass, P2', 'Branco', 'JBL', 139.99, 12),
    ('Mouse King', 'Mouse King Pro Horda World of Warcraft, 26000dpi, 5 Botões', 'Azul', 'Redragon', 319.99, 8);

-- Inserir dados na tabela 'imagens_produto'
INSERT INTO imagens_produto (produto_id, url_imagem)
VALUES
    (1, 'https://i.ibb.co/zhRPrqQS/01.jpg'),
    (1, 'https://i.ibb.co/Vp0B2h2m/02.jpg'),
    (1, 'https://i.ibb.co/nqXtTqG4/03.jpg'),
    (2, 'https://i.ibb.co/60gSPfDP/01.jpg'),
    (2, 'https://i.ibb.co/prfqF9fM/02.jpg'),
    (2, 'https://i.ibb.co/tTx9zdtd/03.jpg'),
    (3, 'https://i.ibb.co/xqz36nXW/01.jpg'),
    (3, 'https://i.ibb.co/k6zsq6VW/03.jpg'),
    (3, 'https://i.ibb.co/wNBdSfg5/02.jpg'),
    (4, 'https://i.ibb.co/9zDbk1w/01.jpg'),
    (4, 'https://i.ibb.co/0j0gTT5H/02.jpg'),
    (4, 'https://i.ibb.co/DHCSqKsd/03.jpg'),
    (5, 'https://i.ibb.co/pB2hLd9R/03.jpg'),
    (5, 'https://i.ibb.co/3mSRMSgd/02.jpg'),
    (5, 'https://i.ibb.co/ch0hNMsv/04.jpg'),
    (6, 'https://i.ibb.co/G4BR1Sds/03.jpg'),
    (6, 'https://i.ibb.co/wZr6MgWB/02.jpg'),
    (6, 'https://i.ibb.co/tTYkZ85S/01.jpg'),
    (7, 'https://i.ibb.co/Zp3rKKBQ/01.jpg'),
    (7, 'https://i.ibb.co/fzhyhJfc/02.jpg'),
    (7, 'https://i.ibb.co/nskSxbrP/03.jpg'),
    (8, 'https://i.ibb.co/JjM8Kn9B/02.jpg'),
    (8, 'https://i.ibb.co/XrWMYMZj/03.jpg'),
    (8, 'https://i.ibb.co/ksHzyn0Q/01.jpg'),
    (9, 'https://i.ibb.co/RGDt1tQG/03.jpg'),
    (9, 'https://i.ibb.co/wrsf52qB/02.jpg'),
    (9, 'https://i.ibb.co/XfcFtrCH/01.jpg'),
    (10, 'https://i.ibb.co/NnGDKpRD/04.jpg'),
    (10, 'https://i.ibb.co/8DJncPDX/02.jpg'),
    (10, 'https://i.ibb.co/wN0tr7sk/01.jpg'),
    (11, 'https://i.ibb.co/LdwJHxtZ/01.jpg'),
    (11, 'https://i.ibb.co/GQ4T0xTd/03.jpg'),
    (11, 'https://i.ibb.co/gFXrgBLk/02.jpg'),
    (12, 'https://i.ibb.co/R47938fc/01.jpg'),
    (12, 'https://i.ibb.co/fV3CvpfR/02.jpg'),
    (12, 'https://i.ibb.co/ccdSz1n9/03.jpg');
