/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario;

import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class ArbolBinario {
    private Nodo raiz;
    private int num_nodos;
    private int alt = 0;
    
    public ArbolBinario() {
        raiz = null;
        num_nodos = 0;
        alt = 0;
    }
    
    public void insertar(int dato) {
        if (existe(dato)) return;
        Nodo nuevo = new Nodo(dato);
        if (raiz == null)
            raiz = nuevo;
        else
        {
            Nodo anterior = null;
            Nodo temp = raiz;
            while (temp !=  null)
            {
                anterior = temp;
                if (dato < temp.getDato())
                    temp = temp.getIzq();
                else
                    temp = temp.getDer();
            }
            if (dato >= anterior.getDato())
                anterior.setDer(nuevo);
            else
                anterior.setIzq(nuevo);
        }
        num_nodos++;
    }
    
    public Nodo getRaiz() {
        return raiz;
    }
    
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public int getNumNodos() {
        return num_nodos;
    }
    
    public void preorden(Nodo aux, LinkedList recorrido) {
        if (aux != null)
        {
            recorrido.add(aux.getDato());
            preorden(aux.getIzq(), recorrido);
            preorden(aux.getDer(), recorrido);
        }
    }
    
    public void inorden(Nodo aux, LinkedList recorrido) {
        if (aux != null)
        {
            preorden(aux.getIzq(), recorrido);
            recorrido.add(aux.getDato());
            preorden(aux.getDer(), recorrido);
        }
    }
    
    public void postorden(Nodo aux, LinkedList recorrido) {
        if (aux != null) {
            postorden(aux.getIzq(), recorrido);
            postorden(aux.getDer(), recorrido);
            recorrido.add(aux.getDato());
        }
    }
    
    public void porNivel(Nodo aux, LinkedList recorrido) {
        LinkedList<Nodo> cola = new LinkedList<>();
        cola.addLast(aux);
        while (cola.size() > 0) {
            Nodo temp = cola.pollFirst();
            recorrido.add(temp.getDato());
            if (temp.getIzq() != null) {
                cola.addLast(temp.getIzq());
            }
            if (temp.getDer() != null) {
                cola.addLast(temp.getDer());
            }
        }
    }
    
    public boolean existe(int dato) {
        Nodo aux = raiz;
        while (aux != null) {
            if (dato == aux.getDato())
                return true;
            else if (dato > aux.getDato())
                aux = aux.getDer();
            else
                aux = aux.getIzq();
        }
        return false;
    }
    
    private void altura (Nodo aux, int nivel) {
        if (aux != null) {
            altura(aux.getIzq(), nivel + 1);
            alt = nivel;
            altura(aux.getDer(), nivel+1);
        }
    }
    
    public int getAltura() {
        altura(raiz, 1);
        return alt;
    }
}
