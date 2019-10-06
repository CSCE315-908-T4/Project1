package edu.tamu.csce315_908_t4.imdbConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Util{
    public static void sqlTry(ISQLThrowFunction function){
        try{
            function.run();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> parseArray(String arrayIn){
        ArrayList<StringBuilder> outBuilder = new ArrayList<>();
        boolean inWord = false;
        for(int x = 0; x < arrayIn.length(); x++){
            char ch = arrayIn.charAt(x);
            if(!inWord && (ch == '[' || ch == ']' || ch == ',')){
                continue;
            }
            if(ch == '\"'){
                if(inWord){
                    inWord = false;
                } else{
                    inWord = true;
                    outBuilder.add(new StringBuilder());
                }
            } else{
                outBuilder.get(outBuilder.size() - 1).append(ch);
            }
        }
        ArrayList<String> out = new ArrayList<>(outBuilder.size());
        for(StringBuilder stringBuilder : outBuilder){
            out.add(stringBuilder.toString());
        }
        return out;
    }

    public static <T> String toSQL(T in){
        if(in == null){
            return "NULL";
        }
        if(in.getClass() == String.class){
            return "\'" +
                    in.toString().replace("\'", "\'\'") +
                    "\'";
        }
        return in.toString();
    }

    public static Short sqlParseShort(ResultSet resultSet, String columnName) throws SQLException{
        Object result = resultSet.getObject(columnName);
        if(result == null){
            return null;
        }
        return ((Number) result).shortValue();
    }

    public static Integer sqlParseInt(ResultSet resultSet, String columnName) throws SQLException{
        Object result = resultSet.getObject(columnName);
        if(result == null){
            return null;
        }
        return ((Number) result).intValue();
    }

    public static Float sqlParseFloat(ResultSet resultSet, String columnName) throws SQLException{
        Object result = resultSet.getObject(columnName);
        if(result == null){
            return null;
        }
        return ((Number) result).floatValue();
    }
}
