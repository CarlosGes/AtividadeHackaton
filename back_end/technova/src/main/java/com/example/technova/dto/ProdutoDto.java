package com.example.technova.dto;

import com.example.technova.entity.ImagemProduto;
import com.example.technova.entity.Produto;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {
    private Long id;
    private String nome;
    private String texto_descritivo;
    private String cor;
    private String fabricante;
    private BigDecimal preco;
    private int quantidade;
    private List<ImagemProdutoDto> imagens;


    public Produto toProduto(){
        Produto produto = new Produto();

        produto.setNome(this.nome);
        produto.setTextoDescritivo(this.texto_descritivo);
        produto.setCor(this.cor);
        produto.setFabricante(this.fabricante);
        produto.setPreco(this.preco);
        produto.setQuantidade(this.quantidade);

        if (this.imagens != null) {
            List<ImagemProduto> imagensList = new ArrayList<>();
            for (ImagemProdutoDto imagemDto : this.imagens) {
                ImagemProduto imagem = new ImagemProduto();
                imagem.setUrl_imagem(imagemDto.getUrl_imagem());
                imagem.setProduto(produto); // <-- ESSENCIAL
                imagensList.add(imagem);
            }
            produto.setImagens(imagensList);
        }

        return produto;
    }

    public static ProdutoDto fromProduto(Produto produto) {
        List<ImagemProdutoDto> imagensDto = produto.getImagens().stream()
                .map(imagem -> new ImagemProdutoDto(imagem.getId(), imagem.getUrl_imagem()))
                .collect(Collectors.toList());

        return new ProdutoDto(
                produto.getId(),
                produto.getNome(),
                produto.getTextoDescritivo(),
                produto.getCor(),
                produto.getFabricante(),
                produto.getPreco(),
                produto.getQuantidade(),
                imagensDto
        );
    }


    public ProdutoDto() {}

    public ProdutoDto(Long id, String nome, String texto_descritivo, String cor, String fabricante, BigDecimal preco, int quantidade, List<ImagemProdutoDto> imagens) {
        this.id = id;
        this.nome = nome;
        this.texto_descritivo = texto_descritivo;
        this.cor = cor;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagens = imagens;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTextoDescritivo() {
        return texto_descritivo;
    }

    public void setTextoDescritivo(String texto_descritivo) {
        this.texto_descritivo = texto_descritivo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<ImagemProdutoDto> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemProdutoDto> imagens) {
        this.imagens = imagens;
    }
}
