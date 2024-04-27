/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa_ca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edward
 */
public class Song {
    private static int songIdCounter;
    private int id;
    private String name, artist, genre;
    String filePath = "data/songs.csv";
    public Song() {
    }

    public Song(int id,String name, String artist, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
        public static int getSongIdCounter() {
        return songIdCounter;
    }
        
@Override
public String toString() {
    return getId() + "," + getName() + "," + getArtist() + "," + getGenre();
}

    public static void setSongIdCounter(int counter) {
        Song.songIdCounter = counter;
    }
public static void initializeSongIdCounter(String filePath) {
    // loads songs from csv file
    List<Song> songs = loadSongsFromCSV(filePath);

    // finds the last id 
    int maxId = 0;
    for (Song song : songs) {
        if (song.getId() > maxId) {
            maxId = song.getId();
        }
    }

    // increment id of last song
    songIdCounter = maxId + 1;
}
// loads the song from csv file into an array for use in stack
    protected static List<Song> loadSongsFromCSV(String filePath) {
        List<Song> songs = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 4) { // makes sure the songs are correct
                try {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String artist = parts[2];
                    String genre = parts[3];
                    songs.add(new Song(id, name, artist, genre));

                    // update the counter of the last song
                    songIdCounter = id;
                } catch (NumberFormatException e) {
                    // skip the line if id is not valid
                    continue;
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error reading from CSV file: " + e.getMessage());
    }

    return songs;
}


    public static void saveSongToCSV(Song song) {
    // file path for the csv file
    String filePath = "data/songs.csv";

    try {
        // creates a FileWriter object to write to the file 
        FileWriter fileWriter = new FileWriter(filePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // writes the song information to the file with the id at the start
        bufferedWriter.write(song.getId() + "," + song.getName() + "," + song.getArtist() + "," + song.getGenre());
        bufferedWriter.newLine(); // next line

        // close the BufferedWriter
        bufferedWriter.close();

        // increments the counter after saving the song
        songIdCounter++;
        
        System.out.println("Song added to CSV successfully.");
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error writing to CSV file: " + e.getMessage());
    }
}
    // writes the song to the corresponding csv
    void saveSongToGenrePlaylistCSV(Song song) {
    // path for the csv file
    String genre = song.getGenre();
    String filePath = "data/playlists/" + genre + ".csv";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        // adds the song information to the csv file
        writer.write(song.getId() + "," + song.getName() + "," + song.getArtist() + "," + song.getGenre());
        writer.newLine();
        System.out.println("Song added to genre playlist CSV successfully.");
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error writing to genre playlist CSV file: " + e.getMessage());
    }
}

    }


