/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa_ca;

import dsa_ca.Song;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author edward
 */
public class GenrePlaylist implements DLLInterface {
private Node head;
    private Node tail;
        private int size; //manually manage the list size
    private Node curr;
private Node last;
    
    GenrePlaylist(){ //empty list created
        head = null; 
        tail = null; 
        size = 0;
        curr = head;
    }
private void setCurrent(int index){
    if (head == null) {
        throw new IllegalStateException("The list is empty");
    }
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    curr = head; //start at the head 
    System.out.println("Initial curr: " + curr); // Debug statement
    for (int i  = 0; i < index; i++){ // Start from 0, not 1
        curr = curr.getNext();
        System.out.println("Curr after " + i + " iterations: " + curr); // Debug statement
    }
}
    @Override
    public boolean isEmpty() {
	 return (size == 0);
    }

    @Override
    public int size() {
	 return size;
    }

@Override
public Object get(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    setCurrent(index);
    if (curr == null) {
        throw new IllegalStateException("Current node is null");
    }
    return curr.getElement(); // Return the element of the Node
}

    @Override
    public Object remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
@Override
public void add(int index, Object element) {
    if (index < 0 || index > size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    
    Node newNode = new Node(element, null, null);    
    if (size == 0) {
        // This is the first node, so it's both the first and last node
        head = newNode;
        tail = newNode;
    } else if (index == 0) {
        // Add to the start of the list
        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
    } else if (index == size) {
        // Add to the end of the list
        tail.setNext(newNode);
        newNode.setPrev(tail);
        tail = newNode;
    } else {
        // Add to somewhere in the middle of the list
        Node current = getNode(index);
        Node previous = current.getPrev();
        newNode.setPrev(previous);
        newNode.setNext(current);
        previous.setNext(newNode);
        current.setPrev(newNode);
    }
    
    size++; // Don't forget to increment the size
}

public Node getHead() {
    return head;
}

public Node getTail() {
    return tail;
}



public void clear() {
    head = null;
    tail = null;
        System.out.println("Cleared the list");

}
@Override
public String printList() {
    StringBuilder sb = new StringBuilder();
    Node current = head;
    while (current != null) {
        sb.append(current.getElement()).append("\n");
        current = current.getNext();
    }
    return sb.toString();
}
// this reads the song that are stored in the csv file
public GenrePlaylist readPlaylistFromFile(String filePath) {
    GenrePlaylist genrePlaylist = new GenrePlaylist();
    try {
        System.out.println("Opening file: " + filePath);
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        // Skip the first line if it's a header line
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println("Read line: " + line);
            String[] songAttributes = line.split(",");
            if (songAttributes.length == 4) {
                int songId = Integer.parseInt(songAttributes[0]);
                String songName = songAttributes[1];
                String songArtist = songAttributes[2];
                String songGenre = songAttributes[3];
                Song song = new Song(songId, songName, songArtist, songGenre);
                Node songNode = new Node(song, null, null); // Create a Node with the Song as the element
                genrePlaylist.add(genrePlaylist.size(), songNode); // Add the Node to the GenrePlaylist
                System.out.println("Added node to genrePlaylist: " + songNode);
            } else {
                System.out.println("Invalid line: " + line);
            }
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + filePath);
    } catch (Exception e) {
        System.out.println("An error occurred while reading the file: " + e.getMessage());
    }
    if (genrePlaylist.size() == 0) {
        throw new IllegalStateException("No songs were added to the playlist");
    }
    return genrePlaylist;
}


void populateDLL(GenrePlaylist genrePlaylist) {
    // Clear the current DLL before populating it
    clear();

    // Iterate through the genrePlaylist and add nodes to the DLL
    for (int i = 0; i < genrePlaylist.size(); i++) {
        // Retrieve the node at index i from the genrePlaylist
        Node oldNode = null;
        try {
            oldNode = (Node) genrePlaylist.get(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + i + " is out of bounds");
            continue;
        }

        // Check if oldNode is null
        if (oldNode == null) {
            System.out.println("Node at index " + i + " is null");
            continue;
        }

        // Create a new node with the same element as the old node
        Node newNode = new Node(oldNode.getElement(), null, null);

        // Add the new node to the end of the DLL
        if (isEmpty()) {
            // If the DLL is empty, set both head and tail to the new node
            head = newNode;
            tail = newNode;
        } else {
            // Otherwise, add the new node after the current tail node
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++; // Increment the size of the DLL
    }
}

private Node getNode(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    Node node = head;
    for (int i = 0; i < index; i++) {
        node = node.getNext();
    }
    return node;
}
}


