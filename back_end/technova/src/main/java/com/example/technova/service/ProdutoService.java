package com.example.technova.service;

import com.example.technova.dto.ProdutoDto;
import com.example.technova.entity.ImagemProduto;
import com.example.technova.entity.Produto;
import com.example.technova.repository.ImagemProdutoRepository;
import com.example.technova.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    //consulta de produtos
    public List<Produto> getAllProduto(){
        return produtoRepository.findAll();
    }

    public Optional<ProdutoDto> getById(Long idProduto){
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        if(produtoOptional.isPresent()){
            ProdutoDto produtoDto = new ProdutoDto();
            return Optional.of(produtoDto.fromProduto(produtoOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    //cadastro de produtos
    public ProdutoDto createProduto(ProdutoDto produtoDto){
        Produto produto = produtoDto.toProduto();
        produto = produtoRepository.save(produto);
        return produtoDto.fromProduto(produto);
    }

    //atualização de estoque
    public Optional<ProdutoDto> updateProduto(Long idProduto, ProdutoDto produtoDto){
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        if (produtoOptional.isPresent()){
            Produto produto = produtoOptional.get();
            produto.setQuantidade(produtoDto.getQuantidade());

            produto = produtoRepository.save(produto);

            return Optional.of(produtoDto.fromProduto(produto));
        } else {
            return Optional.empty();
        }
    }

    //remoção de produto
    public boolean delete(Long idProduto){
        if (produtoRepository.existsById(idProduto)){
            produtoRepository.deleteById(idProduto);
            return true;
        } else {
            return false;
        }
    }


    // gerenciar imagens do produto
    public Optional<ProdutoDto> adicionarImagem(Long idProduto, String url_imagem) {
        return produtoRepository.findById(idProduto)
                .map(produto -> {
                    ImagemProduto novaImagem = new ImagemProduto();
                    novaImagem.setUrl_imagem(url_imagem);
                    novaImagem.setProduto(produto);

                    imagemProdutoRepository.save(novaImagem);
                    return new ProdutoDto().fromProduto(produto);
                });
    }

    public Optional<Boolean> removerImagem(Long idProduto, String url_imagem) {
        return produtoRepository.findById(idProduto)
                .map(produto -> {
                    boolean removido = produto.getImagens().remove(url_imagem);
                    if (removido) {
                        produtoRepository.save(produto);
                    }
                    return removido;
                });
    }

}
