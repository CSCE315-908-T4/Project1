package edu.tamu.csce315_908_t4.gui.backend.arguments;


public class ListArg implements IArgument{
    public final int initialYear;
    public final int finalYear;
    public ListArg(int year1, int year2)
    {
        if(year1> year2) {
            initialYear = year2;
            finalYear = year1;
        }
        else{
            initialYear = year1;
            finalYear = year2;
        }
    }
}
