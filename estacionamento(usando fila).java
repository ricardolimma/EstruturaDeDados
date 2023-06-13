import java.util.Random;
import javax.swing.JOptionPane;
public class UsandoFilaGenerics {
    public static void Main(String[] args) {
        
        Fila<Carro> estacionamento = new Fila<>();
        int op;
        int cont = 0;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog("Digite: \n1 para estacionar \n2 para retirar \n3 para sair "));
            switch (op) {
                case 1:
                    String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente");                    
                    Pessoa p = new Pessoa(++cont, nome);
                    String placa = JOptionPane.showInputDialog(null, "Digite a placa do veículo");
                    Carro carro = new Carro(placa, p);
                    estacionamento.enfileirar(carro);
                    break;
                case 2:
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o Id do proprietário"));
                    while (id != estacionamento.peek().getDono().getId()) {
                        // joga os carros para o final da fila até encontrar o carro do dono
                        estacionamento.enfileirar(estacionamento.desenfileirar());
                    }
                    estacionamento.desenfileirar();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Obrigado, volte sempre");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        } while (op != 3);
        JOptionPane.showMessageDialog(null, estacionamento);
    }
}
class Pessoa {
    private int id;
    private String nome;
    public Pessoa (int id, String nome) {
        this.nome = nome;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + "]";
        return "[nome=" + nome + ", id=" + id + "]";
    }        
}

class Carro {
    private String placa;
    private Pessoa dono;
    public Carro(String placa, Pessoa dono) {
        this.placa = placa;
        this.dono = dono;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public Pessoa getDono() {
        return dono;
    }
    public void setDono(Pessoa dono) {
        this.dono = dono;
    }
    @Override
    public String toString() {
        return "carro [placa=" + placa + ", dono=" + dono + "]";
    }            
}
class Fila <T> {
    private class No<T> {
        private T conteudo;
        private No<T> proximo;
    
        public No(T conteudo) {
            this.conteudo = conteudo;
        }
        public T getConteudo() {
            return conteudo;
        }
        public void setConteudo(T conteudo) {
            this.conteudo = conteudo;
        }
        public No<T> getProximo() {
            return proximo;
        }
        public void setProximo(No<T> proximo) {
            this.proximo = proximo;
        }
        @Override
        public String toString() {
            String str = "";
            str += "Conteudo[" + this.conteudo + "]";
            str += this.conteudo + " ";
            return str;
        }
    }
    private No<T> primeiro;
    private No<T> ultimo;
    private int tamanho;
    public No<T> getPrimeiro() {
        return primeiro;
    }
    public void setPrimeiro(No<T> primeiro) {
        this.primeiro = primeiro;
    }
    public No<T> getUltimo() {
        return ultimo;
    }
    public void setUltimo(No<T> ultimo) {
        this.ultimo = ultimo;
    }
    public int getTamanho() {
        return tamanho;
    }
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    public void enfileirar (T conteudo) {
        No<T> novoNo = new No<T>(conteudo);
        if (estaVazia()) {
            primeiro = novoNo;            
        }
        else {
            ultimo.setProximo(novoNo);
        }
        tamanho++;
        ultimo = novoNo;
    }
    public T desenfileirar() {
        T i = primeiro.getConteudo();
        
        primeiro = primeiro.getProximo();
        tamanho--;
        if (tamanho == 0) {
            ultimo = null;
        }
        return i;
    }
    public T peek() {
        return primeiro.getConteudo();
    }    
    public boolean estaVazia() {
        return primeiro == null;
    }
    @Override
    public String toString() {
        String s = "";
        if (estaVazia()) {
            s += "vazia";
        } else {
            No<T> runner = primeiro;
            while (runner != null) {
                s += runner + " -> ";
                s += runner + " \n ";
                runner = runner.getProximo();
            }
            s += "//";
        }
        return s;
    }    
}
