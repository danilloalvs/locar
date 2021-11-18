package locarfx.DAO;

import java.util.ArrayList;

public interface IBasicoDAO<T> {
    void inserir(T objeto);
    void alterar(T objeto);
    void remover(T objeto);
    T buscarPorNome(String nome);
    T buscarPorCodigo(Integer codigo);
    ArrayList<T> retornarTodos();
}