package com.example.technova.dto;

import com.example.technova.entity.ImagemProduto;
import com.example.technova.entity.Produto;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {
    private Long id;
    private String nome;
    private String textoDescritivo;
    private String cor;
    private String fabricante;
    private double preco;
    private int quantidade;
    private List<ImagemProdutoDto> imagens;


    public Produto toProduto(){
        Produto produto = new Produto();

        produto.setNome(this.nome);
        produto.setTextoDescritivo(this.textoDescritivo);
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

    public ProdutoDto(Long id, String nome, String textoDescritivo, String cor, String fabricante, double preco, int quantidade, List<ImagemProdutoDto> imagens) {
        this.id = id;
        this.nome = nome;
        this.textoDescritivo = textoDescritivo;
        this.cor = cor;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagens = imagens;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
