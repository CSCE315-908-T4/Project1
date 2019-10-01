package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.BiConsumer;

public class OutputPaths{
    private OutputDataBlock data;
    private HashMap<EOutputTable, FileWriter> files;

    public void setData(OutputDataBlock data){
        this.data = data;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public OutputPaths(
            OutputDataBlock data,
            String characterTable,
            String crewTable,
            String episodeTable,
            String knownForTable,
            String personTable,
            String principalTable,
            String professionTable,
            String ratingTable,
            String titleTable,
            String genreTable
    ){
        this.data = data;

        files = new HashMap<>(EOutputTable.values().length);

        try{
            new File(characterTable).createNewFile();
            new File(crewTable).createNewFile();
            new File(episodeTable).createNewFile();
            new File(knownForTable).createNewFile();
            new File(personTable).createNewFile();
            new File(principalTable).createNewFile();
            new File(professionTable).createNewFile();
            new File(ratingTable).createNewFile();
            new File(titleTable).createNewFile();
            new File(genreTable).createNewFile();

            files.put(EOutputTable.CHARACTER_TABLE, new FileWriter(characterTable));
            files.put(EOutputTable.CREW_TABLE, new FileWriter(crewTable));
            files.put(EOutputTable.EPISODE_TABLE, new FileWriter(episodeTable));
            files.put(EOutputTable.KNOWN_FOR_TABLE, new FileWriter(knownForTable));
            files.put(EOutputTable.PERSON_TABLE, new FileWriter(personTable));
            files.put(EOutputTable.PRINCIPAL_TABLE, new FileWriter(principalTable));
            files.put(EOutputTable.PROFESSION_TABLE, new FileWriter(professionTable));
            files.put(EOutputTable.RATING_TABLE, new FileWriter(ratingTable));
            files.put(EOutputTable.TITLE_TABLE, new FileWriter(titleTable));
            files.put(EOutputTable.GENRE_TABLE, new FileWriter(genreTable));
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private class SendForEach implements BiConsumer<EOutputTable, FileWriter>{
        @Override
        public void accept(EOutputTable eOutputTable, FileWriter file){
            try{
                file.write("[\n");
                for(int x = 0; x < data.getOutputData().get(eOutputTable).size(); x++){
                    IOutputTable line = data.getOutputData().get(eOutputTable).get(x);
                    if(x != 0){
                        file.write(",\n");
                    }
                    JsonObject object = new JsonObject();
                    for(Field field : eOutputTable.tableClass.getDeclaredFields()){
                        Class<?> type = field.getType();
                        if(type == String.class){
                            object.addProperty(field.getName(), (String) field.get(line));
                        } else if(type == int.class || type == Integer.class){
                            object.addProperty(field.getName(), (Integer)field.get(line));
                        } else if(type == boolean.class || type == Boolean.class){
                            object.addProperty(field.getName(), (Boolean)field.get(line));
                        } else if(type == short.class || type == Short.class){
                            object.addProperty(field.getName(), (Short)field.get(line));
                        } else if(type == byte.class || type == Byte.class){
                            object.addProperty(field.getName(), (Byte)field.get(line));
                        } else if(type == long.class || type == Long.class){
                            object.addProperty(field.getName(), (Long)field.get(line));
                        } else if(type == double.class || type == Double.class){
                            object.addProperty(field.getName(), (Double)field.get(line));
                        } else if(type == float.class || type == Float.class){
                            object.addProperty(field.getName(), (Float)field.get(line));
                        } else if(type == char.class || type == java.lang.Character.class){
                            object.addProperty(field.getName(), (java.lang.Character)field.get(line));
                        } else{
                            throw new RuntimeException("Unknown Class " + type.getName());
                        }
                    }
                    file.write(object.toString());
                }
                file.write("]\n");
            } catch(IOException | IllegalAccessException | NullPointerException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void sendToFiles(){
        files.forEach(new SendForEach());
        files.forEach((eOutputTable1, fileWriter) -> {
            try{
                fileWriter.close();
            }
            catch(IOException e){
                throw new RuntimeException(e);
            }
        });
    }
}
