package pl.edu.agh.niebieskiekotki.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Terms{
    List<String> headers = Arrays.asList("Poniedziałek", "Wtorek", "Sroda", "Czwartek","Piątek");
    List<Row> rows = new ArrayList<>();

    public List<String> getHeaders() {
        return headers;
    }

    public List<Row> getRows() {
        return rows;
    }

    Terms(){
        rows.add(new Row("8:00 - 9:30"));
        rows.add(new Row("9:35 - 11:05"));
        rows.add(new Row("11:15 - 12:45"));
        rows.add(new Row("12:50 - 14:20"));
        rows.add(new Row("14:40 - 16:10"));
        rows.add(new Row("16:15 - 17:45"));
        rows.add(new Row("17:50 - 19:20"));
    }

    class Row{
        String Label;
        List<Cell> cells = new ArrayList<>();

        public String getLabel() {
            return Label;
        }

        public List<Cell> getCells() {
            return cells;
        }

        public Row(String label) {
            Label = label;
            cells.add(new Cell());
            cells.add(new Cell());
            cells.add(new Cell());
            cells.add(new Cell());
            cells.add(new Cell());
        }

        class Cell{
            static int nextID = 0;
            Integer id;


            Cell(){
                id = nextID++;
            }

            public Integer getId() {
                return id;
            }

        }

    }
}
