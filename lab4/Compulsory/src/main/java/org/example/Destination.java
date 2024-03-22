package org.example;
import lombok.*;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class Destination {
    private String name;
    private List<Destination> road;
}
