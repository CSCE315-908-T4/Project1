package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class InputDataBlock{
    private ArrayList<NameBasic> nameBasics;
    private ArrayList<TitleBasic> titleBasics;
    private ArrayList<TitleCrew> titleCrews;
    private ArrayList<TitleEpisode> titleEpisodes;
    private ArrayList<TitlePrincipal> titlePrincipals;
    private ArrayList<TitleRating> titleRatings;

    private InputDataBlock(ArrayList<NameBasic> nameBasics, ArrayList<TitleBasic> titleBasics, ArrayList<TitleCrew> titleCrews, ArrayList<TitleEpisode> titleEpisodes, ArrayList<TitlePrincipal> titlePrincipals, ArrayList<TitleRating> titleRatings){
        this.nameBasics = nameBasics;
        this.titleBasics = titleBasics;
        this.titleCrews = titleCrews;
        this.titleEpisodes = titleEpisodes;
        this.titlePrincipals = titlePrincipals;
        this.titleRatings = titleRatings;
    }

    /**
     * Turn text files into File types
     *
     * @param filePaths
     * @return {@link ArrayList<File>}
     */
    private static ArrayList<File> CreateFileTypes(ArrayList<String> filePaths){
        ArrayList<File> importFiles = new ArrayList<>();
        for (String s : filePaths) {
            importFiles.add(new File(s));
        }
        return importFiles;
    }

    private static<T extends Number> T parseNullable(String in, Class<T> clazz){
        if(in.equals("\\N")){
            return null;
        }
        final int intHash = Integer.class.hashCode();
        final int shortHash = Short.class.hashCode();

        try{
            if(clazz == Integer.class){
                return clazz.getConstructor(int.class).newInstance(Integer.parseInt(in));
            }
            if(clazz == Short.class){
                return clazz.getConstructor(short.class).newInstance(Short.parseShort(in));
            }
        }
        catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Unknown class " + clazz.getName());
    }

    /**
     * Inputs files in filePaths into our ArrayLists
     * @param filePaths
     * @return InputDataBlock
     */
    public static InputDataBlock readFromFiles(ArrayList<String> filePaths){
        ArrayList<File> files = CreateFileTypes(filePaths);
        ArrayList<NameBasic> nameBasics = new ArrayList<>(10000000);
        ArrayList<TitleBasic> titleBasics = new ArrayList<>(10000000);
        ArrayList<TitleCrew> titleCrews = new ArrayList<>(10000000);
        ArrayList<TitleEpisode> titleEpisodes = new ArrayList<>(10000000);
        ArrayList<TitlePrincipal> titlePrincipals = new ArrayList<>(10000000);
        ArrayList<TitleRating> titleRatings = new ArrayList<>(10000000);
        BufferedReader tsvReader;
        try {
            // loop through each file and inserts into correct ArrayList
            for(int i = 0; i < files.size(); i++){
                tsvReader = new BufferedReader(new FileReader(files.get(i)));
                tsvReader.readLine(); // trash first line
                String dataLine = tsvReader.readLine();
                System.out.println("Reading file num " + i);
                long line_num = 0;
                switch(i){
                    case 0:
//                        while(dataLine != null){
//                            if(line_num % 1000000 == 0){
//                                System.out.println("Line num: " + line_num);
//                            }
//                            line_num++;
//                            String[] columns = dataLine.split("\\t");
//                            nameBasics.add(new NameBasic(columns[0], columns[1], parseNullable(columns[2], Short.class), parseNullable(columns[3], Short.class), columns[4], columns[5]));
//                            dataLine = tsvReader.readLine();
//                        }
//                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 1:
//                        while(dataLine != null){
//                            if(line_num % 1000000 == 0){
//                                System.out.println("Line num: " + line_num);
//                            }
//                            line_num++;
//                            String[] columns = dataLine.split("\\t");
//                            titleBasics.add(new TitleBasic(columns[0], columns[1], columns[2], columns[3], Boolean.parseBoolean(columns[4]), parseNullable(columns[5], Short.class), parseNullable(columns[6], Short.class), parseNullable(columns[7], Integer.class), columns[8]));
//                            dataLine = tsvReader.readLine();
//                        }
//                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 2:
//                        while(dataLine != null){
//                            if(line_num % 1000000 == 0){
//                                System.out.println("Line num: " + line_num);
//                            }
//                            line_num++;
//                            String[] columns = dataLine.split("\\t");
//                            titleCrews.add(new TitleCrew(columns[0], columns[1], columns[2]));
//                            dataLine = tsvReader.readLine();
//                        }
//                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 3:
//                        while(dataLine != null){
//                            if(line_num % 1000000 == 0){
//                                System.out.println("Line num: " + line_num);
//                            }
//                            line_num++;
//                            String[] columns = dataLine.split("\\t");
//                            titleEpisodes.add(new TitleEpisode(columns[0], columns[1], parseNullable(columns[2], Short.class), parseNullable(columns[3], Integer.class)));
//                            dataLine = tsvReader.readLine();
//                        }
//                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 4:
                        while(dataLine != null){
                            if(line_num % 1000000 == 0){
                                System.out.println("Line num: " + line_num);
                            }
                            line_num++;
                            String[] columns = dataLine.split("\\t");
                            titlePrincipals.add(new TitlePrincipal(columns[0], parseNullable(columns[1], Integer.class), columns[2], columns[3], columns[4], columns[5]));
                            dataLine = tsvReader.readLine();
                        }
                        System.out.println("Lines processed: " + line_num);
                        break;
                    case 5:
//                        while(dataLine != null){
//                            if(line_num % 1000000 == 0){
//                                System.out.println("Line num: " + line_num);
//                            }
//                            line_num++;
//                            String[] columns = dataLine.split("\\t");
//                            titleRatings.add(new TitleRating(columns[0], Float.parseFloat(columns[1]), parseNullable(columns[2], Integer.class)));
//                            dataLine = tsvReader.readLine();
//                        }
//                        System.out.println("Lines processed: " + line_num);
                        break;
                }
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        return new InputDataBlock(nameBasics, titleBasics, titleCrews, titleEpisodes, titlePrincipals, titleRatings);
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
