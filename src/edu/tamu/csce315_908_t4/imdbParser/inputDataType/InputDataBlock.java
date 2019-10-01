package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class InputDataBlock{
    private ArrayList<NameBasic> nameBasics;
    private ArrayList<TitleAKA> titleAKAs;
    private ArrayList<TitleBasic> titleBasics;
    private ArrayList<TitleCrew> titleCrews;
    private ArrayList<TitleEpisode> titleEpisodes;
    private ArrayList<TitlePrincipal> titlePrincipals;
    private ArrayList<TitleRating> titleRatings;

    private InputDataBlock(ArrayList<NameBasic> nameBasics, ArrayList<TitleAKA> titleAKAs, ArrayList<TitleBasic> titleBasics, ArrayList<TitleCrew> titleCrews, ArrayList<TitleEpisode> titleEpisodes, ArrayList<TitlePrincipal> titlePrincipals, ArrayList<TitleRating> titleRatings){
        this.nameBasics = nameBasics;
        this.titleAKAs = titleAKAs;
        this.titleBasics = titleBasics;
        this.titleCrews = titleCrews;
        this.titleEpisodes = titleEpisodes;
        this.titlePrincipals = titlePrincipals;
        this.titleRatings = titleRatings;
    }

    private static ArrayList<File> CreateFileTypes(ArrayList<String> filePaths){
        ArrayList<File> importFiles = new ArrayList<>();
        for (String s : filePaths) {
            importFiles.add(new File(s));
        }
        return importFiles;
    }

    private static Short parseNullableShort(String in){
        if(in.equals("\\N")){
            return null;
        }
        return Short.parseShort(in);
    }

    /**
     * @param filePaths
     * @return
     */
    public static InputDataBlock readFromFiles(ArrayList<String> filePaths){
        ArrayList<File> files = CreateFileTypes(filePaths);
        ArrayList<NameBasic> nameBasics = new ArrayList<>(10000000);
        ArrayList<TitleAKA> titleAKAs = new ArrayList<>(10000000);
        ArrayList<TitleBasic> titleBasics = new ArrayList<>(10000000);
        ArrayList<TitleCrew> titleCrews = new ArrayList<>(10000000);
        ArrayList<TitleEpisode> titleEpisodes = new ArrayList<>(10000000);
        ArrayList<TitlePrincipal> titlePrincipals = new ArrayList<>(10000000);
        ArrayList<TitleRating> titleRatings = new ArrayList<>(10000000);
        BufferedReader tsvReader;
        try{
            for(int i = 0; i < files.size(); i++){
                tsvReader = new BufferedReader(new FileReader(files.get(i)));
                tsvReader.readLine(); // trash first line
                String dataLine = tsvReader.readLine();
                System.out.println("Reading file num " + i);
                long line_num = 0;
                switch(i){
                    case 0:
                        while(dataLine != null){
                            if(line_num % 1000000 == 0){
                                System.out.println("Line num: " + line_num);
                            }
                            line_num++;
                            String[] columns = dataLine.split("\\t");
                            nameBasics.add(new NameBasic(columns[0], columns[1], parseNullableShort(columns[2]), parseNullableShort(columns[3]), columns[4], columns[5]));
                            dataLine = tsvReader.readLine();
                        }
                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 1:
                        while(dataLine != null){
                            if(line_num % 1000000 == 0){
                                System.out.println("Line num: " + line_num);
                            }
                            line_num++;
                            String[] columns = dataLine.split("\\t");
                            titleAKAs.add(new TitleAKA(columns[0], Integer.parseInt(columns[1]), columns[2], columns[3], columns[4], columns[5], columns[6], Boolean.parseBoolean(columns[7])));
                            dataLine = tsvReader.readLine();
                        }
                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 2:
                        while(dataLine != null){
                            if(line_num % 1000000 == 0){
                                System.out.println("Line num: " + line_num);
                            }
                            line_num++;
                            String[] columns = dataLine.split("\\t");
                            titleBasics.add(new TitleBasic(columns[0], columns[1], columns[2], columns[3], Boolean.parseBoolean(columns[4]), Short.parseShort(columns[5]), Short.parseShort(columns[6]), Integer.parseInt(columns[7]), columns[8]));
                            dataLine = tsvReader.readLine();
                        }
                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 3:
                        while(dataLine != null){
                            if(line_num % 1000000 == 0){
                                System.out.println("Line num: " + line_num);
                            }
                            line_num++;
                            String[] columns = dataLine.split("\\t");
                            titleCrews.add(new TitleCrew(columns[0], columns[1], columns[2]));
                            dataLine = tsvReader.readLine();
                        }
                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 4:
                        while(dataLine != null){
                            if(line_num % 1000000 == 0){
                                System.out.println("Line num: " + line_num);
                            }
                            line_num++;
                            String[] columns = dataLine.split("\\t");
                            titleEpisodes.add(new TitleEpisode(columns[0], columns[1], Short.parseShort(columns[2]), Integer.parseInt(columns[3])));
                            dataLine = tsvReader.readLine();
                        }
                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 5:
                        while(dataLine != null){
                            if(line_num % 1000000 == 0){
                                System.out.println("Line num: " + line_num);
                            }
                            line_num++;
                            String[] columns = dataLine.split("\\t");
                            titlePrincipals.add(new TitlePrincipal(columns[0], Integer.parseInt(columns[1]), columns[2], columns[3], columns[4], columns[5]));
                            dataLine = tsvReader.readLine();
                        }
                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 6:
                        while(dataLine != null){
                            if(line_num % 1000000 == 0){
                                System.out.println("Line num: " + line_num);
                            }
                            line_num++;
                            String[] columns = dataLine.split("\\t");
                            titleRatings.add(new TitleRating(columns[0], Float.parseFloat(columns[1]), Integer.parseInt(columns[2])));
                            dataLine = tsvReader.readLine();
                        }
                        System.out.println("Lines processed: " + line_num);
                        break;
                }
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        return new InputDataBlock(nameBasics, titleAKAs, titleBasics, titleCrews, titleEpisodes, titlePrincipals, titleRatings);
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
