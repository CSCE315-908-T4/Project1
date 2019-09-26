package edu.tamu.csce315_908_t4.imdbParser.inputDataType;

public class TitlePrincipal{
    private String tconst;
    private int ordering;
    private String nconst;
    private String category;
    private String job;
    private String characters;

    public TitlePrincipal(String tconst, int ordering, String nconst, String category, String job, String characters){
        this.tconst = tconst;
        this.ordering = ordering;
        this.nconst = nconst;
        this.category = category;
        this.job = job;
        this.characters = characters;
    }

    public String getTconst(){
        return tconst;
    }

    public int getOrdering(){
        return ordering;
    }

    public String getNconst(){
        return nconst;
    }

    public String getCategory(){
        return category;
    }

    public String getJob(){
        return job;
    }

    public String getCharacters(){
        return characters;
    }
}
