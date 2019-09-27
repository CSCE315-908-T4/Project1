package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class InputDataBlock{
    private ArrayList<NameBasic> nameBasics;
    private ArrayList<TitleAKA> titleAKAs;
    private ArrayList<TitleBasic> titleBasics;
    private ArrayList<TitleCrew> titleCrews;
    private ArrayList<TitleEpisode> titleEpisodes;
    private ArrayList<TitlePrincipal> titlePrincipals;
    private ArrayList<TitleRating> titleRatings;

    public static ArrayList<File> CreateFileTypes(ArrayList<String> filePaths) {
        ArrayList<File> importFiles = new ArrayList<File>;
        for (String s : filePaths) {
            importFiles.add(new File(s));
        }
        return importFiles;
    }

    public static void getFileData(File f) {
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filePaths
     * @return
     */
    public static InputDataBlock readFromFiles(ArrayList<String> filePaths){
        ArrayList<File> files = CreateFileTypes(filePaths);
        for (int i = 0; i < files.size(); i++) {
            switch (i) {
                case 0:
                    setNameBasics
            }
        }

        return InputDataBlock(nameBasics, titleAKAs, titleBasics, titleCrews, titleEpisodes, titlePrincipals, titleRatings);
    }

    public InputDataBlock(ArrayList<NameBasic> nameBasics, ArrayList<TitleAKA> titleAKAs, ArrayList<TitleBasic> titleBasics, ArrayList<TitleCrew> titleCrews, ArrayList<TitleEpisode> titleEpisodes, ArrayList<TitlePrincipal> titlePrincipals, ArrayList<TitleRating> titleRatings){
        this.nameBasics = nameBasics;
        this.titleAKAs = titleAKAs;
        this.titleBasics = titleBasics;
        this.titleCrews = titleCrews;
        this.titleEpisodes = titleEpisodes;
        this.titlePrincipals = titlePrincipals;
        this.titleRatings = titleRatings;
    }

    public ArrayList<TitleAKA> getTitleAKAs(){
        return titleAKAs;
    }

    public ArrayList<NameBasic> getNameBasics(){
        return nameBasics;
    }

    public ArrayList<TitleBasic> getTitleBasics(){
        return titleBasics;
    }

    public ArrayList<TitleCrew> getTitleCrews(){
        return titleCrews;
    }

    public ArrayList<TitleEpisode> getTitleEpisodes(){
        return titleEpisodes;
    }

    public ArrayList<TitlePrincipal> getTitlePrincipals(){
        return titlePrincipals;
    }

    public ArrayList<TitleRating> getTitleRatings(){
        return titleRatings;
    }
}
