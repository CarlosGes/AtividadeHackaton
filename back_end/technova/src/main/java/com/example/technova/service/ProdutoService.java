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

    public Optional<ProdutoDto> getById(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()){
            ProdutoDto produtoDto = new ProdutoDto();
            return Optional.of(produtoDto.fromProduto(produtoOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    //cadastro de produtos
    public ProdutoDto createProduto(ProdutoDto produtoDto){
        System.out.println("DTO.textoDescritivo = " + produtoDto.getTextoDescritivo());

        Produto produto = produtoDto.toProduto();

        System.out.println("Produto.textoDescritivo = " + produto.getTextoDescritivo());

        for (ImagemProduto imagem : produto.getImagens()){
            imagem.setProduto(produto);
        }
        produto = produtoRepository.save(produto);
        return produtoDto.fromProduto(produto);
    }

    //atualização de estoque
    public Optional<ProdutoDto> updateProduto(Long id, ProdutoDto produtoDto){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
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
    public boolean delete(Long id){
        if (produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    // gerenciar imagens do produto
    public Optional<ProdutoDto> adicionarImagem(Long id, String url_imagem) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    ImagemProduto novaImagem = new ImagemProduto();
                    novaImagem.setUrl_imagem(url_imagem);
                    novaImagem.setProduto(produto);

                    imagemProdutoRepository.save(novaImagem);
                    return new ProdutoDto().fromProduto(produto);
                });
    }

    public Optional<Boolean> removerImagem(Long id, String url_imagem) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    boolean removido = produto.getImagens().remove(url_imagem);
                    if (removido) {
                        produtoRepository.save(produto);
                    }
                    return removido;
                });
    }

}
