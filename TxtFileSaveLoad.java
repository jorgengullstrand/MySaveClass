package com.moruscerberus.ravenspath.save;

import com.moruscerberus.ravenspath.GameState;
import com.moruscerberus.ravenspath.Global;
import com.moruscerberus.ravenspath.gfx.Color;
import com.moruscerberus.ravenspath.gfx.Renderer;
import com.moruscerberus.ravenspath.level.Level;
import com.moruscerberus.ravenspath.sound.Music;
import com.moruscerberus.ravenspath.sound.Sound;
import com.moruscerberus.ravenspath.util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.TimeZone;

public class TxtFileSaveLoad implements ActionListener {


    // Save file Directory for Release is: C:/Users/current user/Saved Games/Ravenspath/saves/

    // Save file Directory for Debug is: ProjectFolder/saves/
    public static String DEV_PATH = "saves/";

    static String saveFile =  "savefile" +  ".txt";
    static String settingsFile = "settings" + ".txt";
    static String achievementsFile = "achievements" + ".txt";

    private static final int SAVE_ICON_X = 16, SAVE_ICON_Y = 11;

    private static float pos = 1f;


    static String className = "TxtFileSaveLoad";
    private static File saveFilesDirectory(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        return new File(directory + "/" + filename);
    }



    public static void loadGameData(){
        try{
            BufferedReader br = new BufferedReader(new FileReader((saveFilesDirectory(Global.SAVE_PATH, saveFile))));
            Level.seed = Long.parseLong(br.readLine());
            GameState.player.health = Integer.parseInt(br.readLine());
            GameState.player.stamina = Integer.parseInt(br.readLine());
            GameState.player.x = Integer.parseInt(br.readLine());
            GameState.player.y = Integer.parseInt(br.readLine());
            GameState.playerColor = Integer.parseInt(br.readLine());
            GameState.difficulty = Float.parseFloat(br.readLine());
            GameState.score = Integer.parseInt(br.readLine());
            DayNightCycle.ticks = Integer.parseInt(br.readLine());
            DayNightCycle.days = Integer.parseInt(br.readLine());
            GameplayTimer.secondsPassed = Integer.parseInt(br.readLine());

            br.close();
            Log.info(className, "Loaded Game Successfully");

        } catch (Exception e){
            Log.error(className, "Error Loading Game Data: " + e.getMessage());
        }

    }

    public static boolean showIcon;
    public static void saveGameData(){
        try {

            showIcon = true;

            BufferedWriter bw = new BufferedWriter(new FileWriter(saveFilesDirectory(Global.SAVE_PATH, saveFile)));
            bw.write("" + Level.seed);
            bw.newLine();
            bw.write("" + GameState.player.health);
            bw.newLine();
            bw.write("" + GameState.player.stamina);
            bw.newLine();
            bw.write("" + GameState.player.x);
            bw.newLine();
            bw.write("" + GameState.player.y);
            bw.newLine();
            bw.write("" + GameState.playerColor);
            bw.newLine();
            bw.write("" + GameState.difficulty);
            bw.newLine();
            bw.write("" + GameState.score);
            bw.newLine();
            bw.write("" + DayNightCycle.ticks);
            bw.newLine();
            bw.write("" + DayNightCycle.days);
            bw.newLine();
            bw.write(""+ GameplayTimer.secondsPassed);
            bw.close();
            Log.info(className, "Saved Game Successfully");

        }
        catch (Exception e)
        {
            Log.error(className,"Error Saving Game!" + e.getMessage());
        }
    }

    // SETTINGS

    public static void loadSettingsData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader((saveFilesDirectory(Global.SAVE_PATH, settingsFile))));
            GameState.toggleFocus = Boolean.parseBoolean(br.readLine());
            Window.fullscreen = Boolean.parseBoolean(br.readLine());
            Window.vsync = Boolean.parseBoolean(br.readLine());
            Music.muted = Boolean.parseBoolean(br.readLine());
            Sound.muted = Boolean.parseBoolean(br.readLine());
            Window.showFPS = Boolean.parseBoolean(br.readLine());
            br.close();
            Log.info(className, "Loaded Settings Successfully");
        }
        catch (Exception e){
            Log.error(className,"Error Loading Settings!" + e.getMessage());
        }
    }

    public static void saveSettingsData(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter((saveFilesDirectory(Global.SAVE_PATH, settingsFile))));
            ;
            bw.write("" + GameState.toggleFocus);
            bw.newLine();
            bw.write("" + Window.fullscreen);
            bw.newLine();
            bw.write("" + Window.vsync);
            bw.newLine();
            bw.write("" + Music.muted);
            bw.newLine();
            bw.write("" + Sound.muted);
            bw.newLine();
            bw.write("" + Window.showFPS);
            bw.close();
            Log.info(className, "Saved Settings Successfully");


        }
        catch (Exception e){
            Log.error(className,"Error Saving Settings: " + e.getMessage());
        }
    }

    public static void loadAchievementData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader((saveFilesDirectory(Global.SAVE_PATH, achievementsFile))));
            Achievement.name = br.readLine();
            Achievement.completed = Boolean.parseBoolean(br.readLine());
            br.close();
            Log.info(className, "Loaded Achievements Successfully");

        }
        catch (Exception e){
            Log.error(className, "Error Loading Achievements: " + e.getMessage());
        }
    }

    public static void saveAchievementData(){
        try {
        BufferedWriter bw = new BufferedWriter(new FileWriter((saveFilesDirectory(Global.SAVE_PATH, achievementsFile))));
        bw.write(Achievement.name + Achievement.completed);
        bw.close();
        Log.info("TxtFileSaveLoad", "Saved Achievements Successfully");

    }
        catch (Exception e){
            Log.error("TxtFileSaveLoad","Error saving achievements Data: " + e.getMessage());
    }

    }




    public void renderShowIcon(){


        Renderer.render(
                SAVE_ICON_X, SAVE_ICON_Y,
                Renderer.WIDTH - 15, (int) (Renderer.HEIGHT +- pos),
                555,
                Renderer.FLIP_NONE
        );

        if (pos >= 0 && pos <= 12) {
            pos++;

        }
        else {
            pos--;

        }



    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
