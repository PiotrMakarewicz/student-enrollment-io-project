package pl.edu.agh.niebieskiekotki.views;

import pl.edu.agh.niebieskiekotki.entitites.Vote;

import java.util.List;

public class ImposibilitiesView {
    private List<Vote> votes;

    public ImposibilitiesView(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
