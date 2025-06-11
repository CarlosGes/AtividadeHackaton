const API_URL = "http://localhost:8080/produto";
let allProducts = [];

// ⏳ Inicialização
document.addEventListener("DOMContentLoaded", async () => {
    await fetchProdutos();
    document.getElementById("loading").style.display = "none";
});

// 🔄 Buscar todos os produtos
async function fetchProdutos() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error("Erro ao carregar produtos");
        allProducts = await response.json();
        displayProducts(allProducts);
    } catch (error) {
        mostrarErro(error.message);
    }
}

// 🖼️ Exibir lista de produtos
function displayProducts(products) {
    const tbody = document.querySelector("#produtosTable tbody");
    tbody.innerHTML = "";

    products.forEach(product => {
        const temImagens = product.imagens?.length > 0;
        const primeiraImagem = temImagens
            ? `<img src="${product.imagens[0].url_imagem}" width="50" height="50" style="object-fit: cover;">`
            : "Sem imagem";

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${product.id}</td>
            <td>
                <div style="display: flex; align-items: center; gap: 10px;">
                    ${primeiraImagem}
                    ${product.nome}
                </div>
            </td>
            <td>R$ ${product.preco.toFixed(2)}</td>
            <td>${product.quantidade}</td>
            <td>
                <div class="table-actions">
                    <button class="btn-primary" onclick="editarProduto(${product.id})">Editar</button>
                    <button class="btn-danger" onclick="deletarProduto(${product.id})">Excluir</button>
                </div>
            </td>
        `;
        tbody.appendChild(row);
    });
}

// ✍️ Editar produto
async function editarProduto(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) throw new Error("Produto não encontrado");

        const produto = await response.json();
        preencherFormulario(produto);
        window.scrollTo({ top: 0, behavior: "smooth" });
    } catch (error) {
        mostrarErro(error.message);
    }
}

// 🧾 Preencher formulário com dados
function preencherFormulario(produto) {
    document.getElementById("produtoId").value = produto.id || "";
    document.getElementById("nome").value = produto.nome || "";
    document.getElementById("texto_descritivo").value = produto.textoDescritivo || produto.texto_descritivo || "";
    document.getElementById("cor").value = produto.cor || "";
    document.getElementById("fabricante").value = produto.fabricante || "";
    document.getElementById("preco").value = produto.preco || "";
    document.getElementById("quantidade").value = produto.quantidade || "";

    const container = document.getElementById("imagens-container");
    container.innerHTML = "";
    produto.imagens?.forEach(imagem => adicionarCampoImagem(imagem.url_imagem));
}

// 🗑️ Excluir produto
async function deletarProduto(id) {
    if (!confirm("Tem certeza que deseja excluir este produto?")) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        if (!response.ok) throw new Error("Erro ao excluir produto");

        alert("Produto excluído com sucesso!");
        await fetchProdutos();
    } catch (error) {
        mostrarErro(error.message);
    }
}

// 💾 Salvar (criar ou atualizar) produto
async function salvarProduto() {
    const id = document.getElementById("produtoId").value;
    const nome = document.getElementById("nome").value;
    const textoDescritivo = document.getElementById("texto_descritivo").value;
    const preco = document.getElementById("preco").value;
    const quantidade = document.getElementById("quantidade").value;

    if (!nome || !textoDescritivo || !preco || !quantidade) {
        alert("Por favor, preencha todos os campos obrigatórios.");
        return;
    }

    // Monta objeto produto com base no formato esperado
    const produto = {
        id: id ? parseInt(id) : null,
        nome,
        cor: document.getElementById("cor").value,
        fabricante: document.getElementById("fabricante").value,
        preco: parseFloat(preco),
        quantidade: parseInt(quantidade),
        textoDescritivo,
        imagens: Array.from(document.querySelectorAll(".imagem-url"))
            .filter(input => input.value.trim() !== "")
            .map((input, index) => {
                const imgIdAttr = input.getAttribute("data-id");
                return {
                    id: imgIdAttr ? parseInt(imgIdAttr) : null,
                    url_imagem: input.value.trim()
                };
            })
    };

    try {
        const metodo = id ? "PUT" : "POST";
        const url = id ? `${API_URL}/${id}` : `${API_URL}`;

        const response = await fetch(url, {
            method: metodo,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(produto)
        });

        if (!response.ok) {
            const erro = await response.text();
            throw new Error(`Erro ${response.status}: ${erro}`);
        }

        const resultado = await response.json();
        alert(`Produto ${id ? "atualizado" : "cadastrado"} com sucesso!`);
        limparFormulario();
        fetchProdutos();
    } catch (error) {
        console.error("Erro ao salvar produto:", error);
        alert(`Erro ao salvar produto: ${error.message}`);
    }
}


// ➕ Adicionar campo de imagem
function adicionarCampoImagem(url = "") {
    const container = document.getElementById("imagens-container");
    const div = document.createElement("div");
    div.className = "imagem-item";
    div.innerHTML = `
        <input type="text" placeholder="URL da Imagem" class="imagem-url" value="${url}" />
        <button type="button" class="btn-remover" onclick="this.parentElement.remove()">Remover</button>
    `;
    container.appendChild(div);
}

// 🧼 Limpar formulário
function limparFormulario() {
    document.getElementById("produtoForm").reset();
    document.getElementById("produtoId").value = "";
    document.getElementById("imagens-container").innerHTML = "";
}

// ❌ Mostrar erro na tela
function mostrarErro(mensagem) {
    console.error("Erro:", mensagem);
    document.getElementById("error").style.display = "block";
    document.getElementById("error").innerText = mensagem;
}