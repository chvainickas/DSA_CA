/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dsa_ca;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edward
 */
public class MainMenuGUI extends javax.swing.JFrame {
private NewSongGUI newSongGUI;
private final LikedSongs likedSongsInstance;
private final ArrayList<Song> allLikedSongs;
    private GenrePlaylist genrePlaylist;
    /**
     * Creates new form MainMenu
     */
    public MainMenuGUI() {
        initComponents();
        likedSongsInstance = new LikedSongs();
    allLikedSongs = likedSongsInstance.getLikedSongs(); // populates the stack array to be shown in the liked songs table
        populateLikedTable(); // populates the table 
        NewSongGUI newSongGUI = new NewSongGUI(this);
        // event listener for when a new song is added, it updates the table
        newSongGUI.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("songAdded".equals(evt.getPropertyName())) {
                    // Handle the event here, update the table, etc.
                    populateLikedTable(); // Example method to update the table
                }
            }
        });
        // hides all of the genre playlist editing buttons for the dll genre playlist until the edit mode is on
                addBtn.setVisible(false);
        delBtn.setVisible(false);
        upBtn.setVisible(false);
        downBtn.setVisible(false);
        savePlaylistBtn.setVisible(false);
    }
    
    public void addToLikedSongs(Song newSong) {
    likedSongsInstance.push(newSong);
}


private void populateLikedTable() {
    DefaultTableModel model = (DefaultTableModel) likedTable.getModel();
    model.setRowCount(0); // clear the current table

    String csvFilePath = "data/songs.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
        String line;
        int lineCounter = 0;
        while ((line = br.readLine()) != null) {
            if (lineCounter == 0) { // skips the header line
                lineCounter++;
                continue;
            }
            String[] data = line.split(",");
            // makes sure that each song has 4 headings
            if (data.length >= 4) {
                model.addRow(new Object[]{data[0], data[1], data[2], data[3]});
            }
            lineCounter++;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
// rewrites the csv for when the program is opened up again so no progress is saved
private void saveAllLikedSongsToCSV() {
    // path for the csv file
    String filePath = "data/songs.csv";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        // write the headers
        writer.write("id,name,artist,genre\n");
        
        // Write each song to the csv file
        for (Song song : allLikedSongs) {
            writer.write(song.getId() + "," + song.getName() + "," + song.getArtist() + "," + song.getGenre() + "\n");
        }
        
        System.out.println("All liked songs saved to CSV successfully.");
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error writing all liked songs to CSV file: " + e.getMessage());
    }
}
// search function that looks for the name of the song
private void searchInPlaylist(String searchQuery) {
    DefaultTableModel model = new DefaultTableModel();

    // adds columns to the model of the table
    model.addColumn("ID");
    model.addColumn("Name");
    model.addColumn("Artist");
    model.addColumn("Genre");

    // start at the first songs
    Node current = genrePlaylist.getHead();

    // loops through the playlist and adds any song with the matching searchQuery
    while (current != null) {
        Object element = current.getElement();
        if (element instanceof Node) {
            Node innerNode = (Node) element;
            Object innerElement = innerNode.getElement();
            if (innerElement instanceof Song) {
                Song song = (Song) innerElement;
                // check if the song matches the search query
                if (song.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                    // adds the matching song to the model of the table
                    model.addRow(new Object[]{song.getId(), song.getName(), song.getArtist(), song.getGenre()});
                }
            }
        }
        current = current.getNext();
    }

    // updates the table
    playlistTable.setModel(model);

}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        likeSongsPanel = new javax.swing.JPanel();
        likedLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        likedTable = new javax.swing.JTable();
        addToPlaylistBtn = new javax.swing.JButton();
        addSongBtn = new javax.swing.JButton();
        playlistPanel = new javax.swing.JPanel();
        playlistLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playlistTable = new javax.swing.JTable();
        playlistNameLbl = new javax.swing.JLabel();
        playlistCombo = new javax.swing.JComboBox<>();
        displayBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();
        upBtn = new javax.swing.JButton();
        searchTF = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        editTggl = new javax.swing.JToggleButton();
        downBtn = new javax.swing.JButton();
        songLbl = new javax.swing.JLabel();
        showSongsLbl = new javax.swing.JLabel();
        savePlaylistBtn = new javax.swing.JButton();
        loadBtn = new javax.swing.JButton();
        searchLbl = new javax.swing.JLabel();
        playBtn = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        likeSongsPanel.setBackground(new java.awt.Color(255, 255, 153));

        likedLbl.setText("Liked Songs");

        likedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Artist", "Genre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(likedTable);

        addToPlaylistBtn.setText("Add song to playlist");
        addToPlaylistBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToPlaylistBtnActionPerformed(evt);
            }
        });

        addSongBtn.setText("Add New Liked Song");
        addSongBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout likeSongsPanelLayout = new javax.swing.GroupLayout(likeSongsPanel);
        likeSongsPanel.setLayout(likeSongsPanelLayout);
        likeSongsPanelLayout.setHorizontalGroup(
            likeSongsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(likeSongsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(likeSongsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(likeSongsPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, likeSongsPanelLayout.createSequentialGroup()
                        .addGap(0, 211, Short.MAX_VALUE)
                        .addComponent(likedLbl)
                        .addGap(137, 137, 137))
                    .addGroup(likeSongsPanelLayout.createSequentialGroup()
                        .addComponent(addToPlaylistBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, likeSongsPanelLayout.createSequentialGroup()
                        .addComponent(addSongBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        likeSongsPanelLayout.setVerticalGroup(
            likeSongsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(likeSongsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(likedLbl)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addSongBtn)
                .addGap(7, 7, 7)
                .addComponent(addToPlaylistBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        playlistPanel.setBackground(new java.awt.Color(204, 255, 204));

        playlistLbl.setText("Play List");

        playlistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Artist", "Genre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(playlistTable);

        playlistNameLbl.setText("Playlist:");

        playlistCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pop", "Rap" }));

        displayBtn.setText("Display All Songs");
        displayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayBtnActionPerformed(evt);
            }
        });

        addBtn.setText("Add Song");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        delBtn.setText("Delete Song");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        upBtn.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        upBtn.setText("↑");
        upBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upBtnActionPerformed(evt);
            }
        });

        searchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTFActionPerformed(evt);
            }
        });

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        editTggl.setText("Editing mode");
        editTggl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTgglActionPerformed(evt);
            }
        });

        downBtn.setText("↓");
        downBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downBtnActionPerformed(evt);
            }
        });

        songLbl.setText("No of songs:");

        savePlaylistBtn.setText("Save");
        savePlaylistBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePlaylistBtnActionPerformed(evt);
            }
        });

        loadBtn.setText("Load");
        loadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBtnActionPerformed(evt);
            }
        });

        searchLbl.setText("Song Name:");

        playBtn.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        playBtn.setText("►");
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout playlistPanelLayout = new javax.swing.GroupLayout(playlistPanel);
        playlistPanel.setLayout(playlistPanelLayout);
        playlistPanelLayout.setHorizontalGroup(
            playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playlistPanelLayout.createSequentialGroup()
                .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playlistPanelLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(playlistLbl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(playlistPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(playlistPanelLayout.createSequentialGroup()
                                .addComponent(playlistNameLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playlistCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(loadBtn)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(playlistPanelLayout.createSequentialGroup()
                                .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(songLbl)
                                    .addComponent(searchLbl))
                                .addGap(12, 12, 12)
                                .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(playlistPanelLayout.createSequentialGroup()
                                        .addComponent(searchTF)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(searchBtn))
                                    .addGroup(playlistPanelLayout.createSequentialGroup()
                                        .addComponent(showSongsLbl)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(displayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playlistPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(playlistPanelLayout.createSequentialGroup()
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(upBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(downBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(editTggl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(savePlaylistBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        playlistPanelLayout.setVerticalGroup(
            playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playlistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playlistLbl)
                .addGap(14, 14, 14)
                .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playlistNameLbl)
                    .addComponent(playlistCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayBtn)
                    .addComponent(songLbl)
                    .addComponent(showSongsLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn)
                    .addComponent(searchLbl))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editTggl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(playlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(delBtn)
                    .addComponent(upBtn)
                    .addComponent(downBtn)
                    .addComponent(savePlaylistBtn))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(likeSongsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(playlistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(playlistPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(likeSongsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(897, 678));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTFActionPerformed

    private void addToPlaylistBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToPlaylistBtnActionPerformed
    // pops the last song of the stack
    Song poppedSong = (Song) likedSongsInstance.pop();

    // check if the popped song is not null
    if (poppedSong != null) {
        // rewrites the csv with whats left
        saveAllLikedSongsToCSV();

        // writes the popped song to the corresponding genre playlist
        poppedSong.saveSongToGenrePlaylistCSV(poppedSong); 

        // updates the liked song table without the popped song
        populateLikedTable();
    } else {
        showMessageDialog(null, "No songs in the playlist");
    }



    }//GEN-LAST:event_addToPlaylistBtnActionPerformed

    private void addSongBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongBtnActionPerformed
         if (newSongGUI == null || !newSongGUI.isVisible()) {
        // if the window for the new song form is not open, creates one
        newSongGUI = new NewSongGUI(this); 
        // event listener for when the new song is added
        newSongGUI.addPropertyChangeListener((PropertyChangeEvent evt1) -> {
            if ("songAdded".equals(evt1.getPropertyName())) {
                // when a new song is added update the table to include new song
                populateLikedTable(); 
            }
        });
    }
    newSongGUI.setVisible(true);
    }//GEN-LAST:event_addSongBtnActionPerformed
   

    private void loadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBtnActionPerformed
DefaultTableModel model = (DefaultTableModel) playlistTable.getModel();
model.setRowCount(0); // clears the table so that no songs from other genres are there
String selectedGenre = (String) playlistCombo.getSelectedItem();
    String filePath = "data/playlists/" + selectedGenre + ".csv";

    // load the the songs from the corresponding genre file
    ArrayList<Song> songsForDll = new ArrayList<>(Song.loadSongsFromCSV(filePath));

    // use the dll
    genrePlaylist = new GenrePlaylist();

    // populate the dll
    for (int i = 0; i < songsForDll.size(); i++) {
        Song song = songsForDll.get(i);
        Node songNode = new Node(song, null, null);
        genrePlaylist.add(i, songNode);
    }

    int songCount = genrePlaylist.size();
    String songCountStr = String.valueOf(songCount);
    showSongsLbl.setText(songCountStr);
    
    }//GEN-LAST:event_loadBtnActionPerformed
 public GenrePlaylist getLoadedPlaylist() {
        return genrePlaylist; // this is used for the playerGUI
    }
 
 // this is the display button functionality that updates the genre playlist table
    private void displayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayBtnActionPerformed
DefaultTableModel model = new DefaultTableModel();

// add columns to the model of the table
model.addColumn("ID");
model.addColumn("Name");
model.addColumn("Artist");
model.addColumn("Genre");

// start at this node
Node current = genrePlaylist.getHead();

// loop over each song and add it to the the table model
while (current != null) {
    Object element = current.getElement();
    if (element instanceof Node) {
        Node innerNode = (Node) element;
        Object innerElement = innerNode.getElement();
        if (innerElement instanceof Song) {
            Song song = (Song) innerElement;
            System.out.println("Adding song to table: " + song.getName()); // Debug print
            model.addRow(new Object[]{song.getId(), song.getName(), song.getArtist(), song.getGenre()});
        } else {
            System.out.println("Inner node element is not a Song: " + innerElement); // Debug print
        }
    } else {
        System.out.println("Current node element is not a Node: " + element); // Debug print
    }
    current = current.getNext();
}

// updates the table
playlistTable.setModel(model);    }//GEN-LAST:event_displayBtnActionPerformed
// search button functionality
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
String searchQuery = searchTF.getText();
    
    // make sure the input field is not empty
    if (!searchQuery.isEmpty()) {
        // call the search function to update the table
        searchInPlaylist(searchQuery);
    } else {
        // if there is no search input display message
        showMessageDialog(null, "Please enter a search query.");
    }    }//GEN-LAST:event_searchBtnActionPerformed
// toggle that hides and unhides buttons to do with editing a playlist
    private void editTgglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTgglActionPerformed
boolean isEditMode = editTggl.isSelected();
    
    if (isEditMode) {
        addBtn.setVisible(true);
        delBtn.setVisible(true);
        upBtn.setVisible(true);
        downBtn.setVisible(true);
        savePlaylistBtn.setVisible(true);
    } else {
        addBtn.setVisible(false);
        delBtn.setVisible(false);
        upBtn.setVisible(false);
        downBtn.setVisible(false);
        savePlaylistBtn.setVisible(false);
    }    }//GEN-LAST:event_editTgglActionPerformed
// this is the play button functionality
    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
MainMenuGUI mainMenuGUI = this;
    
    // sees if there was a playlist loaded
    GenrePlaylist playlist = mainMenuGUI.getLoadedPlaylist();
    
    // checks if it is not empty
    if (playlist != null) {
        // Create an instance of PlayerGUI with the loaded playlist
        PlayerGUI playerGUI = new PlayerGUI(playlist);
    } else {
        // if no playlist is loaded, notify the user
        JOptionPane.showMessageDialog(this, "Please load a playlist before playing.", "No Playlist Loaded", JOptionPane.WARNING_MESSAGE);
    }    }//GEN-LAST:event_playBtnActionPerformed
// add button functionality
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
// 1. Pop the song from allLikedSongs and hold it in a variable poppedSong
    Song poppedSong = (Song) likedSongsInstance.pop();

    // Check if poppedSong is null
    if (poppedSong != null) {
        // 2. Save the new allLikedSongs array to the CSV
        saveAllLikedSongsToCSV();

        // 3. Write the poppedSong variable to a CSV based on the genre of the poppedSong
        poppedSong.saveSongToGenrePlaylistCSV(poppedSong); // Pass the poppedSong as an argument

        // 4. Populate the liked table with the updated data
        populateLikedTable();
    } else {
        showMessageDialog(null, "No songs in the playlist");
    }


    }//GEN-LAST:event_addBtnActionPerformed

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
int selectedRow = playlistTable.getSelectedRow();
    
    // checks if any row is selected
    if (selectedRow != -1) {
        // removes that song
        DefaultTableModel model = (DefaultTableModel) playlistTable.getModel();
        model.removeRow(selectedRow);
    }    }//GEN-LAST:event_delBtnActionPerformed
//moves songs up in the table
    private void upBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upBtnActionPerformed
// gets the index of the selected row
    int selectedRow = playlistTable.getSelectedRow();
    
    // checks if the selected row is not the first row and a row is selected
    if (selectedRow > 0 && selectedRow != -1) {
        // swaps the selected row with the row above it
        DefaultTableModel model = (DefaultTableModel) playlistTable.getModel();
        model.moveRow(selectedRow, selectedRow, selectedRow - 1);
        // updates the selection to reflect the new position
        playlistTable.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
    }    }//GEN-LAST:event_upBtnActionPerformed
//moves songs down in the table
    private void downBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downBtnActionPerformed
// gets the index of the selected row
    int selectedRow = playlistTable.getSelectedRow();
    // gets the total number of rows in the table
    int totalRows = playlistTable.getRowCount();

    // checks if the selected row is not the last row and a row is selected
    if (selectedRow != -1 && selectedRow < totalRows - 1) {
        // swaps the selected row with the row below it
        DefaultTableModel model = (DefaultTableModel) playlistTable.getModel();
        model.moveRow(selectedRow, selectedRow, selectedRow + 1);
        // updates the selection to reflect the new position
        playlistTable.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
    }    }//GEN-LAST:event_downBtnActionPerformed
// this saves the playlist after editing
    private void savePlaylistBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePlaylistBtnActionPerformed
String message = "By saving this playlist, you will overwrite the contents of the current playlist as per the table. Are you sure you want to proceed?";
    int option = JOptionPane.showConfirmDialog(this, message, "Confirm Save", JOptionPane.YES_NO_OPTION);
    
    if (option == JOptionPane.YES_OPTION) {
        String filePath = "data/playlists/" + playlistCombo.getSelectedItem() + ".csv";

        // gets the table model
        DefaultTableModel model = (DefaultTableModel) playlistTable.getModel();

        // write the data from the table model to the csv file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.write(model.getValueAt(row, col).toString());
                    if (col < model.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
            System.out.println("Playlist saved successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error saving playlist: " + ex.getMessage());
        }

        // Clear the table
        model.setRowCount(0);
    }    }//GEN-LAST:event_savePlaylistBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenuGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton addSongBtn;
    private javax.swing.JButton addToPlaylistBtn;
    private javax.swing.JButton delBtn;
    private javax.swing.JButton displayBtn;
    private javax.swing.JButton downBtn;
    private javax.swing.JToggleButton editTggl;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel likeSongsPanel;
    private javax.swing.JLabel likedLbl;
    private javax.swing.JTable likedTable;
    private javax.swing.JButton loadBtn;
    private javax.swing.JButton playBtn;
    private javax.swing.JComboBox<String> playlistCombo;
    private javax.swing.JLabel playlistLbl;
    private javax.swing.JLabel playlistNameLbl;
    private javax.swing.JPanel playlistPanel;
    private javax.swing.JTable playlistTable;
    private javax.swing.JButton savePlaylistBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel searchLbl;
    private javax.swing.JTextField searchTF;
    private javax.swing.JLabel showSongsLbl;
    private javax.swing.JLabel songLbl;
    private javax.swing.JButton upBtn;
    // End of variables declaration//GEN-END:variables
}
