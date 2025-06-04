// URL do endpoint que você vai acessar
const url = 'https://seu-endpoint-java.com/api/produtos';  // Altere para o URL correto do seu endpoint

// Função para fazer a requisição e tratar a resposta
async function obterProduto() {
    try {
        // Fazendo a requisição GET ao endpoint
        const response = await fetch(url, {
            method: 'GET',  // Pode ser 'POST' se for o caso
            headers: {
                'Content-Type': 'application/json',
                // Se for necessário algum header de autenticação, por exemplo:
                // 'Authorization': 'Bearer seu_token_aqui',
            }
        });

        // Verificar se a requisição foi bem-sucedida (status 200)
        if (!response.ok) {
            throw new Error('Erro na requisição: ' + response.status);
        }

        // Recebe os dados em JSON
        const data = await response.json();
        console.log(data);  // Exibe os dados no console para você verificar
        
        // Chamar uma função para mostrar os dados na página
        exibirProduto(data);
    } catch (error) {
        console.error('Erro ao buscar dados:', error);
    }
}

// Função para exibir os dados na tela
function exibirProduto(produto) {
    const produtoContainer = document.getElementById('produto-container');

    // Criar um HTML dinâmico com os dados
    const produtoHTML = `
        <h2>${produto.nome}</h2>
        <p><strong>Descrição:</strong> ${produto.textoDescritivo}</p>
        <p><strong>Cor:</strong> ${produto.cor}</p>
        <p><strong>Fabricante:</strong> ${produto.fabricante}</p>
        <p><strong>Preço:</strong> R$ ${produto.preco.toFixed(2)}</p>
        <p><strong>Quantidade:</strong> ${produto.quantidade}</p>
        <div class="imagens">
            ${produto.imagens.map(img => `<img src="${img}" alt="Imagem do produto" width="150" />`).join('')}
        </div>
    `;

    // Inserir o HTML dinâmico dentro de um container
    produtoContainer.innerHTML = produtoHTML;
}

// Chamar a função para obter os dados do produto quando a página carregar
document.addEventListener('DOMContentLoaded', obterProduto);
