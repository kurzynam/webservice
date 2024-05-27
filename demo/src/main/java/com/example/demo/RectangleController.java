package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RectangleController {
    List<Rectangle> rectangles = new ArrayList<>();

    @GetMapping("/rectangle")
    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(
                20,10,
                100,213,
                "red");
        return rectangle;
    }

    @PostMapping("/addRectangle")
    public int addRectangle() {
        Rectangle rectangle = new Rectangle(
                20,10,
                100,213,
                "red");

        rectangles.add(rectangle);

        return rectangles.size();
    }

    @GetMapping("rectangles")
    public List<Rectangle> getRectangles() {
        return rectangles;
    }


    @PostMapping("/addRectangleBody")
    public void addRectangle(@RequestBody Rectangle rectangle) {
        rectangles.add(rectangle);
    }

    @GetMapping("/get/{index}")
    public Rectangle getRectangle(@PathVariable int index) {
        if (index >= 0 && index < rectangles.size()) {
            return rectangles.get(index);
        } else {
            throw new IndexOutOfBoundsException("Nie ma prostokąta o podanym indeksie.");
        }
    }
    @PutMapping("/insert/{index}")
    public void updateRectangle(@PathVariable int index, @RequestBody Rectangle rectangle) {
        if (index >= 0 && index < rectangles.size()) {
            rectangles.set(index, rectangle);
        } else {
            throw new IndexOutOfBoundsException("Nie ma prostokąta o podanym indeksie.");
        }
    }

    @DeleteMapping("/delete/{index}")
    public void deleteRectangle(@PathVariable int index) {
        if (index >= 0 && index < rectangles.size()) {
            rectangles.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Nie ma prostokąta o podanym indeksie.");
        }
    }
    @GetMapping("/svg")
    public String getSvg() {
        StringBuilder svgBuilder = new StringBuilder();
        svgBuilder.append("<svg width=\"800\" height=\"600\" xmlns=\"http://www.w3.org/2000/svg\">");

        for (Rectangle rect : rectangles) {
            svgBuilder.append(String.format(
                    "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"%s\" />",
                    rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), rect.getColor()
            ));
        }

        svgBuilder.append("</svg>");
        return svgBuilder.toString();
    }
}