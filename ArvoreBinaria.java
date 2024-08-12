package org.example;

public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int valor) {

        if (valor < 0) {
            System.out.println("Valor inválido. Apenas números positivos são permitidos.");
            return;
        }

        No novoNo = new No(valor);
        if(this.raiz == null) {
            this.raiz = novoNo;
        } else {
            No atual = this.raiz;
            No pai = null;
            boolean esquerda = false;
            while(atual != null) {
                if(novoNo.getValor() < atual.getValor()) {
                    pai = atual;
                    atual = atual.getEsq();
                    esquerda = true;
                } else {
                    pai = atual;
                    atual = atual.getDir();
                    esquerda = false;
                }
            }
            if(esquerda) {
                pai.setEsq(novoNo);
            } else {
                pai.setDir(novoNo);
            }
        }
    }

    public boolean removerNoFolha(int valor) {

        No atual = this.raiz;
        No pai = null;
        boolean esquerda = false;

        while (atual != null) {
            if(valor == atual.getValor()) { // se o valor inserido for igual ao valor buscado
                if (atual.getEsq() == null && atual.getDir() == null) { // se não tem filhos na esquerda, nem na direita
                    // logo, sendo um nó folha
                    if(pai == null) {
                        this.raiz = null; // caso o nó folha seja a raiz, a raiz recebe nulo
                    } else if (esquerda) {
                        pai.setEsq(null); // seta o atual como nulo, pelo apontamento do pai
                    } else {
                        pai.setDir(null); // seta o atual como nulo, pelo apontamento do pai
                    }

                    System.out.println("\nNó folha com valor " + valor + " removido com sucesso!");
                    return true;
                }
                else {
                    System.out.println("\nNó encontrado com valor " + valor + " não é uma folha e não pode ser removido.");
                    return false; // nó encontrado não é uma folha
                }
            }

            pai = atual; // mantém a referência ao nó pai antes de mover o atual
            if(valor < atual.getValor()) {
                atual = atual.getEsq(); // muda o atual pro próximo nó na árvore com base no valor comparado
                esquerda = true; // define se o próximo nó a ser visitado é à esquerda ou à direita do nó atual.
            } else {
                atual = atual.getDir();
                esquerda = false;
            }
        }

        System.out.println("\nO valor " + valor + " não foi encontrado na árvore.");
        return false;
    }



    public boolean removerNoRaiz() {
        if(this.raiz == null) {
            System.out.println("A árvore está vazia.");
            return false;
        }

        if (this.raiz.getEsq() == null && this.raiz.getDir() == null) {  // se a raiz não tem filhos, recebe null
            this.raiz = null;
        }

        else if (this.raiz.getEsq() == null) {  // se a raiz tem um filho a direita, a raiz é substituida pelo filho à direita
            this.raiz = this.raiz.getDir();
        }
        else if (this.raiz.getDir() == null) {  // se a raiz tem um filho a esquerda, a raiz é substituida pelo filho à esquerda
            this.raiz = this.raiz.getEsq();
        } else {  // raiz com 2 filhos
            if (this.raiz.getEsq() != null && this.raiz.getDir() != null) {
                No atual = this.raiz;
                No sucessor = encontrarMenorNo(atual.getDir());
                int valorSucessor = sucessor.getValor();
                removerNo(sucessor.getValor());
                atual.setValor(valorSucessor);
            }
        }

        System.out.println("\nRaiz removida com sucesso!");
        return true;

    }

    public boolean removerNo(int valor) {
        No atual = this.raiz;
        No anterior = null;

        while (atual != null) {
            if (atual.getValor() == valor) {
                break;
            } else if (valor < atual.getValor()) {
                anterior = atual;
                atual = atual.getEsq();
            } else {
                anterior = atual;
                atual = atual.getDir();
            }
        }

        if (atual == null) {
            return false; // valor não encontrado
        }

        // nó não tem filhos (folha)
        if (atual.getEsq() == null && atual.getDir() == null) {
            removerNoFolha(atual.getValor());
        }

        // nó tem um filho à direita
        else if (atual.getEsq() == null) {
            removerNoComUmFilho(atual.getValor());
            System.out.println("\nNó com valor " + valor + " removido com sucesso!");
        }

        // nó tem um filho à esquerda
        else if (atual.getDir() == null) {
            removerNoComUmFilho(atual.getValor());
            System.out.println("\nNó com valor " + valor + " removido com sucesso!");
        }

        // nó tem dois filhos
        else {
            No sucessor = encontrarMenorNo(atual.getDir());
            int valorSucessor = sucessor.getValor();
            removerNo(sucessor.getValor());
            atual.setValor(valorSucessor);
            System.out.println("\nNó com valor " + valor + " substituído por " + valorSucessor + " com sucesso!");
        }

        return true;
    }

    public boolean removerNoComUmFilho(int valor) {
        No atual = this.raiz;
        No anterior = null;

        while (atual != null && atual.getValor() != valor) {  // busca o nó para remover desde a raiz
            anterior = atual;
            if (valor < atual.getValor()) {  // se o valor for menor que o nó atual, vai para esquerda
                atual = atual.getEsq();
            } else {  // se não para direita
                atual = atual.getDir();
            }
        }

        if (atual == null) {
            System.out.println("\nO valor " + valor + " não foi encontrado na árvore.");
            return false;
        }

        if (atual.getEsq() != null && atual.getDir() != null) {
            System.out.println("\nO nó tem dois filhos, não é possível ser removido!");
            return false; // o nó tem dois filhos
        }

        if (atual.getEsq() == null) {  // esquerda é igual a nulo, logo tendo apenas 1 filho a direita
            if (anterior == null) {  // nó atual é a raiz da árvore
                this.raiz = atual.getDir();  // remove a raiz, deixando o filho como no seu lugar
            } else if (anterior.getEsq() == atual) {  // se o nó atual era o flho esquerdo do anterior
                anterior.setEsq(atual.getDir());  // remove o nó atual apontando pra esquerda do anterior
            } else {
                anterior.setDir(atual.getDir());  // ponteiro direito do nó anterior é atualizado para apontar para o filho direito do nó atual.
            }
        }

        else if (atual.getDir() == null) {  // direita é igual a nulo, logo tendo apenas 1 filho a esquerda
            if (anterior == null) {
                this.raiz = atual.getEsq(); // remove a raiz, deixando o filho como no seu lugar
            } else if (anterior.getEsq() == atual) {
                anterior.setEsq(atual.getEsq());
            } else {
                anterior.setDir(atual.getEsq());
            }
        }

        return true;
    }

//    public boolean removerNoComDoisFilhos(int valor) {  // tentativa falha de remover dois nós
//
//        if (this.raiz.getEsq() != null && this.raiz.getDir() != null) {
//            No atual = this.raiz;
//            No sucessor = encontrarMenorNo(atual.getDir());
//            int valorSucessor = sucessor.getValor();
//            removerNo(sucessor.getValor());
//            atual.setValor(valorSucessor);
//        }
//
//        System.out.println("\nNó " + valor + " substituido com sucesso!");
//        return true;
//    }

    private No encontrarMenorNo(No no) {
        while (no.getEsq() != null) {
            no = no.getEsq();
        }
        return no;
    }

    public No getRaiz() {
        return this.raiz;
    }

    public void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.println(no.getValor());
        preOrdem(no.getEsq());
        preOrdem(no.getDir());
    }

    public void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsq());
        System.out.println(no.getValor());
        emOrdem(no.getDir());
    }

    public void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsq());
        posOrdem(no.getDir());
        System.out.println(no.getValor());
    }

}