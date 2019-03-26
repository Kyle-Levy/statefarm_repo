package com.kylelevydevgmail.brewwhiz;

/**
 * @author Kyle Levy 12/30/2017
 */
public class RadialCoordinate {
    private float radius;
    private float angle;

    public RadialCoordinate(float r, float theta){
        radius = r;
        angle = degreesToRadian(theta);
    }

    public float getRise(){
        float rise = radius * (float)(Math.sin((double)angle));
        return rise;
    }

    public float getRun(){
        float run = radius * (float)(Math.cos((double)angle));
        return run;
    }

    private float degreesToRadian(float degrees){
        return (degrees * (float)(Math.PI/180.0));
    }

    private float radianToDegrees(float radian) {
        return (radian * (float)(180.0/Math.PI));
    }

    public String toString(){
        return this.radius + ", " + radianToDegrees(this.angle);
    }
}

