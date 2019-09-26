package edu.tamu.csce315_908_t4.imdbParser.outputDataType;

public class Principal{
    private String tconst;
    private int ordering;
    private String nconst;
    private String category;
    private String job;

    public Principal(String tconst, int ordering, String nconst, String category, String job){
        this.tconst = tconst;
        this.ordering = ordering;
        this.nconst = nconst;
        this.category = category;
        this.job = job;
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
}
