package org.sring.LeanTaaS.projector.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sring.LeanTaaS.projector.model.Projector;
import org.sring.LeanTaaS.projector.model.Team;

public class Database {

    private static Map<Integer, Projector> projectors = new HashMap<Integer, Projector>();
    private static Map<Integer, Team> teams = new HashMap<Integer, Team>();
    
    
    public static Map<Integer, Projector> getProjectors() {
        return projectors;
    }
    public static Map<Integer, Team> getTeams() {
        return teams;
    }
    static {
        projectors.put(1, new Projector(1));
        projectors.put(2, new Projector(2));
        projectors.put(3, new Projector(3));
        teams.put(1, new Team(1));
        teams.put(2, new Team(2));
        teams.put(3, new Team(3));
        teams.put(4, new Team(4));
        teams.put(5, new Team(5));
    }
}
