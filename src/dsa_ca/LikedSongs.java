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

/**
 *
 * @author edward
 */
public class LikedSongs implements StackInterface {
    private ArrayList<Song> likedSongs;
    private static final String LAST_SONG_ID_FILE = "data/last_song_id.txt";
    private int lastSongID;

    public ArrayList<Song> getLikedSongs() {
        return likedSongs;
    }

    public void setLikedSongs(ArrayList<Song> likedSongs) {
        this.likedSongs = likedSongs;
    }

    public LikedSongs() {
        likedSongs = new ArrayList<>();
        populateLikedSongsFromCSV("data/songs.csv");
    }

    // populates the stack from the csv that stores all of the songs
    private void populateLikedSongsFromCSV(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int lineCounter = 0;
            while ((line = br.readLine()) != null) {
                if (lineCounter > 0) { // Skip the header line
                    String[] data = line.split(",");
                    // Assuming the CSV format is: ID,Name,Artist,Genre
                    String id = data[0];
                    String name = data[1];
                    String artist = data[2];
                    String genre = data[3];
                    likedSongs.add(new Song(Integer.parseInt(id), name, artist, genre));
                }
                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void push(Object newItem) {
        if (newItem instanceof Song) {
            likedSongs.add((Song) newItem);
        } else {
            System.out.println("Error: Only Song objects can be pushed onto the stack.");
        }
    }

    @Override
    public Object pop() {
        if (!likedSongs.isEmpty()) {
            return likedSongs.remove(likedSongs.size() - 1);
        } else {
            System.out.println("Liked playlist is empty.");
            return null;
        }
    }

    @Override
    public Object peek() {
        if (!likedSongs.isEmpty()) {
            return likedSongs.isEmpty();
        } else {
            System.out.println("Error: Stack is empty.");
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return likedSongs.isEmpty();
    }

    @Override
    public int size() {
        return likedSongs.size();
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        for (Song song : likedSongs) {
            sb.append(song.toString()).append("\n");
        }
        return sb.toString();
    }

    // saves the song to the playlist with the corresponding genge
    private void saveSongToGenrePlaylistCSV(Song song, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Append the song information to the CSV file
            writer.write(song.getId() + "," + song.getName() + "," + song.getArtist() + "," + song.getGenre());
            writer.newLine();
            System.out.println("Song added to genre playlist CSV successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing to genre playlist CSV file: " + e.getMessage());
        }
    }

    // reads the file that contains the value of the next id for the new song so
    // there is no duplicate ids
    private int readLastSongID() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LAST_SONG_ID_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0; // Default value if the file doesn't exist or cannot be read
    }

    // rewrites the last song id
    private void saveLastSongID(int lastSongID) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_SONG_ID_FILE))) {
            writer.write(Integer.toString(lastSongID));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void incrementAndSaveLastSongID() {
        lastSongID++;
        saveLastSongID(lastSongID);
    }
}
