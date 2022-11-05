package code;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.*;

/**
 * This class is used to read and write data from and to a CSV file.
 *
 */
abstract class Database{
    /**
     * This method is used to read data from a CSV file.
     *
     * @param fileName The name of the CSV file to read from.
     * @return ArrayList of String arrays containing the data from the CSV file
     * @throws IOException If the file cannot be read.
     */
    public abstract ArrayList read(String filename) throws IOException;

    /**
     * This method is used to write data to a CSV file.
     *
     * @param fileName The name of the CSV file to write to.
     * @param data     The data to write to the CSV file.
     * @throws IOException If the file cannot be written to.
     */
    public abstract void save(String filename, List data) throws IOException;

    /**
     * This method is used to read data from a CSV file.
     *
     * @param file The name of the CSV file to write to.
     * @return List of String arrays containing the data from the CSV file
     */
    public static List readAllData(String file) {
        try {
            //reader
            CSVReader csvReader = new CSVReader(new FileReader("src\\database\\data\\" + file));

            List<String[]> data = csvReader.readAll();

            csvReader.close();

            return data;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }

    /**
     * This method is used to write all data to a CSV file.
     *
     * @param file The name of the CSV file to write to.
     * @param data     The data to write to the CSV file.
     */
    public static void writeAllData(String file, List<String[]> data) {
        try {
            //writer
            Writer writer = Files.newBufferedWriter(Paths.get("src\\database\\data\\" + file));

            // create a csv writer
            ICSVWriter csvWriter = new CSVWriterBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withEscapeChar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .build();

            // write data records
            csvWriter.writeAll(data);
            csvWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
