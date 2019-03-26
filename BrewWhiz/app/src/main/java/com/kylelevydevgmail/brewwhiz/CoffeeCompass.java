package com.kylelevydevgmail.brewwhiz;

import java.util.HashMap;

/**
 * @author Kyle Levy 12/30/2017
 */
public class CoffeeCompass {

    private static final String[] flavorNames = {"Soupy", "Bulky", "Beefy", "Dull", "QuickFinish", "Salty", "Sour", "Vegetal",
            "Nutty", "Bland", "Underwhelming", "Insipid", "Watery", "Tea-like", "Flimsy", "Slender", "Sparse",
            "Faint", "Muted", "Scrawny", "Fragile", "Dilute", "Limp", "Astringent", "Dusty", "Empty", "Powdery",
            "Bitter", "Dry", "Intense", "Potent", "Severe", "Harsh", "Overwhelming", "Big", "Sticky", "Plump",
            "Substantial", "Buttery", "Smooth", "Nuanced", "Soft", "Creamy", "Transparent", "Thin", "Gentle",
            "Tasty", "Delicate", "Aromatic", "Pleasing", "Sweet", "Fruity", "Rich", "Luscious", "Mouth-filling",
            "Heavy", "Robust", "Thick", "Strong", "Hefty", "Balanced"};

    private static final RadialCoordinate[] flavorCoordinates = {new RadialCoordinate(6f, 102.8f), new RadialCoordinate(6f, 115.714f),
            new RadialCoordinate(6f, 128.571f), new RadialCoordinate(6f, 141.428f), new RadialCoordinate(6f, 154.285f),
            new RadialCoordinate(6f, 167.142f), new RadialCoordinate(6f, 191.25f), new RadialCoordinate(6f, 202.5f),
            new RadialCoordinate(6f, 213.75f), new RadialCoordinate(6f, 225f), new RadialCoordinate(6f, 236.25f),
            new RadialCoordinate(6f, 241f), new RadialCoordinate(6.5f, 241f), new RadialCoordinate(6f, 247.5f),
            new RadialCoordinate(6.5f, 247.5f), new RadialCoordinate(6f, 253), new RadialCoordinate(6.5f, 253),
            new RadialCoordinate(6f, 263), new RadialCoordinate(6f, 272), new RadialCoordinate(6f, 284.85f),
            new RadialCoordinate(6.5f, 284.85f), new RadialCoordinate(6f, 297.7f), new RadialCoordinate(6.5f, 297.7f),
            new RadialCoordinate(6f, 310.55f), new RadialCoordinate(6f, 323.4f), new RadialCoordinate(6f, 336.25f),
            new RadialCoordinate(6f, 349.1f), new RadialCoordinate(6f, 12.9f), new RadialCoordinate(6f, 25.7f),
            new RadialCoordinate(6f, 38.57f), new RadialCoordinate(6f, 51.42f), new RadialCoordinate(6f, 64.285f),
            new RadialCoordinate(6f, 77.142f), new RadialCoordinate(6f, 85), new RadialCoordinate(6f, 102.857f),
            new RadialCoordinate(4.8f, 117.7f), new RadialCoordinate(4.6f, 128.57f), new RadialCoordinate(3.9f, 136.4f),
            new RadialCoordinate(6f, 165.2f), new RadialCoordinate(4f, 180f), new RadialCoordinate(4.1f, 192.25f),
            new RadialCoordinate(4.6f, 236.25f), new RadialCoordinate(3, 241f), new RadialCoordinate(5, 247.5f),
            new RadialCoordinate(4.6f, 270f), new RadialCoordinate(3.8f, 270f), new RadialCoordinate(1.8f, 282.2f),
            new RadialCoordinate(5, 282.2f), new RadialCoordinate(4, 323.4f), new RadialCoordinate(3.3f, 347.2f),
            new RadialCoordinate(3.9f, 0), new RadialCoordinate(2.2f, 7f), new RadialCoordinate(4, 38.57f),
            new RadialCoordinate(3.1f, 64.28f), new RadialCoordinate(1.5f, 90), new RadialCoordinate(3.4f, 94f),
            new RadialCoordinate(3.4f, 87), new RadialCoordinate(4.2f, 77.14f), new RadialCoordinate(4.1f, 90f),
            new RadialCoordinate(5, 77.142f), new RadialCoordinate(0, 0)};

    private static final HashMap<String, RadialCoordinate> compass = initializeCompass();

    private static HashMap<String, RadialCoordinate> initializeCompass() {

        HashMap<String, RadialCoordinate> methodCompass = new HashMap<>();

        for (int i = 0; i < flavorNames.length; i++) {
            methodCompass.put(flavorNames[i].toLowerCase(), flavorCoordinates[i]);
        }

        return methodCompass;
    }

    public RadialCoordinate getRadialCoordinate(String name) {
        return compass.get(name.toLowerCase());
    }

    public float[] calculateBrewChanges(String currentName, String desiredName) {
        RadialCoordinate currentPoint = getRadialCoordinate(currentName);
        RadialCoordinate desiredPoint = getRadialCoordinate(desiredName);
        float xChange = calcChangeX(currentPoint, desiredPoint);
        float yChange = calcChangeY(currentPoint, desiredPoint);
        float zChange = (float) Math.sqrt((xChange * xChange) + (yChange * yChange));
        float degree = calcBrewDegree(xChange, yChange);

        int coffee = 0, extract = 0;
        if (degree == -1) {
            //TODO this means no change in X or Y, do somethin about it
        } else if (degree < 22.5 || degree >= 337.5) {
            extract = 1;
            coffee = -1;
        } else if (degree >= 22.5 && degree < 67.5) {
            extract = 1;
        } else if (degree >= 67.5 && degree < 112.5) {
            coffee = 1;
        } else if (degree >= 112.5 && degree < 157.5) {
            extract = -1;
            coffee = 1;
        } else if (degree >= 157.5 && degree < 202.5) {
            extract = -1;
            coffee = 1;
        } else if (degree >= 202.5 && degree < 247.5) {
            extract = -1;
        } else if (degree >= 247.5 && degree < 292.5) {
            coffee = -1;
        } else if (degree >= 292.5 && degree < 337.5) {
            extract = 1;
            coffee = -1;
        }
        float[] directions = {coffee, extract, zChange};
        return directions;
    }

    private float calcChangeX(RadialCoordinate currentPoint, RadialCoordinate desiredPoint) {
        return desiredPoint.getRun() - currentPoint.getRun();
    }

    private float calcChangeY(RadialCoordinate currentPoint, RadialCoordinate desiredPoint) {
        return desiredPoint.getRise() - currentPoint.getRise();
    }

    private float calcBrewDegree(float x, float y) {
        float degree = 0;

        //If x and y are very small, make them equal to 0.
        if(x < .001 && x >= 0){
            x = 0;
        }

        if(y < .001 && y >= 0){
            y = 0;
        }

        if (x == 0 && y == 0) {
            degree = -1;
        } else if (x == 0) {
            if (y > 0) {
                degree = 90;
            } else if (y < 0) {
                degree = 270;
            }
        } else if (y == 0) {
            if (x > 0) {
                degree = 0;
            } else if (x < 0) {
                degree = 180;
            }
        } else {
            int quadrant = determineQuadrant(x, y);
            System.out.println(quadrant);
            double radian = Math.atan((double) y / x);
            degree = ((quadrant - 1) * 90) + (float)Math.toDegrees(radian);
            if(quadrant == 2 || quadrant == 4){
                degree += 90;
            }
            System.out.println(degree);
        }

        return degree;
    }

    private int determineQuadrant(float x, float y) {
        int quadrant = 0;
        if (x > 0 && y > 0) {
            quadrant = 1;
        } else if (x < 0 && y > 0) {
            quadrant = 2;
        } else if (x < 0 && y < 0) {
            quadrant = 3;
        } else if (x > 0 && y < 0) {
            quadrant = 4;
        }
        return quadrant;
    }
}
