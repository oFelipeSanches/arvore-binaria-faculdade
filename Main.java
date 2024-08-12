package org.example;

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        arvoreBinaria.inserir(5);
        arvoreBinaria.inserir(8);
        arvoreBinaria.inserir(3);
        arvoreBinaria.inserir(2);
        arvoreBinaria.inserir(18);
        arvoreBinaria.inserir(6);
        arvoreBinaria.inserir(7);
        arvoreBinaria.inserir(19);

        System.out.println("Pré-ordem: ");
        arvoreBinaria.preOrdem(arvoreBinaria.getRaiz());
        System.out.println("Em ordem: ");
        arvoreBinaria.emOrdem(arvoreBinaria.getRaiz());
        System.out.println("Pós ordem: ");
        arvoreBinaria.posOrdem(arvoreBinaria.getRaiz());
        System.out.println("Pré-ordem: ");
        arvoreBinaria.preOrdem(arvoreBinaria.getRaiz());
        arvoreBinaria.removerNo(8);
        System.out.println("Pré-ordem: ");
        arvoreBinaria.preOrdem(arvoreBinaria.getRaiz());


    }
}