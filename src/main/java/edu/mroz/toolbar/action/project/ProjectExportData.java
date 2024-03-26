package edu.mroz.toolbar.action.project;

import edu.mroz.components.ColoredShape;
import edu.mroz.data.PointerParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectExportData implements Serializable {

    private PointerParameters pointerParameters;
    private List<ColoredShape> shapes;
}
