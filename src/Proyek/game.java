package Proyek;

import java.util.ArrayList;

public class game {
    protected String nama;
    protected int score;
    protected int stage;
    protected ArrayList<pesawat> listMusuh;
    protected pesawat player;
    
    public game(String nama, int stage) {
        this.nama = nama;
        this.stage = stage;
        this.score = 0;
        this.listMusuh = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public ArrayList<pesawat> getListMusuh() {
        return listMusuh;
    }
    
    public void addMusuh(pesawatMusuh m){
        this.listMusuh.add(m);
    }

    public pesawat getPlayer() {
        return player;
    }

    public void setPlayer(pesawat player) {
        this.player = player;
    }
    
}
