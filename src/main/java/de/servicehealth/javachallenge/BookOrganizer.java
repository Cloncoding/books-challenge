package de.servicehealth.javachallenge;

import de.servicehealth.javachallenge.model.Element;
import de.servicehealth.javachallenge.model.Type;

import java.util.*;
import java.util.stream.Collectors;

public class BookOrganizer {

	public Collection<String> organize(Iterable<Element> elements) {
        HashMap<Long, Element> elMap = new HashMap<>();
        List<Element> books = new ArrayList<>();

        for(Element e : elements) {
            switch (e.getType()) {
                case ITEM -> books.add(e);
                case COLLECTION -> elMap.put(e.getId(), e);
            }
        }

        /* Conventional way
        Collection<String> paths = new ArrayList<>();
        for(Element book : books) {
            paths.add(findParent(elMap, book));
        }
        */

        // Lambda expression
        Collection<String> paths = books.stream()
                .map(b -> findParent(elMap, b))
                .toList();

        paths.forEach(System.out::println);
        return paths;
	}

    private String findParent(HashMap<Long, Element> map, Element el) {
        /* Conventional way
        String text = "";
        if(el.getParentId() != null) {
            text = findParent(map, map.get(el.getParentId()));
        }
        return text + "/" + el.getName();
        */

        // short
        return (el.getParentId() != null
                    ? findParent(map, map.get(el.getParentId()))
                    : "")
                + "/" + el.getName();
    }
}
