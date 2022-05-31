package pl.edu.agh.niebieskiekotki.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeletedImpossibilitiesView {
    List<Long> deletedImpossibilities = new ArrayList<>();

    public DeletedImpossibilitiesView() {
    }

    public List<Long> getDeletedImpossibilities() {
        return deletedImpossibilities;
    }

    public void setDeletedImpossibilities(List<Long> deletedImposibilities) {
        this.deletedImpossibilities = deletedImposibilities;
    }
}
