package dsa_ca;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author edward
 */
public interface DLLInterface {
    public boolean isEmpty();

    public int size();

    public Object get(int index);

    public Object remove(int index);

    public void add(int index, Object theElement);

    //public void add(Object element);
    public String printList();
}
