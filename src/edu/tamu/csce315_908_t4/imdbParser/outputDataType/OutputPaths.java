package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class OutputPaths{
    private OutputDataBlock data;
    private HashMap<OutputTable, File> files;

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

        files = new HashMap<>(OutputTable.values().length);
        files.put(OutputTable.AKA_TABLE, new File(akaTable));
        files.put(OutputTable.CHARACTER_TABLE, new File(characterTable));
        files.put(OutputTable.CREW_TABLE, new File(crewTable));
        files.put(OutputTable.EPISODE_TABLE, new File(episodeTable));
        files.put(OutputTable.KNOWN_FOR_TABLE, new File(knownForTable));
        files.put(OutputTable.PERSON_TABLE, new File(personTable));
        files.put(OutputTable.PRINCIPAL_TABLE, new File(principalTable));
        files.put(OutputTable.PROFESSION_TABLE, new File(professionTable));
        files.put(OutputTable.RATING_TABLE, new File(ratingTable));
        files.put(OutputTable.TITLE_TABLE, new File(titleTable));
        files.put(OutputTable.GENRE_TABLE, new File(genreTable));

        files.forEach((outputTable, file) -> {
            try{
                file.delete();
                file.createNewFile();
                if(!file.canWrite()){
                    throw new RuntimeException("Cannot write to " + outputTable.readableName + " file: " + file.getAbsolutePath());
                }
            } catch(IOException e){
                System.err.println("IOException on file " + outputTable.readableName + ": " + file.getAbsolutePath());
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Outputs data to files in json format
     *
     * @param outputDataBlock the data to be output
     * @param filePaths       the paths to the files to be written to, may or may not already exist and will be
     *                        overwritten if so
     */
    public static void sendToFiles(OutputDataBlock outputDataBlock, OutputPaths filePaths){

    }
}
