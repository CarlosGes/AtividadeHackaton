const API_URL = "http://localhost:8080/produto"; // Altere para sua URL
        let allProducts = [];
        
        // Carrega produtos ao iniciar
        document.addEventListener("DOMContentLoaded", async () => {
            try {
                const response = await fetch(API_URL);
                if (!response.ok) throw new Error("Erro ao carregar produtos");
                
                allProducts = await response.json();
                displayProducts(allProducts);
                document.getElementById("loading").style.display = "none";
            } catch (error) {
                document.getElementById("loading").style.display = "none";
                document.getElementById("error").style.display = "block";
                document.getElementById("error").innerText = error.message;
            }
        });
        
        // Exibe produtos na tela
        // Substitua a função displayProducts por esta versão corrigida
function displayProducts(products) {
    const grid = document.getElementById("productGrid");
    grid.innerHTML = "";
    
    document.getElementById("productCount").textContent = products.length;
    
    if (products.length === 0) {
        grid.innerHTML = '<div class="error">Nenhum produto encontrado</div>';
        return;
    }
    
    products.forEach(product => {
        const card = document.createElement("div");
        card.className = "product-card";
        
        // Corrigido: usando url_imagem em vez de url
        const imageUrl = product.imagens && product.imagens.length > 0 
            ? product.imagens[0].url_imagem  // <-- Aqui está a correção
            : "https://via.placeholder.com/300x200?text=Sem+Imagem";
        
        card.innerHTML = `
            <div class="product-image" style="background-image: url('${imageUrl}')"></div>
            <div class="product-info">
                <h3 class="product-title">${product.nome}</h3>
                <p class="product-description">${product.textoDescritivo || product.texto_descritivo || 'Sem descrição'}</p>
                
                <div class="product-meta">
                    <div class="product-price">R$ ${product.preco.toFixed(2)}</div>
                    <div class="product-stock">${product.quantidade} em estoque</div>
                </div>
                
                <div class="product-details">
                    <div><strong>Cor:</strong> ${product.cor || 'Não informada'}</div>
                    <div><strong>Fabricante:</strong> ${product.fabricante || 'Não informado'}</div>
                </div>
                
                <div class="product-actions">
                    <button class="btn-edit" onclick="editProduct(${product.id})">Comprar</button>
                </div>
            </div>
        `;
        
        grid.appendChild(card);
    });
}
        
        // Filtra produtos por termo de busca
        function filterProducts() {
            const searchTerm = document.getElementById("searchInput").value.toLowerCase();
            const filtered = allProducts.filter(product => 
                product.nome.toLowerCase().includes(searchTerm) ||
                product.texto_descritivo.toLowerCase().includes(searchTerm) ||
                product.fabricante?.toLowerCase().includes(searchTerm)
            );
            displayProducts(filtered);
        }
        
        // Funções para editar/excluir (pode implementar depois)
        function editProduct(nome) {
            alert(`Produto comprado: ${nome}`);
            // window.location.href = `editar.html?nome=${nome}`;
        }
        