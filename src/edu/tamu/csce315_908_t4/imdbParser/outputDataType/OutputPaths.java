package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

public class OutputPaths{
    private OutputDataBlock data;
    private HashMap<EOutputTable, FileWriter> files;

    public OutputPaths(
            OutputDataBlock data,
            String akaTable,
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
            files.put(EOutputTable.AKA_TABLE, new FileWriter(akaTable));
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

    public void sendToFiles(){
        files.forEach((eOutputTable, file) -> {
            try{
                file.write("[\n");
                for(Iterator<? extends IOutputTable> iterator = data.getOutputData().get(eOutputTable).iterator(); iterator.hasNext(); ){
                    IOutputTable line = iterator.next();
                    if(iterator != data.getOutputData().get(eOutputTable).iterator()){
                        file.write(",\n");
                    }
                    JsonObject object = new JsonObject();
                    for(Field field : eOutputTable.tableClass.getDeclaredFields()){
                        Class<?> type = field.getType();
                        if(type == String.class){
                            object.addProperty(field.getName(), (String) field.get(line));
                        } else if(type == int.class || type == Integer.class){
                            object.addProperty(field.getName(), field.getInt(line));
                        } else if(type == boolean.class || type == Boolean.class){
                            object.addProperty(field.getName(), field.getBoolean(line));
                        } else if(type == short.class || type == Short.class){
                            object.addProperty(field.getName(), field.getShort(line));
                        } else if(type == byte.class || type == Byte.class){
                            object.addProperty(field.getName(), field.getByte(line));
                        } else if(type == long.class || type == Long.class){
                            object.addProperty(field.getName(), field.getLong(line));
                        } else if(type == double.class || type == Double.class){
                            object.addProperty(field.getName(), field.getDouble(line));
                        } else if(type == float.class || type == Float.class){
                            object.addProperty(field.getName(), field.getFloat(line));
                        } else if(type == char.class || type == java.lang.Character.class){
                            object.addProperty(field.getName(), field.getChar(line));
                        } else{
                            throw new RuntimeException("Unknown Class " + type.getName());
                        }
                    }
                    file.write(object.toString());
                }
                file.write("]\n");
            } catch(IOException | IllegalAccessException e){
                throw new RuntimeException(e);
            }
        });
    }
}
