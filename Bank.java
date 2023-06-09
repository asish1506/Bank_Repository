package model;

import java.util.List;

public record Bank(String id,
                      String name,
                      String description,
                      List<Course> courses) {

}