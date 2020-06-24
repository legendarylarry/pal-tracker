package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Set<TimeEntry> entries = new HashSet<TimeEntry>();
    long nextId = 1L;

    public TimeEntry create(TimeEntry timeEntry){
        if ( timeEntry.getId() < 1){
            timeEntry.setId(nextId++);
        }
        entries.add(timeEntry);
        return timeEntry;
    }

    public List<TimeEntry> list(){
        return Arrays.asList( entries.toArray(new TimeEntry[0]) );
    }

    public TimeEntry delete( Long id ){
        TimeEntry item = find(id);
        if ( item != null ) {
            entries.removeIf(timeEntry -> timeEntry.getId() == id);
            return item;
        }
        return null;
    }

    public TimeEntry update( Long id, TimeEntry entry ){

        if ( find(id) != null ){
            entries.removeIf(timeEntry -> timeEntry.getId() == id);
            entry.setId(id);
            entries.add(entry);
            return entry;
        }
        return null;
    }

    public TimeEntry find(Long id){
        for ( TimeEntry t: entries ){
            if ( t.getId() == id){
                return t;
            }
        }
        return null;
    }
}
