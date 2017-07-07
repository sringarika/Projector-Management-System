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
}
