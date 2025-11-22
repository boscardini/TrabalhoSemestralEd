package util;

import br.edu.fateczl.Lista;
import model.dto.Disciplina;

public class HashTable {
    private Lista<Disciplina>[] table;
    private int capacity;

  
    public HashTable(int capacity){
        this.capacity = capacity;
        table = new Lista[capacity];
        for (int i = 0; i < capacity; i++) table[i] = new Lista<>();
    }

    private int hash(int key){
        return Math.abs(key) % capacity;
    }

    public void put(int key, Disciplina value) throws Exception{
        int idx = hash(key);
       
        for (int i=0;i<table[idx].size();i++){
            if(table[idx].get(i).getId().equals(value.getId())){
                table[idx].remove(i);
                break;
            }
        }
        table[idx].addLast(value);
    }

    public Lista<Disciplina> getBucket(int key){
        return table[hash(key)];
    }

    public Lista<Disciplina>[] getAllBuckets(){
        return table;
    }
}