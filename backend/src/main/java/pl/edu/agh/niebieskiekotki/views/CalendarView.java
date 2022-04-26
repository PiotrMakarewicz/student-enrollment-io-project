package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Timeslot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CalendarView {


    List<String> headers = Arrays.asList("Monday","Tuesday", "Wednesday", "Thursday", "Friday");
    List<CalendarViewRow> rows;


    public CalendarView(List<Term> terms) {
        this.rows = new ArrayList<>();

        for (Timeslot timeslot: DataBaseMock.timeslots) {
            this.rows.add(Math.toIntExact(timeslot.getId()), new CalendarViewRow(timeslot.toString()));
        }

        for(Term term: terms) {
            rows.get(Math.toIntExact(term.getTimeslot().getId() - 1))
                    .add(Math.toIntExact(term.getDay()),term.getId());
        }
    }

    class CalendarViewRow{
        String label;
        List<Long> cells;

        public CalendarViewRow(String label) {
            this.label = label;
            this.cells = new ArrayList<>();
        }

        public void add(Integer index, Long value){
            this.cells.add(index,value);
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public List<Long> getCells() {
            return cells;
        }

        public void setCells(List<Long> cells) {
            this.cells = cells;
        }
    }


    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<CalendarViewRow> getRows() {
        return rows;
    }

    public void setRows(List<CalendarViewRow> rows) {
        this.rows = rows;
    }
}
