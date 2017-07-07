package org.sring.LeanTaaS.projector.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.sring.LeanTaaS.projector.database.Database;
import org.sring.LeanTaaS.projector.model.Projector;
import org.sring.LeanTaaS.projector.model.Team;

public class ProjectorService {
    private Map<Integer, Projector> projectors = Database.getProjectors();
    private Map<Integer, Team> teams = Database.getTeams();
    public ProjectorService() {
        projectors.put(1, new Projector(1));
        projectors.put(2, new Projector(2));
        projectors.put(3, new Projector(3));
        teams.put(1, new Team(1));
        teams.put(2, new Team(2));
        teams.put(3, new Team(3));
        teams.put(4, new Team(4));
        teams.put(5, new Team(5));
    }

    public List<Projector> getAllProjectors() {
        return new ArrayList<Projector>(projectors.values());
    }
    public Projector getProjector(int id) {
        return projectors.get(id);
    }
    
}
