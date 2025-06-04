package com.example.technova.dto;

import com.example.technova.entity.ImagemProduto;
import com.example.technova.entity.Produto;

public class ImagemProdutoDto {
    private Long id;
    private String url_imagem;
    private Produto produto;

    public ImagemProduto toImagemProduto(){
        return new ImagemProduto(
                this.id,
                this.url_imagem,
                this.produto
        );
    }

    public ImagemProdutoDto fromImagemProduto(ImagemProduto imagemProduto){
        return new ImagemProdutoDto(
                imagemProduto.getId(),
                imagemProduto.getUrl_imagem(),
                imagemProduto.getProduto()
        );
    }

    public ImagemProdutoDto() {
    }

    public ImagemProdutoDto(Long id, String url_imagem, Produto produto) {
        this.id = id;
        this.url_imagem = url_imagem;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
