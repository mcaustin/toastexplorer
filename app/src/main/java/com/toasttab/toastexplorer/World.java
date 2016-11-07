package com.toasttab.toastexplorer;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

public class World {

    private String name;

    private Room firstRoom;

    private HashMap<Integer, Room> roomMap;

    public World() {
        roomMap = new HashMap<>();
    }

    public Room getFirstRoom() {
        return firstRoom;
    }

    public Room getRoom(Integer id) {
        return roomMap.get(id);
    }

    private Route parseRoute(XmlResourceParser xmlResourceParser) {
        String text = xmlResourceParser.getAttributeValue(null, "text");
        System.out.println("text=" + text);
        Integer id = Integer.parseInt(xmlResourceParser.getAttributeValue(null, "id"));
        System.out.println("id=" + id);
        Route route = new Route(id, text);
        return route;
    }

    private Room parseRoom(XmlResourceParser xmlResourceParser) {
        name = xmlResourceParser.getAttributeValue(null, "name");
        System.out.println("name=" + name);
        Integer id = Integer.parseInt(xmlResourceParser.getAttributeValue(null, "id"));
        System.out.println("id=" + id);

        Room room = new Room("imagePath", name);
        room.setId(id);

        try {
            while(xmlResourceParser.getEventType() != XmlPullParser.END_TAG) {
                if (xmlResourceParser.getName() == null) {
                    xmlResourceParser.next();
                    continue;
                }
                switch (xmlResourceParser.getName()) {
                    case "route":
                        Route route = parseRoute(xmlResourceParser);
                        room.addRoute(route);
                        break;
                    default:
                }
                xmlResourceParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return room;
    }

    public void initFromFile(Resources res) {
        XmlResourceParser xmlResourceParser = res.getXml(R.xml.toastoffice);

        try {
            while (xmlResourceParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xmlResourceParser.getName() == null || xmlResourceParser.getEventType() == XmlPullParser.END_TAG) {
                    xmlResourceParser.next();
                    continue;
                }
                String name;
                switch (xmlResourceParser.getName()) {
                    case "world":
                        name = xmlResourceParser.getAttributeValue(null, "name");
                        System.out.println("name=" + name);
                        break;
                    case "mainroom":
                        this.firstRoom = parseRoom(xmlResourceParser);
                        roomMap.put(firstRoom.getId(), firstRoom);
                        break;
                    case "room":
                        Room parsedRoom = parseRoom(xmlResourceParser);
                        roomMap.put(parsedRoom.getId(), parsedRoom);
                        break;
                    default:
                }

                System.out.println(xmlResourceParser.getName());
                xmlResourceParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
