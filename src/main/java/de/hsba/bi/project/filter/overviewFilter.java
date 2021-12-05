package de.hsba.bi.project.filter;

import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Event;
import org.springframework.cglib.core.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class overviewFilter {
    public static void main(String[] args) {
        Event e1 = new Event("NameEins", "BeschreibungEins", Category.Seminar, 1, 10);
        Event e2 = new Event("NameZwei", "BeschreibungZwei", Category.ELearning, 2, 15);
        Event e3 = new Event("NameDrei", "BeschreibungDrei", Category.Teamtag, 3, 20);
        Event e4 = new Event("NameVier", "BeschreibungVier", Category.Workshop, 4, 5);

        //var events = Arrays.asList(e1, e2, e3, e4);
        List<Event> events1 = Arrays.asList(e1, e2, e3, e4);

 /*       List<Event> eventWithMoreThanTwoDuration = events1
                .stream()
                .filter(Event::hasOverTwoDuration)
                .collect(Collectors.toList());


        //ArrayList<Event[]>(events);

        List<Event> events2 = Arrays.asList(e1, e2, e3, e4);
        ArrayList<String> filteredList = new ArrayList<>(events2);
        CollectionUtils.filter(filteredList, new org.springframework.cglib.core.Predicate<String>() {
            @Override
            public boolean evaluate(Object o) {
                return o.toString().startsWith("F");
            }
        });
*/
    }
}

