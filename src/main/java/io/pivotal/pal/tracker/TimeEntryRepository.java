package io.pivotal.pal.tracker;

import java.util.Arrays;
import java.util.List;

public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry timeEntry);

    public List<TimeEntry> list();

    public TimeEntry delete( long id );

    public TimeEntry update( long id, TimeEntry entry );

    public TimeEntry find(long id);
}
