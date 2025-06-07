package com.example.technova.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "texto_descritivo", nullable = false)
    private String textoDescritivo;
    private String cor;
    private String fabricante;
    private double preco;
    private int quantidade;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ImagemProduto> imagens = new ArrayList<>();


    public void adicionarImagem(ImagemProduto imagem) {
        imagem.setProduto(this);
        this.imagens.add(imagem);
    }


    public Produto() {
    }

    public Produto(Long id, String nome, String textoDescritivo, String cor, String fabricante, double preco, int quantidade, List<ImagemProduto> imagens) {
        this.id = id;
        this.nome = nome;
        this.textoDescritivo = textoDescritivo;
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
