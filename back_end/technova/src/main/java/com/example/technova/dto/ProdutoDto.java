package com.example.technova.dto;

import com.example.technova.entity.ImagemProduto;
import com.example.technova.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDto {
    private Long idProduto;
    private String nome;
    private String textoDescritivo;
    private String cor;
    private String fabricante;
    private double preco;
    private int quantidade;
    private List<ImagemProduto> imagens = new ArrayList<>();


    public Produto toProduto(){
        return new Produto(
                this.idProduto,
                this.nome,
                this.textoDescritivo,
                this.cor,
                this.fabricante,
                this.preco,
                this.quantidade,
                this.imagens
        );
    }

    public ProdutoDto fromProduto(Produto produto){
        return new ProdutoDto(
            produto.getIdProduto(),
                produto.getNome(),
                produto.getTextoDescritivo(),
                produto.getCor(),
                produto.getFabricante(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getImagens()
        );
    }

    public ProdutoDto() {
    }

    public ProdutoDto(Long idProduto, String nome, String textoDescritivo, String cor, String fabricante, double preco, int quantidade, List<ImagemProduto> imagens) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.textoDescritivo = textoDescritivo;
        this.cor = cor;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagens = imagens;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTextoDescritivo() {
        return textoDescritivo;
    }

    public void setTextoDescritivo(String textoDescritivo) {
        this.textoDescritivo = textoDescritivo;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<ImagemProduto> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemProduto> imagens) {
        this.imagens = imagens;
    }
}
