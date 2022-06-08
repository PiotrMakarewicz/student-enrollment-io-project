package pl.edu.agh.niebieskiekotki.views;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.entitites.Timeslot;
import pl.edu.agh.niebieskiekotki.utility.Days;

import java.util.*;

public class CalendarView {


    List<String> headers = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
    @JsonIgnore
    SortedMap<Integer, CalendarViewRow> privateRows;
    List<CalendarViewRow> rows;


    public void setRows(List<CalendarViewRow> rows) {
        this.rows = rows;
    }

    public CalendarView(List<Term> terms, List<Timeslot> timeslots) {
        this.privateRows = new TreeMap<>();

        for (Timeslot timeslot : timeslots) {
            this.privateRows.put(Math.toIntExact(timeslot.getId()), new CalendarViewRow(timeslot.toString()));
        }

        for (Term term : terms) {
            privateRows.get(Math.toIntExact(term.getTimeslot().getId()))
                    .add(term.getDay(), term.getId());
        }

        rows = new ArrayList<>(privateRows.values());
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

    class CalendarViewRow {
        String label;
        List<Long> cells;

        public CalendarViewRow(String label) {
            this.label = label;
            this.cells = new ArrayList<>();
        }

        public void add(Days day, Long value) {
            this.cells.add(day.ordinal(), value);
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
}
