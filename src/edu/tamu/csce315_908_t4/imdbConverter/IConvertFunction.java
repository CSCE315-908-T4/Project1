package edu.tamu.csce315_908_t4.imdbConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public interface IConvertFunction{
    void convert(ResultSet resultSet, HashMap<EOutputTable, Statement> outputStatements) throws SQLException;
}
